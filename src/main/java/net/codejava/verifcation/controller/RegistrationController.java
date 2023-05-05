package net.codejava.verifcation.controller;

import lombok.extern.slf4j.Slf4j;
import net.codejava.user.Data.entity.User;
import net.codejava.user.Data.repo.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Controller
@RequestMapping
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/verify")
    public String verifyAccount(@RequestParam String token) {
        User user = userRepository.findByToken(token);

        if (user == null) {
            return "redirect:/error";
        }

        user.setVerified(true);
        userRepository.save(user);

        return "redirect:/login";

    }
}