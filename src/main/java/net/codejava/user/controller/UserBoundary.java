package net.codejava.user.controller;

import lombok.extern.slf4j.Slf4j;
import net.codejava.common.enums.AuthenticationTypeE;
import net.codejava.user.Data.entity.User;
import net.codejava.user.modal.CredentialsDTO;
import net.codejava.user.modal.UserDTO;
import net.codejava.user.service.UserServiceT;
import net.codejava.verifcation.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping
public class UserBoundary {

    private final UserServiceT userService;
    private final CredentialsDTO credentialsDTO = new CredentialsDTO();

    private final UserDTO userDTO = new UserDTO();
    @Autowired
    public UserBoundary(UserServiceT userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam("fullname") String fullname,
                        @RequestParam("email") String email,
                        @RequestParam("pass") String password,
                        Model model) {

        credentialsDTO.setEmail(email);
        credentialsDTO.setPassword(password);
        userDTO.setFullName(fullname);
        userDTO.setAuthType(AuthenticationTypeE.valueOf("Basefit"));
        UserDTO isValidUser = userService.UserRegsiter(credentialsDTO, userDTO);
        if (isValidUser.getFullName() != null) {

            return "redirect:/login";
        } else {
            model.addAttribute("error", "User Already exists");
            return "register";
        }
    }
}
