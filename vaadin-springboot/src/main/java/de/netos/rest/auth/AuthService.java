package de.netos.rest.auth;

import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import de.netos.auth.login.LoginRequest;
import de.netos.auth.login.LoginResponse;
import de.netos.auth.signup.SignUpRequest;
import de.netos.rest.RestResponseErrorHandler;

@Service
public class AuthService {
	
	@Autowired
	private RestTemplateBuilder templateBuilder;
	
	@Autowired
	private RestResponseErrorHandler errorHandler;
	
	private RestTemplate template;
	
	@PostConstruct
	private void init() {
		template = templateBuilder
			.errorHandler(errorHandler)
			.build();
	}
	
	/**
	 * 
	 * 
	 * @param loginRequest
	 * @return Response to the login request.
	 * 
	 * @throws HttpClientErrorException - if there was an error that was forced by the client. e.g. bad credentials for a login or an unknown username.
	 * @throws HttpServerErrorException - if there was an internal error in the server.
	 * @see LoginResponse
	 */
	public LoginResponse login(LoginRequest loginRequest) {
		URI build = null;
		try {
			build = new URIBuilder().setScheme("http").setHost("localhost").setPort(8090).setPath("/auth/login/").build();
		} catch (URISyntaxException e) {
			throw new IllegalStateException(); 
		}
		
		HttpEntity<LoginRequest> entity = new HttpEntity<>(loginRequest);
		ResponseEntity<LoginResponse> response = template.postForEntity(build, entity, LoginResponse.class);
		
		return response.getBody();
	}
	
	public void register(SignUpRequest signUpRequest) {
		URI build = null;
		try {
			build = new URIBuilder().setScheme("http").setHost("localhost").setPort(8090).setPath("/auth/register/").build();
		} catch (URISyntaxException e) {
			throw new IllegalStateException(); 
		}
		
		HttpEntity<SignUpRequest> entity = new HttpEntity<>(signUpRequest);
		template.postForEntity(build, entity, String.class);
	}
}
