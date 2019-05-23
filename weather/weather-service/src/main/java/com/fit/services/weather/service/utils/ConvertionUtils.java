package com.fit.services.weather.service.utils;

import org.springframework.stereotype.Component;

@Component
public class ConvertionUtils {

	private static Double CELCIUS = 273.16D;
	
	
	public static Double getCelcius(Double kelvinTemp) {
		
		return (kelvinTemp - CELCIUS);
	}
	
	public static Double getFahrenheit(Double kelvinTemp) {
		
		return (((kelvinTemp - 273) * 9/5) + 32);
	}
}
