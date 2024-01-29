package com.example.messagerie_exam.repositories;

import com.example.messagerie_exam.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long> {

}
