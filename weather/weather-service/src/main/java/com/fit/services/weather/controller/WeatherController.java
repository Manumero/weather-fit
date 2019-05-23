package com.fit.services.weather.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fit.services.weather.model.ResultDTO;
import com.fit.services.weather.model.Weather;
import com.fit.services.weather.service.IWeatherService;

import io.swagger.annotations.ApiOperation;

@RestController
public class WeatherController {

	private static final Logger LOGGER = LoggerFactory.getLogger(WeatherController.class);
	
	@Autowired
	private IWeatherService weatherService;
	
	@ApiOperation(value = "Returns the weather from country selected")
	@GetMapping("/{country}")
	public ResponseEntity<ResultDTO<Weather>> findById(@PathVariable("country") String country)throws Exception {
		LOGGER.info("weather find: country={}", country);
		ResponseEntity<ResultDTO<Weather>> responseEntity = null;
		ResultDTO<Weather> resultDTO = new ResultDTO<>();
		try {
			Weather weather =  weatherService.getWeather(country);
			resultDTO.setDetails(weather);
			resultDTO.setMessage("the service was called ok");
			resultDTO.setStatus("ok");
			responseEntity = new ResponseEntity<ResultDTO<Weather>>(resultDTO,HttpStatus.OK);
		}catch (Exception e) {
			LOGGER.error("Internal Server Error");
			resultDTO = new ResultDTO<>("Inernale server error", "Error while get weather", null);
			responseEntity = new ResponseEntity<ResultDTO<Weather>>(resultDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
}

