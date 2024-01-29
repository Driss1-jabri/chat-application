package com.example.messagerie_exam.controller;

import com.example.messagerie_exam.entities.Permission;
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

@Controller
public class LoginController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/login")
    public String login() {


        return "login";
    }
    @GetMapping("/home")
    public String home(Model model, HttpSession session) {

        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);

        return "home";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
        User user = userRepo.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            User u = userRepo.findByUsername(username);
            if(u.getPermission().equals(Permission.Normal)){
                session.setAttribute("user", u);
                return "redirect:/messagerie";
            }
            if(u.getPermission().equals(Permission.Moderateur)){
                session.setAttribute("user", u);
                return "redirect:/messagerie_moderateur";
            }
            else{
                session.setAttribute("user", u);
                return "redirect:/messagerie_admin";
            }

        } else {
            model.addAttribute("error", "Nom d'utilisateur ou mot de passe incorrect");
            return "login";
        }
    }
}
