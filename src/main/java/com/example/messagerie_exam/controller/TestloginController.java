package com.example.messagerie_exam.controller;

import com.example.messagerie_exam.entities.User;
import com.example.messagerie_exam.repositories.UserRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class TestloginController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/login2")
    public String login() {
        return "login2";
    }
    @GetMapping("/welcome")
    public String welcome(Model model, HttpSession session) {

        User user = (User) session.getAttribute("user");


        model.addAttribute("user", user);

        return "welcome";
    }

    @PostMapping("/login2")

    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        User user = userRepo.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            User u = userRepo.findByUsername(username);
            session.setAttribute("user", u);
            return "redirect:/welcome";
        } else {
            model.addAttribute("error", true);
            return "login";
        }
    }

}
