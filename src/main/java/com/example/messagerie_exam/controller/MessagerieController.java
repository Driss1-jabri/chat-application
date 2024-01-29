package com.example.messagerie_exam.controller;

import com.example.messagerie_exam.dto.MessageDTO;
import com.example.messagerie_exam.entities.Message;
import com.example.messagerie_exam.entities.Permission;
import com.example.messagerie_exam.entities.Status;
import com.example.messagerie_exam.entities.User;
import com.example.messagerie_exam.repositories.MessageRepo;
import com.example.messagerie_exam.repositories.UserRepo;
import com.example.messagerie_exam.service.MessageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class MessagerieController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private MessageService messageService;

    @GetMapping("/messagerie")
    public String messagerie(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);


        List<User> allUsers = userRepo.findAll();


        model.addAttribute("users", allUsers);

        List<MessageDTO> messages = messageService.getAllMessages();
        model.addAttribute("messages", messages);

        return "messagerie";
    }
    @GetMapping("messagerie_admin")
    public String messagerie_admin(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);


        List<User> allUsers = userRepo.findAll();


        model.addAttribute("users", allUsers);

        List<MessageDTO> messages = messageService.getAllMessages();
        model.addAttribute("messages", messages);
        return "messagerie_admin";
    }
    @GetMapping("/messagerie_moderateur")
    public String messagerie_moderateur(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);


        List<User> allUsers = userRepo.findAll();


        model.addAttribute("users", allUsers);

        List<MessageDTO> messages = messageService.getAllMessages();
        model.addAttribute("messages", messages);
        return "messagerie_moderateur";
    }
    @PostMapping("/sendMessage")
    public ResponseEntity<String> sendMessage(@RequestParam String content, HttpSession session) {
        User user = (User) session.getAttribute("user");


        Message message = new Message();
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());
        message.setSender(user);

        messageRepo.save(message);

        return ResponseEntity.ok("Message ajouté avec succès");
    }
    @PostMapping("/updateStatus")
    public ResponseEntity<String> updateStatus(@RequestParam String status, HttpSession session) {
        User user = (User) session.getAttribute("user");


        user.setStatus(Status.valueOf(status));
        userRepo.save(user);

        return ResponseEntity.ok("Statut mis à jour avec succès");
    }
    @PostMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestParam("username") String username) {
        User user = userRepo.findByUsername(username);
        userRepo.delete(user);
        return ResponseEntity.ok("Utilisateur supprimé avec succès");
    }
    @PostMapping("/updateUserStatus")
    @ResponseBody
    public ResponseEntity<String> updateUserStatus(@RequestParam Long userId) {
        // Récupérer le utilisateur par son ID
        Optional<User> optionalUser = userRepo.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // Mettez à jour le statut du utilisateur
            user.setActif(false);
            userRepo.save(user);

            return ResponseEntity.ok("Statut du utilisateur mis à jour avec succès.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé.");
        }
    }
    @GetMapping("/deactivateUser/{userId}")
    public String deactivateUser(@PathVariable Long userId) {
        Optional<User> optionalUser = userRepo.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setActif(false);
            userRepo.save(user);
        } else {

        }

        return "redirect:/messagerie_moderateur";
    }




}
