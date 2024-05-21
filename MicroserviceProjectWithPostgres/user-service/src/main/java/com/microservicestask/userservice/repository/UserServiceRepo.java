package com.microservicestask.userservice.repository;

import com.microservicestask.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserServiceRepo extends JpaRepository<User,Integer> {

    public Optional<User> findByEmailId(String emailId);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmailId(String emailId);
}
