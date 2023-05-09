package net.codejava.verifcation.controller;

import lombok.extern.slf4j.Slf4j;
import net.codejava.common.enums.RoleTypeE;
import net.codejava.user.Data.entity.User;
import net.codejava.user.Data.repo.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Controller
@RequestMapping
public class RegistrationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/verify")
    public String verifyAccount(@RequestParam String token) {
        User user = userRepository.findByToken(token);

        if (user == null || user.isVerified()) {
            return "redirect:/error";
        }

        user.setVerified(true);
        userRepository.save(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        if (user.getRole() == RoleTypeE.valueOf("coach"))
            return "redirect:/";

        return "redirect:/profile";

    }
}