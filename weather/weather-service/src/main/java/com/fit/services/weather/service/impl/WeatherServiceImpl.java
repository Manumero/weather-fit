package com.fit.services.weather.service.impl;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriTemplate;

import com.fit.services.weather.error.WeatherServiceException;
import com.fit.services.weather.model.Weather;
import com.fit.services.weather.service.IWeatherService;
import com.fit.services.weather.service.utils.InvokeUtil;
import com.fit.services.weather.service.utils.ValidationUtils;

@Service
public class WeatherServiceImpl implements IWeatherService{

	private static ResourceBundle rb = ResourceBundle.getBundle("weather");

	private static final Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);
	private static final String API_KEY = rb.getString("key.weather");
	private static final String API_URL = rb.getString("url.weather");

	
	private InvokeUtil webInvoke;
	private List<String> values;
	
	@Autowired
	public WeatherServiceImpl(InvokeUtil webInvoke) {
		this.webInvoke = webInvoke;
		values = new ArrayList<>();
		values.add(API_KEY);
		values.add(API_URL);
	}

	@Override
	public Weather getWeather(String country) throws WeatherServiceException {
		logger.debug("Country selected", country);
		Weather weather = null;
		 values.add(country);
		
		if(ValidationUtils.validParameters(values)) {
			URI url = new UriTemplate(API_URL).expand(country, API_KEY);
			weather = webInvoke.invoke(url, Weather.class);
		}
		return weather;
	}
}
