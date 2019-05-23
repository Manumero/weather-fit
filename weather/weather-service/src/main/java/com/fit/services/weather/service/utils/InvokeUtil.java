package com.fit.services.weather.service.utils;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fit.services.weather.error.WeatherServiceException;
import com.fit.services.weather.handler.ErrorHandler;

@Component
public class InvokeUtil {

	private RestTemplate restTemplate;

	@Autowired
	public InvokeUtil(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public <T> T invoke(URI url, Class<T> responseType) throws WeatherServiceException {
		T weather = null;
		this.restTemplate.setErrorHandler(new ErrorHandler());
		RequestEntity<?> request = RequestEntity.get(url).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<T> exchange = this.restTemplate.exchange(request, responseType);
		weather = exchange.getBody();

		return weather;
	}
}
