package com.poc.fit.weather.test.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ActiveProfiles("test")
public class WeatherControllerTest extends ControllerTest {

	Logger log = LoggerFactory.getLogger(WeatherControllerTest.class);
	private static final int OK = 200;
	private static final int NOT_FOUND = 404;

	private final static String URL = "http://localhost:8092/London";
	private final static String URL_FAIL = "http://localhost:8092/";
	

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void testFindByCountry() {

		try {
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON_VALUE))
					.andReturn();

			int status = mvcResult.getResponse().getStatus();

			assertEquals(OK, status);
			String content = mvcResult.getResponse().getContentAsString();
			log.info("Contenido --->" + content);
			assertTrue(content.contains("the service was called ok"));
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	@Test
	public void testNotFound() {

		try {
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(URL_FAIL).accept(MediaType.APPLICATION_JSON_VALUE))
					.andReturn();

			int status = mvcResult.getResponse().getStatus();

			assertEquals(NOT_FOUND, status);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
}
