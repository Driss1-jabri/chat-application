package com.example.messagerie_exam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor @NoArgsConstructor
public class MessageDTO {
    private String content;
    private LocalDateTime timestamp;
    private String senderUsername;
    private  String img;


}