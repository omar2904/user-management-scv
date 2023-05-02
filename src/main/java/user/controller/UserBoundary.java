package user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import user.modal.CredentialsDTO;
import user.modal.UserDTO;
import user.service.UserServiceT;

@Slf4j
@Controller
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserBoundary {

    private final UserServiceT userService;

    @Autowired
    public UserBoundary(UserServiceT userService) {
        this.userService = userService;
    }

    @GetMapping("/auth")
    public ResponseEntity<UserDTO> validateUserOrAdd(@RequestBody CredentialsDTO credentialsDTO){
        return ResponseEntity.ok(userService.authUser(credentialsDTO));
    }
}
