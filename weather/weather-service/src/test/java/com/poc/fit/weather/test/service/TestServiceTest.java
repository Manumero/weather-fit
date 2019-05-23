package com.poc.fit.weather.test.service;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fit.services.weather.WeatherApplication;
import com.fit.services.weather.model.Weather;
import com.fit.services.weather.service.IWeatherService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { WeatherApplication.class })
public class TestServiceTest {

	@Autowired
	IWeatherService testService;

	@Test
	public void testGetMessage() {

		final SoftAssertions soft = new SoftAssertions();
		Weather weather;
		try {
			weather = testService.getWeather("London");

			soft.assertThat(weather).isNotNull();
			soft.assertThat(weather.getName()).isNotNull();
			soft.assertThat(weather.getName()).isNotBlank();
			soft.assertAll();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}