package com.fit.services.weather.service;

import com.fit.services.weather.error.WeatherServiceException;
import com.fit.services.weather.model.Weather;

public interface IWeatherService {

	/**
	 * Service to get wather from specif country
	 * @param country
	 * @return
	 */
	public Weather getWeather(String country)throws WeatherServiceException;
}
