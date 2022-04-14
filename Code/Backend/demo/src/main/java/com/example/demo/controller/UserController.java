package com.example.demo.controller;

import com.example.demo.entity.HttpResponse;
import com.example.demo.entity.User;
import com.example.demo.entity.UserPrincipal;
import com.example.demo.exception.domain.*;
import com.example.demo.serviceInterfaces.IUserService;
import com.example.demo.utility.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.websocket.server.PathParam;

import java.util.List;

import static com.example.demo.constant.SecurityConstant.JWT_TOKEN_HEADER;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/user")
public class UserController extends ExceptionHandling {

    private IUserService iUserService;

    private AuthenticationManager authenticationManager;

    private JWTTokenProvider tokenProvider;


    @Autowired
    public UserController(IUserService iUserService, AuthenticationManager authenticationManager,
                          JWTTokenProvider tokenProvider)
    {
        this.iUserService = iUserService;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) {
        User user = iUserService.getUserById(id);

        if (user != null) {
            return new ResponseEntity<>(user, OK);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws UsernameExistsException, EmailExistsException, MessagingException {
        User newUser = iUserService.register(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail());
        return new ResponseEntity<>(newUser, OK);
    }


    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) throws UsernameExistsException, EmailExistsException
    {
        authenticate(user.getUsername(), user.getPassword());
        User loginUser = iUserService.getUserByUsername(user.getUsername());
        UserPrincipal userPrincipal = new UserPrincipal(loginUser);
        HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
        return new ResponseEntity<>(loginUser, jwtHeader, OK);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestParam("firstName") String firstName,
                                        @RequestParam("lastName") String lastName,
                                        @RequestParam("username") String username,
                                        @RequestParam("email") String email,
                                        @RequestParam("role") String role,
                                        @RequestParam("isNotLocked") String isNotLocked,
                                        @RequestParam("isActive") String isActive) throws UsernameExistsException, EmailExistsException {
        User newUser = iUserService.addNewUser(firstName, lastName, username, email, role, Boolean.parseBoolean(isNotLocked), Boolean.parseBoolean(isActive));
        return new ResponseEntity<>(newUser, OK);
    }

    @PostMapping("/update")
    public ResponseEntity<User> updateUser(@RequestParam("currentUsername") String currentUsername,
                                           @RequestParam("firstName") String firstName,
                                           @RequestParam("lastName") String lastName,
                                           @RequestParam("username") String username,
                                           @RequestParam("email") String email,
                                           @RequestParam("role") String role,
                                           @RequestParam("isNotLocked") String isNotLocked,
                                           @RequestParam("isActive") String isActive) throws UsernameExistsException, EmailExistsException {
        User updatedUser = iUserService.updateUser(currentUsername, firstName, lastName, username, email, role, Boolean.parseBoolean(isNotLocked), Boolean.parseBoolean(isActive));
        return new ResponseEntity<>(updatedUser, OK);
    }

    @PostMapping("/updatePersonalInfo")
    public ResponseEntity<User> updatePersonalInfo(@PathParam("currentUsername") String currentUsername,
                                                   @PathParam("username") String username,
                                                   @PathParam("email") String email) throws UsernameExistsException, EmailExistsException {
        User updatedUser = iUserService.updateUserPersonalInformation(currentUsername, username, email);
        return new ResponseEntity<>(updatedUser, OK);
    }

    @GetMapping("/find/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username")String username)
    {
        User userByUsername = iUserService.getUserByUsername(username);
        return new ResponseEntity<>(userByUsername, OK);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers()
    {
        List<User> users = iUserService.getUsers();
        return new ResponseEntity<>(users, OK);
    }

    @GetMapping("/resetPassword/{email}")
    public ResponseEntity<HttpResponse> resetPassword(@PathVariable("email")String email) throws MessagingException, EmailExistsException {
        iUserService.resetPassword(email);
        return response(OK, "E-mail with a new password was sent to: " + email);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('user:delete')")
    public ResponseEntity<HttpResponse> deleteUser(@PathVariable("id")Long id)
    {
        iUserService.deleteUser(id);
        return response(NO_CONTENT, "User was successfully deleted");
    }

    @PostMapping("/changePassword")
    public ResponseEntity<User> changePassword(@PathParam("username") String username,
                                               @PathParam("password") String password,
                                               @PathParam("confirmPassword") String confirmPassword) throws PasswordsNotMatchingException {
        User updatedUser = iUserService.changePassword(username, password, confirmPassword);
        return new ResponseEntity<>(updatedUser, OK);

    }

    @GetMapping("/getLastUsers")
    public ResponseEntity<List<User>> getLastFiveUser()
    {
        return new ResponseEntity<>(iUserService.getLastFiveUsers(), OK);
    }


    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message)
    {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }


    private HttpHeaders getJwtHeader(UserPrincipal user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER, tokenProvider.generateJwtToken(user));
        return headers;
    }

    private void authenticate(String username, String password)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
