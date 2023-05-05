package net.codejava.security.oauth;

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
public class OAuthLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private final UserServiceT userService;

	private final CredentialsDTO credentialsDTO = new CredentialsDTO();
	private final UserDTO userDTO = new UserDTO();

	public OAuthLoginSuccessHandler(UserServiceT userService) {
		this.userService = userService;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		CustomOAuth2User oauth2User = (CustomOAuth2User) authentication.getPrincipal();

		credentialsDTO.setEmail(oauth2User.getEmail());
		credentialsDTO.setPassword("");
		userDTO.setFullName(oauth2User.getName());
		userDTO.setAuthType(AuthenticationTypeE.valueOf(oauth2User.getOauth2ClientName()));

		userService.FacebookOrGoogle(credentialsDTO, userDTO);
		
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
