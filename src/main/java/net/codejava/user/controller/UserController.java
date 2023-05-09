package net.codejava.user.controller;

import lombok.extern.slf4j.Slf4j;
import net.codejava.common.enums.AuthenticationTypeE;
import net.codejava.common.enums.RoleTypeE;
import net.codejava.user.modal.CredentialsDTO;
import net.codejava.user.modal.UserDTO;
import net.codejava.user.service.UserServiceT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping
public class UserController {

    private final UserServiceT userService;
    private final CredentialsDTO credentialsDTO;

    private final UserDTO userDTO;
    @Autowired
    public UserController(UserServiceT userService, CredentialsDTO credentialsDTO, UserDTO userDTO) {
        this.userService = userService;
        this.credentialsDTO = credentialsDTO;
        this.userDTO = userDTO;
    }

    @GetMapping("/")
    public String index(Model model) {
        String r = String.valueOf(userDTO.getRole());
        model.addAttribute("role", r);
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/profile")
    public String profile(Model model){
        model.addAttribute("fullname", userDTO.getFullName());
        model.addAttribute("email", credentialsDTO.getEmail());
        model.addAttribute("age", userDTO.getAge());
        model.addAttribute("weight", userDTO.getWeight());
        model.addAttribute("height", userDTO.getHeight());
        return "profile";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam("fullname") String fullname,
                        @RequestParam("email") String email,
                        @RequestParam("pass") String password,
                           @RequestParam("role") String role,
                        Model model) {

        credentialsDTO.setEmail(email);
        credentialsDTO.setPassword(password);
        userDTO.setFullName(fullname);
        userDTO.setRole(RoleTypeE.valueOf(role));
        userDTO.setAuthType(AuthenticationTypeE.valueOf("Basefit"));
        UserDTO isValidUser = userService.UserRegsiter(credentialsDTO, userDTO);


        if (isValidUser.getFullName() != null) {
            return "redirect:/login";
        } else {
            model.addAttribute("error", "User Already exists");
            return "register";
        }
    }

    @PostMapping("/profile")
    public String profile(@RequestParam("fullname") String fullname,
                          @RequestParam("email") String email,
                          @RequestParam("age") int age,
                           @RequestParam("weight") int weight,
                           @RequestParam("height") int height,
                           Model model) {

        credentialsDTO.setEmail(email);
        userDTO.setFullName(fullname);
        userDTO.setAge(age);
        userDTO.setHeight(height);
        userDTO.setWeight(weight);
        UserDTO UserProfile = userService.Profile(credentialsDTO, userDTO);


        if (UserProfile.isProfileCompleted()) {

            return "redirect:/";
        } else {
            model.addAttribute("error", "Please Complete your Profile");
            return "profile";
        }
    }
}
