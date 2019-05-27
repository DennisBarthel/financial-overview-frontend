package de.netos.rest.auth;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import de.netos.auth.login.LoginRequest;
import de.netos.rest.OAuthAuthorazationInterceptor;

public class AuthService {
	
	public void login(String email, String password) throws URISyntaxException {
		URI build = new URIBuilder().setScheme("http").setHost("localhost").setPort(8090).setPath("/v1/account/").build();
		
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setEmail(email);
		loginRequest.setPassword(password);
		
		HttpEntity<LoginRequest> entity = new HttpEntity<>(loginRequest);
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new OAuthAuthorazationInterceptor("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZW5uaXMuYmFydGhlbC44M0BnbWFpbC5jb20iLCJleHAiOjE1NTg2NTc3NDQsImlhdCI6MTU1ODY1Njg0NH0.t8L9jiAWXyOPu31qpqaQwTHaWxqsUSxI7GVjM3KvRqbMIVj9272-_U6emG1D6z4HRII2NbJtwtPKH1oYUaX4qQ"));
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(build, String.class);
		
		System.out.println();
	}
	
	public static void main(String[] args) throws URISyntaxException {
		AuthService service = new AuthService();
		service.login("", "");
	}
}
