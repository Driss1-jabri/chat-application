package com.example.messagerie_exam.controller;

import com.example.messagerie_exam.entities.Permission;
import com.example.messagerie_exam.entities.Status;
import com.example.messagerie_exam.entities.User;
import com.example.messagerie_exam.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepo userRepository;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "inscription";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        user.setPermission(Permission.Normal);
        user.setStatus(Status.Online);
        user.setActif(true);
        user.setImg("https://bootdey.com/img/Content/avatar/avatar4.png");
        userRepository.save(user);
        return "redirect:/login";
    }
}
