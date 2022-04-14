package com.example.demo.dal.implementation;


import com.example.demo.dal.IUserDal;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDalJPA implements IUserDal {

    @Autowired
    UserDao userRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getTheLastFiveUsers() {
        List<User> lastFive = new ArrayList<>();
        int i;
        for(i = userRepository.findAll().size() - 1; i >= userRepository.findAll().size() - 5; i--)
        {
            lastFive.add(userRepository.findAll().get(i));
        }
        return lastFive;
    }
}
