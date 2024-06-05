package com.WebApp.WeatherApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.WebApp.WeatherApp.Pojo.Geonames;
import com.WebApp.WeatherApp.Request.CityDetailsReq;
import com.WebApp.WeatherApp.Request.CityDetailsResponse;
import com.WebApp.WeatherApp.Request.WeatherDataReq;
import com.WebApp.WeatherApp.Responses.GeonamesRes;
import com.WebApp.WeatherApp.Responses.HeadRes;
import com.WebApp.WeatherApp.Responses.WeatherDataRes;
import com.WebApp.WeatherApp.Services.WeatherService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/weatherApp")

public class WeatherController {
	private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);
	@Autowired
	private final WeatherService weatherService;

	public WeatherController(WeatherService weatherService) {
		this.weatherService = weatherService;

	}

	@PostMapping("/getWeather")
	@CrossOrigin(origins = "http://localhost:4200")
	public WeatherDataRes getWeather(@RequestBody WeatherDataReq req) {
		if (req.getCity() == null || req.getCity().isEmpty()) {
			WeatherDataRes weatherError = new WeatherDataRes();
			HeadRes error = new HeadRes();
			error.setCode("404");
			error.setMessage("City Name is Missing or Null");
			weatherError.setHead(error);
			return weatherError;
		}

		WeatherDataRes weatherData = new WeatherDataRes();
		weatherData = weatherService.getWeatherData(req);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String responseBody = objectMapper.writeValueAsString(weatherData);
			logger.info("Response Body: {}", responseBody);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return weatherData;
	}

	@PostMapping("/getCity")
	public GeonamesRes getcityDetails(@RequestBody CityDetailsReq req) {		
		GeonamesRes response = new GeonamesRes();
		response = weatherService.getCityCityDetails(req);
		return response;

	}
}
