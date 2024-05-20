package com.microservicestask.userservice.service;

import com.microservicestask.userservice.entity.User;

import java.util.List;

public interface UserService {

    public User createUser(User user);


    public User getOneUser(int id);

//    public User getUser(int id);


    public List<User> getAllUser();

    public User updateUser(int id, User updatedUser);

    public void deleteUser(int id);

    public List<User> getUserInfo();

    public List<User> getAdminInfo();

}
