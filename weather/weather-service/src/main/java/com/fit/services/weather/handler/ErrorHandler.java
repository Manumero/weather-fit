package com.fit.services.weather.handler;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import com.fit.services.weather.error.WeatherException;

public class ErrorHandler implements ResponseErrorHandler {
    private static final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    private ResponseErrorHandler responseError = new DefaultResponseErrorHandler();

    public boolean hasError(ClientHttpResponse response) throws IOException {
        return responseError.hasError(response);
    }

    public void handleError(ClientHttpResponse response) throws IOException {
   		logger.error("Response error:", response.getStatusCode(), response.getStatusText());
        throw new WeatherException(response.getStatusCode(), response.getStatusText());
    }
}