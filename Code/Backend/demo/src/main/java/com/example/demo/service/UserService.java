package com.example.demo.service;

import com.example.demo.dal.IUserDal;
import com.example.demo.entity.User;
import com.example.demo.entity.UserPrincipal;
import com.example.demo.enumeration.Role;
import com.example.demo.exception.domain.EmailExistsException;
import com.example.demo.exception.domain.PasswordsNotMatchingException;
import com.example.demo.exception.domain.UsernameExistsException;
import com.example.demo.serviceInterfaces.IUserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class UserService implements IUserService, UserDetailsService {

    public static final String USERNAME_IS_ALREADY_TAKEN = "This username is already taken";
    public static final String EMAIL_IS_ALREADY_TAKEN = "This email is already taken";
    public static final String NO_USER_FOUND_WITH_USERNAME = "No user found with username: ";
    public static final String NO_USER_FOUND_WITH_EMAIL = "There is no user found with email: ";
    private Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private IUserDal userDal; //in tutorial era cu userDao, daca nu merge il schimb inapoi

    private BCryptPasswordEncoder passwordEncoder;

    private LogInAttemptService logInAttemptService;

    private EmailService emailService;

    @Autowired
    public UserService(IUserDal userDal, BCryptPasswordEncoder passwordEncoder, LogInAttemptService logInAttemptService,
                       EmailService emailService)
    {
        this.userDal = userDal;
        this.passwordEncoder = passwordEncoder;
        this.logInAttemptService = logInAttemptService;
        this.emailService = emailService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDal.getUserByUsername(username);
        if(user == null)
        {
            LOGGER.error(NO_USER_FOUND_WITH_USERNAME + username);
            throw new UsernameNotFoundException(NO_USER_FOUND_WITH_USERNAME + username);
        }
        else
        {
            validateLoginAttempt(user);
            user.setLastLoginDateDisplay(user.getLastLoginDate());
            user.setLastLoginDate(new Date());
            userDal.addUser(user);
            UserPrincipal userPrincipal = new UserPrincipal(user);
            LOGGER.info("Returning found user by username: " + username);
            return userPrincipal;
        }
    }

    private void validateLoginAttempt(User user) { //to test
        if(user.getNotLocked())
        {
            if(logInAttemptService.hasExceededMaxAttempts(user.getUsername()))
            {
                user.setNotLocked(false);
            }
            else
            {
                user.setNotLocked(true);
            }
        }
        else
        {
            logInAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
        }
    }

    @Override
    public User register(String firstName, String lastName, String username, String email) throws UsernameExistsException, EmailExistsException, MessagingException {
        validateNewUsernameAndEmail(StringUtils.EMPTY, username, email);
        User user = new User();

        user.setUserId(generateUserId());
        setUserDetails(user, username, firstName, lastName, email, true, true);
        String password = generatePassword();
        user.setJoinDate(new Date());
        user.setPassword(encodePassword(password));
        user.setRole(Role.ROLE_USER.name());
        user.setAuthorities(Role.ROLE_USER.getAuthorities());

        userDal.addUser(user);
        LOGGER.info("New user password: " + password);
        emailService.sendNewPasswordEmail(firstName, password, email);

        return user;
    }

    @Override
    public User getUserById(Long id) {
        return userDal.getUserById(id);
    }

    private String encodePassword(String password)
    {
        return passwordEncoder.encode(password);
    }

    private String generatePassword()
    {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    private String generateUserId()
    {
        return RandomStringUtils.randomNumeric(10);
    }

    private User validateNewUsernameAndEmail(String currentUsername, String newUsername, String email) throws
            UsernameExistsException, UsernameNotFoundException, EmailExistsException
    {
        User userByNewUsername = userDal.getUserByUsername(newUsername);
        User userByNewEmail = userDal.getUserByEmail(email);

        if(StringUtils.isNoneBlank(currentUsername))
        {
            User currentUser = userDal.getUserByUsername(currentUsername);
            if(currentUser == null)
            {
                throw new UsernameNotFoundException(NO_USER_FOUND_WITH_USERNAME + currentUsername);
            }

            if(userByNewUsername != null && !currentUser.getId().equals(userByNewUsername.getId()))
            {
                throw new UsernameExistsException(USERNAME_IS_ALREADY_TAKEN);
            }

            if(userByNewEmail != null && !currentUser.getId().equals(userByNewEmail.getId()))
            {
                throw new EmailExistsException(EMAIL_IS_ALREADY_TAKEN);
            }
            return currentUser;
        }
        else
        {
            User userByUsername = userDal.getUserByUsername(newUsername);
            if(userByUsername != null)
            {
                throw new UsernameExistsException(USERNAME_IS_ALREADY_TAKEN);
            }
            User userByEmail = userDal.getUserByEmail(email);
            if(userByEmail != null)
            {
                throw new EmailExistsException(EMAIL_IS_ALREADY_TAKEN);
            }
            return null;
        }
    }

    @Override
    public List<User> getUsers() {
        return userDal.getAllUsers();
    }

    @Override
    public User getUserByUsername(String username) {
        return userDal.getUserByUsername(username);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDal.getUserByEmail(email);
    }

    @Override
    public User addNewUser(String firstName, String lastName, String username, String email, String role, boolean isNotLocked, boolean isActive) throws UsernameExistsException, EmailExistsException {
        validateNewUsernameAndEmail(StringUtils.EMPTY, username, email);
        User user = new User();

        user.setUserId(generateUserId());
        String password = generatePassword();
        user.setJoinDate(new Date());
        user.setPassword(encodePassword(password));
        setUserDetails(user, username, firstName, lastName, email, isNotLocked, isActive);
        user.setRole(getRoleEnumName(role).name());
        user.setAuthorities(getRoleEnumName(role).getAuthorities());
        LOGGER.info("New user password: " + password);
        userDal.addUser(user);
        return user;
    }

    private Role getRoleEnumName(String role)
    {
        return Role.valueOf(role.toUpperCase());
    }

    @Override
    public User updateUser(String currentUsername, String newFirstName, String newLastName, String newUsername, String newEmail, String role, boolean isNotLocked, boolean isActive) throws UsernameExistsException, EmailExistsException {
        User currentUser = validateNewUsernameAndEmail(currentUsername, newUsername, newEmail);

        setUserDetails(currentUser, newUsername, newFirstName, newLastName, newEmail, isNotLocked, isActive);
        currentUser.setRole(getRoleEnumName(role).name());
        currentUser.setAuthorities(getRoleEnumName(role).getAuthorities());

        userDal.addUser(currentUser);
        return currentUser;
    }

    @Override
    public void deleteUser(Long id)
    {
        userDal.deleteUser(id);
    }

    @Override
    public void resetPassword(String email) throws EmailExistsException, MessagingException {
        User user = userDal.getUserByEmail(email);

        if(user == null)
        {
            throw new EmailExistsException(NO_USER_FOUND_WITH_EMAIL + email);
        }
        String password = generatePassword();
        user.setPassword(encodePassword(password));

        userDal.addUser(user);
        emailService.sendNewPasswordEmail(user.getFirstName(), password, email);

    }

    @Override
    public User updateUserPersonalInformation(String currentUsername, String newUsername, String newEmail) throws UsernameExistsException, EmailExistsException {
        User currentUser = validateNewUsernameAndEmail(currentUsername, newUsername, newEmail);

        currentUser.setUsername(newUsername);
        currentUser.setEmail(newEmail);

        userDal.addUser(currentUser);
        return currentUser;
    }

    @Override
    public User changePassword(String username, String password, String confirmPassword) throws UsernameNotFoundException, PasswordsNotMatchingException {
        User user = this.getUserByUsername(username);
        if(user == null)
        {
            throw new UsernameNotFoundException(NO_USER_FOUND_WITH_USERNAME + username);
        }
        else if(!password.equals(confirmPassword))
        {
            throw new PasswordsNotMatchingException("The passwords don't match");
        }
        else
        {
            user.setPassword(encodePassword(password));
            LOGGER.info("New user password: " + password);
            userDal.addUser(user);
            return user;
        }

    }

    @Override
    public List<User> getLastFiveUsers() {
        return userDal.getTheLastFiveUsers();
    }

    private void setUserDetails(User user, String username, String firstName, String lastName, String email, boolean isNotLocked, boolean isActive)
    {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setEmail(email);
        user.setActive(isActive);
        user.setNotLocked(isNotLocked);
    }
}
