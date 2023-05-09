package net.codejava.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.codejava.user.modal.CredentialsDTO;
import net.codejava.user.modal.UserDTO;
import net.codejava.user.service.UserServiceT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


	private final UserServiceT userService;

	private final CredentialsDTO credentialsDTO;
	private final UserDTO userDTO;
	@Autowired
	public DatabaseLoginSuccessHandler(UserServiceT userService, CredentialsDTO credentialsDTO, UserDTO userDTO) {
		this.userService = userService;
		this.credentialsDTO = credentialsDTO;
		this.userDTO = userDTO;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();



		credentialsDTO.setEmail(userDetails.getUsername());
		credentialsDTO.setPassword(userDetails.getPassword());

		UserDTO user =  userService.UserLogin(credentialsDTO);

		userDTO.setFullName(user.getFullName());
		userDTO.setHeight(user.getHeight());
		userDTO.setWeight(user.getWeight());
		userDTO.setAge(user.getAge());
		userDTO.setRole(user.getRole());

		super.onAuthenticationSuccess(request, response, authentication);
	}

}
