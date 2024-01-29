package com.example.messagerie_exam.service;

import com.example.messagerie_exam.dto.MessageDTO;

import java.util.List;

public interface MessageService {
    List<MessageDTO> getAllMessages();
}