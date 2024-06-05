package com.WebApp.WeatherApp.Constants;

import org.springframework.context.annotation.Configuration;

@Configuration
public class WeatherConstants {
	public static final String VALIDATION_URL = "http://api.geonames.org/searchJSON";
	public static final String VALIDATION_USERNAME = "nidhin1998";
	public static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
	public static final String API_ID = "&appid=";
	public static final String ICON_URL = "http://openweathermap.org/img/wn/";
	public static final String ICON_CODE = "@2x.png";

}
