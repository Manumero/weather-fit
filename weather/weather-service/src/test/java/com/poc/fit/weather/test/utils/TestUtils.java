package com.poc.fit.weather.test.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UriTemplate;

import com.fit.services.weather.WeatherApplication;
import com.fit.services.weather.error.WeatherServiceException;
import com.fit.services.weather.model.Weather;
import com.fit.services.weather.service.utils.ConvertionUtils;
import com.fit.services.weather.service.utils.InvokeUtil;
import com.fit.services.weather.service.utils.ValidationUtils;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = { WeatherApplication.class })
@ActiveProfiles("test")
public class TestUtils {

	protected MockMvc mvc;
	private ResourceBundle rb;
	private String API_KEY;
	private String API_URL;
	private String country;
	private Double kelvin;
	private Double celcius;
	private Double fahrenheit;

	@Autowired
	WebApplicationContext webApplicationContext;
	

	@Autowired
	InvokeUtil webInvokeUtil;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		rb = ResourceBundle.getBundle("weather");
		API_KEY = rb.getString("key.weather");
		API_URL = rb.getString("url.weather");
		country = "London";
		kelvin = 292.9;
		celcius = 19.739999999999952;
		fahrenheit = 67.81999999999996;
	}

	@Test
	public void testInvoke() {

		URI url = new UriTemplate(API_URL).expand(country, API_KEY);
		Weather weather = null;
		
		try {
			weather = webInvokeUtil.invoke(url, Weather.class);
			
			assertNotNull(weather);
			assertEquals(weather.getCountryCode(), "GB");
		} catch (WeatherServiceException e) {
			
			e.printStackTrace();
		}

	}
	
	@Test
	public void validParametersNull() {
		
		List<String> parameters = new ArrayList<>();
		parameters.add(null);
		parameters.add(API_URL);
		parameters.add(country);
		
		assertEquals(ValidationUtils.validParameters(parameters), Boolean.FALSE);
	}
	
	@Test
	public void validParametersNotNull() {
		
		List<String> parameters = new ArrayList<>();
		parameters.add(API_KEY);
		parameters.add(API_URL);
		parameters.add(country);
		
		  assertEquals(ValidationUtils.validParameters(parameters), Boolean.TRUE);
	}
	
	@Test
	public void testGetCelcius() {
		
		Double celcius2 = ConvertionUtils.getCelcius(kelvin);
		assertEquals(celcius, celcius2);
		
	}
	
	@Test
	public void testGetFahrenheit() {
		
		Double fahrenheit2 = ConvertionUtils.getFahrenheit(kelvin);
		
		assertEquals(fahrenheit2, fahrenheit);
	}

}
