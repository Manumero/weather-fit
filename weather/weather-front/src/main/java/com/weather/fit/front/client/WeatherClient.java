package com.weather.fit.front.client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.weather.fit.front.exception.FrontException;
import com.weather.fit.front.model.ResultDTO;
import com.weather.fit.front.model.Weather;


public class WeatherClient {

	private static Logger logger = Logger.getLogger(WeatherClient.class);
	private static ResourceBundle rb = ResourceBundle.getBundle("frontweather");
	private static final String BASE_URL = rb.getString("front.url.service");
	
	private WeatherClient() {
		throw new IllegalStateException("WeatherClient class");
	}

	public static ResultDTO<Weather> getWeatherInfo(String cityName) throws Exception {
		String jsonResult = getWeatherJsonString(cityName);
		return toEntity(jsonResult);
	}

	// Covert json string to class object
	@SuppressWarnings("unchecked")
	private static ResultDTO<Weather> toEntity(String jsonString) {
		try {
			Gson gson = new GsonBuilder().create();
			ResultDTO<Weather> weatherInfo = gson.fromJson(jsonString, ResultDTO.class);
			JsonObject jsonObject = gson.toJsonTree(weatherInfo.getDetails()).getAsJsonObject();
			Weather weater = gson.fromJson(jsonObject, Weather.class);
			weatherInfo.setDetails(weater);
			return weatherInfo;
		} catch (JsonSyntaxException ex) {
			throw new JsonSyntaxException(ex.getMessage());
		}
	}

	// Get the weather of the specific city
	private static String getWeatherJsonString(String cityName) throws Exception {

		String baiduUrl = BASE_URL.concat("London");
		try {
			if (Optional.ofNullable(cityName).isPresent())
				baiduUrl = BASE_URL.concat(URLEncoder.encode(cityName, "utf-8"));
		} catch (UnsupportedEncodingException e1) {
			logger.error("UnsupportedEncodingException",e1);
		}

		StringBuilder strBuf = new StringBuilder();

		HttpURLConnection conn = null;
		BufferedReader reader = null;
		try {
			URL url = new URL(baiduUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new FrontException("HTTP GET Request Failed with Error code : " + conn.getResponseCode());
			}

			// Read the content from the defined connection
			// Using IO Stream with Buffer raise highly the efficiency of IO
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String output = null;
			while ((output = reader.readLine()) != null)
				strBuf.append(output);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					logger.error("Error", e);
				}
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return strBuf.toString();
	}
}