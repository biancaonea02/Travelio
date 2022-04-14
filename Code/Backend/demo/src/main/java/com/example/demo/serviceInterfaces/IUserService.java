package com.example.demo.serviceInterfaces;

import com.example.demo.entity.User;
import com.example.demo.exception.domain.EmailExistsException;
import com.example.demo.exception.domain.PasswordsNotMatchingException;
import com.example.demo.exception.domain.UsernameExistsException;

import javax.mail.MessagingException;
import java.util.List;

public interface IUserService {
    User register(String firstName, String lastName, String username, String email) throws UsernameExistsException, EmailExistsException, MessagingException;
    User getUserById(Long id);
    List<User> getUsers();
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    User addNewUser(String firstName, String lastName, String username, String email, String role, boolean isNotLocked, boolean isActive) throws UsernameExistsException, EmailExistsException;
    User updateUser(String currentUsername, String newFirstName,String newLastName, String newUsername, String newEmail, String role, boolean isNotLocked, boolean isActive) throws UsernameExistsException, EmailExistsException;
    void deleteUser(Long id);
    void resetPassword(String email) throws EmailExistsException, MessagingException;
    User updateUserPersonalInformation(String currentUsername, String newUsername, String newEmail) throws UsernameExistsException, EmailExistsException;
    User changePassword(String username, String password, String confirmPassword) throws PasswordsNotMatchingException;
    List<User> getLastFiveUsers();
}


