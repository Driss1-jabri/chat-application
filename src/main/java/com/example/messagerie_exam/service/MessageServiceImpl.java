package com.example.messagerie_exam.service;


import com.example.messagerie_exam.dto.MessageDTO;
import com.example.messagerie_exam.entities.Message;
import com.example.messagerie_exam.repositories.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepo messageRepo;

    @Override
    public List<MessageDTO> getAllMessages() {
        List<Message> messages = messageRepo.findAll();
        return messages.stream()
                .map(message -> new MessageDTO(
                        message.getContent(),
                        message.getTimestamp(),
                        message.getSender().getUsername(),
                        message.getSender().getImg()

                ))
                .collect(Collectors.toList());
    }
}
