package com.microservicestask.userservice.serviceImpl;

import com.microservicestask.userservice.entity.Patient;
import com.microservicestask.userservice.entity.User;
import com.microservicestask.userservice.repository.UserServiceRepo;
import com.microservicestask.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserServiceRepo userServiceRepo;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userServiceRepo.save(user);
    }


    public User getOneUser(int id) {
        User user = userServiceRepo.findById(id).get();
//        Patient patient = restTemplate.getForObject("http://localhost:8082/patient/" + user.getUserId(), Patient.class);
//        user.setPatient(patient);
        return user;
    }

    @Override
    public List<User> getAllUser() {
        return userServiceRepo.findAll();
    }

    @Override
    public User updateUser(int id, User updatedUser) {
        return null;
    }

//    @Override
//    public User updateUser(int id, User updatedUser) {
//        User user=userServiceRepo.findById(id).get();
//        if(updatedUser.getFirstName()!=null)
//        {
//            user.setFirstName(updatedUser.getFirstName());
//        }
//        if(updatedUser.getLastName()!=null)
//        {
//            user.setLastName(updatedUser.getLastName());
//        }
//        if(updatedUser.getEmailId()!=null)
//        {
//            user.setEmailId(updatedUser.getEmailId());
//        }
//        if(updatedUser.getPhoneNo()!= null)
//        {
//            user.setPhoneNo(updatedUser.getPhoneNo());
//        }
//        return userServiceRepo.save(user);
//    }

    @Override
    public void deleteUser(int id) {
        userServiceRepo.deleteById(id);
    }

    @Override
    public List<User> getUserInfo() {
         return userServiceRepo.findAll();
    }

    @Override
    public List<User> getAdminInfo() {
        return userServiceRepo.findAll();
    }


}
