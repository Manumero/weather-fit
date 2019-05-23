package com.fit.services.weather.error;
import java.io.IOException;

import org.springframework.http.HttpStatus;


public class WeatherException extends IOException {
 
	private static final long serialVersionUID = 1L;
	private HttpStatus statusCode;

    public WeatherException(String msg) {
        super(msg);
    }

    public WeatherException(HttpStatus statusCode, String msg) {
        super(msg);
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
}
