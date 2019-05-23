package com.weather.fit.front.model;
import java.io.Serializable;

public class Weather implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name;
	private long timestamp;
	private double temperature;
	private Integer weatherId;
	private String weatherIcon;
	private String weatherMain;
	private String weatherDescription;
	private String countryCode;
	private long sunrise;
	private long sunset;
	private Double celcius;
	private Double fahrenheit;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public Integer getWeatherId() {
		return weatherId;
	}
	public void setWeatherId(Integer weatherId) {
		this.weatherId = weatherId;
	}
	public String getWeatherIcon() {
		return weatherIcon;
	}
	public void setWeatherIcon(String weatherIcon) {
		this.weatherIcon = weatherIcon;
	}
	public String getWeatherMain() {
		return weatherMain;
	}
	public void setWeatherMain(String weatherMain) {
		this.weatherMain = weatherMain;
	}
	public String getWeatherDescription() {
		return weatherDescription;
	}
	public void setWeatherDescription(String weatherDescription) {
		this.weatherDescription = weatherDescription;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public long getSunrise() {
		return sunrise;
	}
	public void setSunrise(long sunrise) {
		this.sunrise = sunrise;
	}
	public long getSunset() {
		return sunset;
	}
	public void setSunset(long sunset) {
		this.sunset = sunset;
	}
	public Double getCelcius() {
		return celcius;
	}
	public void setCelcius(Double celcius) {
		this.celcius = celcius;
	}
	public Double getFahrenheit() {
		return fahrenheit;
	}
	public void setFahrenheit(Double fahrenheit) {
		this.fahrenheit = fahrenheit;
	}
}
