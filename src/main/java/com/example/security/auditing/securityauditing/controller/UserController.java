package com.example.security.auditing.securityauditing.controller;

import com.example.security.auditing.securityauditing.domain.UserDto;
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
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute @Valid UserDto userDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "register";
        }
        else{
            userService.save(userDto);
            return "redirect:/";
        }
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

}
