package user.service;

import common.enums.AuthenticationTypeE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.Data.entity.User;
import user.modal.CredentialsDTO;
import user.modal.UserDTO;
import user.Data.repo.UserRepository;

@Service
public class UserServiceT {
    private final UserRepository userRepo;

    @Autowired
    public UserServiceT(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    public UserDTO authUser(CredentialsDTO credentialsDTO){
        User user = userRepo.getByEmail(credentialsDTO.email);

        if(user == null){
            userRepo.save(new User(
                    "testUserName",credentialsDTO.email, credentialsDTO.password,false, AuthenticationTypeE.BASEFIT
            ));
        }

        return new UserDTO(user.getUsername(), user.getEmail());
    }
}
