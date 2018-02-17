package com.example.security.auditing.securityauditing.controller;

import com.example.security.auditing.securityauditing.domain.User;
import com.example.security.auditing.securityauditing.repository.UserRepository;
import com.example.security.auditing.securityauditing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRerpository;

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute @Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "register";
        }
        else{
            userService.save(user);
            return "redirect:/";
        }
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

}
