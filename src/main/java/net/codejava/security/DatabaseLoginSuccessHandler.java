package net.codejava.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.codejava.common.enums.AuthenticationTypeE;
import net.codejava.user.modal.CredentialsDTO;
import net.codejava.user.modal.UserDTO;
import net.codejava.user.service.UserServiceT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private final UserServiceT userService;

	private final CredentialsDTO credentialsDTO = new CredentialsDTO();

	public DatabaseLoginSuccessHandler(UserServiceT userService) {
		this.userService = userService;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();



		credentialsDTO.setEmail(userDetails.getUsername());
		credentialsDTO.setPassword(userDetails.getPassword());

		userService.UserLogin(credentialsDTO);

		super.onAuthenticationSuccess(request, response, authentication);
	}

}
