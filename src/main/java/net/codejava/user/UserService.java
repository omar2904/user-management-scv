package net.codejava.user;

import javax.transaction.Transactional;

import common.enums.AuthenticationTypeE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.Data.entity.User;
import user.Data.repo.UserRepository;

@Service
@Transactional
public class UserService {
//    @Autowired
//    private UserRepository repo;

    public void updateAuthenticationType(String username, String oauth2ClientName) {
//        User existUser = repo.getByUsername(username);
//
//        if (existUser == null) {
//            User newUser = new User();
//            newUser.setUsername(username);
//            newUser.setEnabled(true);
//
//            repo.save(newUser);
//        }
//        AuthenticationTypeE authType = AuthenticationTypeE.valueOf(oauth2ClientName.toUpperCase());
////        repo.updateAuthenticationType(username, authType);
//        System.out.println("Updated user's authentication type to " + authType);
    }
}
