package de.netos.rest;

import java.io.IOException;

import org.springframework.http.HttpStatus.Series;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResponseErrorHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.spring.annotation.SpringComponent;

import de.netos.error.ApiError;

@SpringComponent
public class RestResponseErrorHandler implements ResponseErrorHandler {

	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return response.getStatusCode().series() == Series.CLIENT_ERROR
				|| response.getStatusCode().series() == Series.SERVER_ERROR;
	}
	
	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		ApiError error = objectMapper.readValue(response.getBody(), ApiError.class);
		
		if (response.getStatusCode().series() == Series.CLIENT_ERROR) {
			throw new HttpClientErrorException(response.getStatusCode(), error.getError());
		} else if (response.getStatusCode().series() == Series.SERVER_ERROR) {
			throw new HttpServerErrorException(response.getStatusCode(), error.getError());
		}
	}
}
