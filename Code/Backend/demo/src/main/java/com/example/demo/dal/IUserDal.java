package com.example.demo.dal;

import com.example.demo.entity.User;

import java.util.List;

public interface IUserDal {
    User getUserById(Long id);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    User addUser(User user);
    void deleteUser(Long id);
    List<User> getTheLastFiveUsers();
}
