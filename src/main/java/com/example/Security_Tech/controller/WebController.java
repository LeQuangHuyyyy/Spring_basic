package com.example.Security_Tech.controller;

import com.example.Security_Tech.repository.UserRepository;
import com.example.Security_Tech.service.UserService;
import com.example.Security_Tech.userDTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class WebController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = {"/register"})
    public String register(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "register";
    }

    @PostMapping("/register")
    public String SaveUser(@ModelAttribute("user") UserDTO userDTO, @RequestParam("ConfirmPassword") String Confirm, Model model) {
        if (!userDTO.getPassword().equals(Confirm)) {
            model.addAttribute("error", "Invalid confirm password");
        } else {
            if (userRepository.findByEmail(userDTO.getEmail()) != null) {
                model.addAttribute("error", "Email already exists");
            } else {
                userDTO.setRole("USER");
                userService.save(userDTO);
                model.addAttribute("message", "Register successfully");
            }
        }
        return "register";
    }

    @GetMapping(value = {"LoginPage", "", "/"})
    public String login() {
        return "LoginPage";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/LoginPage";
    }

    @GetMapping("user-page")
    public String userPage(Model model) {
        return "user-page";
    }

    @PostMapping("success")
    public String adminPage(Model model) {
        return "success";
    }

}
