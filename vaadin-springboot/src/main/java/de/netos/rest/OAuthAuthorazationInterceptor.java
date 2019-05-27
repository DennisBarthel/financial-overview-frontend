package de.netos.rest;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class OAuthAuthorazationInterceptor implements ClientHttpRequestInterceptor {

	private final String accessToken;
	
	public OAuthAuthorazationInterceptor(String accessToken) {
		this.accessToken = accessToken;
	}
	
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		HttpHeaders headers = request.getHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {			
			headers.setBearerAuth(accessToken);
		}
		return execution.execute(request, body);
	}

}
