package com.example.messagerie_exam.repositories;

import com.example.messagerie_exam.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);


    void deleteByUsername(String username);
}