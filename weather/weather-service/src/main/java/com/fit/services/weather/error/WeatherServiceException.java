package com.fit.services.weather.error;

public class WeatherServiceException extends Exception{

	private static final long serialVersionUID = 1L;

	public WeatherServiceException(String msg) {
        super(msg);
    }
}
