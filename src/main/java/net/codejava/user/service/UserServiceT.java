package net.codejava.user.service;

import net.codejava.user.Data.entity.User;
import net.codejava.user.Data.repo.UserRepository;
import net.codejava.user.modal.CredentialsDTO;
import net.codejava.user.modal.UserDTO;
import net.codejava.verifcation.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceT {
    private final UserRepository userRepo;

    @Autowired
    private EmailSenderService senderService;

    @Autowired
    public UserServiceT(UserRepository userRepo, JavaMailSender javaMailSender){
        this.userRepo = userRepo;
    }

    public UserDTO UserLogin(CredentialsDTO credentialsDTO)
    {
        User user = userRepo.findByEmailAndPassword(credentialsDTO.email, credentialsDTO.getPassword());

        if(user != null)
            return new UserDTO(user.getFullname(), user.getAuthType(),
                    user.isVerified(), user.getToken(), user.isProfileCompleted(), user.getAge(), user.getHeight(), user.getWeight(), user.getRole());
        else
            return new UserDTO();
    }

    public UserDTO UserRegsiter(CredentialsDTO credentialsDTO, UserDTO userDTO){
        User user = userRepo.findByEmailAndPassword(credentialsDTO.email, credentialsDTO.getPassword());

        if (user == null)
        {
            String token = String.valueOf(UUID.randomUUID());
            senderService.sendSimpleEmail(credentialsDTO.getEmail(),
                    "Email Verification",
                    "Please verify your email address.\n" +
                            "\n" +
                            "Use the following link to confirm your email address:\n" + "http://localhost:8080/verify?token=" + token);

            user = new User(userDTO.getFullName(), credentialsDTO.getEmail(), passwordEncoder().encode(credentialsDTO.getPassword())
                    ,false, token, false, 0, 0, 0, userDTO.getAuthType(), userDTO.getRole());
            userRepo.save(user);
            return new UserDTO(user.getFullname(), user.getAuthType(),
                    user.isVerified(), user.getToken(), user.isProfileCompleted(), user.getAge(), user.getHeight(), user.getWeight(), user.getRole());

        }

        else
        {
            return new UserDTO();
        }


    }


    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void FacebookOrGoogle(CredentialsDTO credentialsDTO, UserDTO userDTO){
        User user = userRepo.getByEmail(credentialsDTO.email);

        if (user == null)
        {
            userRepo.save(new User(userDTO.getFullName(), credentialsDTO.getEmail(), credentialsDTO.getPassword(),true, "",false, 0, 0, 0, userDTO.getAuthType(), userDTO.getRole()));
        }

    }

    public UserDTO Profile(CredentialsDTO credentialsDTO, UserDTO userDTO) {
        User user = userRepo.getByEmail(credentialsDTO.email);

        if(user != null) {
            user.setAge(userDTO.getAge());
            user.setHeight(userDTO.getHeight());
            user.setWeight(userDTO.getWeight());
            user.setProfileCompleted(true);

            userRepo.save(user);

            return new UserDTO(user.getFullname(), user.getAuthType(),
                    user.isVerified(), user.getToken(), user.isProfileCompleted(), user.getAge(), user.getHeight(), user.getWeight(), user.getRole());
        }
        else
            return new UserDTO();
    }
}
