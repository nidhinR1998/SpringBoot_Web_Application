package com.WebApp.WeatherApp.Services;

import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.WebApp.WeatherApp.Constants.WeatherConstants;
import com.WebApp.WeatherApp.Pojo.Coord;
import com.WebApp.WeatherApp.Pojo.Geonames;
import com.WebApp.WeatherApp.Pojo.Main2;
import com.WebApp.WeatherApp.Pojo.Sys2;
import com.WebApp.WeatherApp.Pojo.Weather;
import com.WebApp.WeatherApp.Request.CityDetailsReq;
import com.WebApp.WeatherApp.Request.CityDetailsResponse;
import com.WebApp.WeatherApp.Request.WeatherDataReq;
import com.WebApp.WeatherApp.Responses.GeonamesRes;
import com.WebApp.WeatherApp.Responses.HeadRes;
import com.WebApp.WeatherApp.Responses.WeatherDataRes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherServiceImpl implements WeatherService {
	private static final Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);
	@Value("${openweathermap.apiKey}")
	private String apiKey;
	@Autowired
	private final RestTemplate restTemplate;
	@Autowired
	private final WeatherConstants constant;

	public WeatherServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
		this.constant = new WeatherConstants();
	}

	@Override
	public WeatherDataRes getWeatherData(WeatherDataReq req) {
		String city1 = req.getCity();
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(constant.VALIDATION_URL)
				.queryParam("username", constant.VALIDATION_USERNAME).queryParam("q", city1).queryParam("country", "IN")
				.queryParam("maxRow", "1");
		logger.info("Validate CityName URL: {}", builder.toUriString());
		CityDetailsResponse response1 = restTemplate.getForObject(builder.toUriString(), CityDetailsResponse.class);
		try {
			if (response1.getTotalResultsCount() == 0) {
				WeatherDataRes weatherError = new WeatherDataRes();
				HeadRes error = new HeadRes();
				error.setCode("404");
				error.setMessage("City Name is Invalid and Response Count is Zero.");
				weatherError.setHead(error);
				return weatherError;
			}
		} catch (Exception e) {
			logger.info("App Error: " + e);
		}
		boolean isValid = validateCity(city1);
		if (isValid == true) {
			logger.info("Successfully Validated the City Name.");
			String apiUrl = constant.API_URL + city1 + constant.API_ID + apiKey;
			logger.info("Fetching weather data from URL: {}", apiUrl);
			WeatherDataRes response = restTemplate.getForObject(apiUrl, WeatherDataRes.class);
			logger.info("Response City Name: {}", response.getName());
			logger.info("UI City Name: {}", req.getCity());
			if (response.getName().equals(city1)) {
				ObjectMapper map = new ObjectMapper();
				try {
					String printResponse = map.writeValueAsString(response);
					logger.info("Main Response: {}", printResponse);
				} catch (Exception e) {
					logger.info("App Error: " + e);
				}
				WeatherDataRes responseDate = response;
				Main2 main2 = new Main2();
				Sys2 sys2 = new Sys2();
				main2.setTemp(converkelvinToCelsius(response.getMain().getTemp()));
				main2.setTemp_min(converkelvinToCelsius(response.getMain().getTemp_min()));
				main2.setTemp_max(converkelvinToCelsius(response.getMain().getTemp_max()));
				sys2.setCountry(response.getSys().getCountry());
				sys2.setSunrise(convertUnixTimestampToDateTime(response.getSys().getSunrise()));
				sys2.setSunset(convertUnixTimestampToDateTime(response.getSys().getSunset()));
				responseDate.setSysTwo(sys2);
				responseDate.setMain2(main2);
				Coord newTest = new Coord();
				newTest.setLatitude(decimaltoDMS(response.getCoord().getLat()));
				newTest.setLongitude(decimaltoDMS(response.getCoord().getLon()));
				List<Weather> weatherlist = response.getWeather();
				if (weatherlist != null && !weatherlist.isEmpty()) {
					for (Weather weather : weatherlist) {
						String iconCode = weather.getIcon();
						String iconUrl = constant.ICON_URL + iconCode + constant.ICON_CODE;
						logger.info("Fetching Weather Icon from URL: {}", iconUrl);
						weather.setIcon2(iconUrl);
					}
				}
				responseDate.setWeather(weatherlist);
				HeadRes error = new HeadRes();
				error.setCode("1");
				error.setMessage("Success Response");
				responseDate.setHead(error);

				return responseDate;
			} else {
				WeatherDataRes weatherError = new WeatherDataRes();
				HeadRes error = new HeadRes();
				error.setCode("404");
				error.setMessage("city not found");
				weatherError.setHead(error);
				return weatherError;
			}
		} else {
			WeatherDataRes weatherError = new WeatherDataRes();
			HeadRes error = new HeadRes();
			error.setCode("404");
			error.setMessage("City Name is Invalid");
			weatherError.setHead(error);
			return weatherError;
		}
	}

	public boolean validateCity(String city1) {
		RestTemplate restTemplate = new RestTemplate();
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(constant.VALIDATION_URL)
				.queryParam("username", constant.VALIDATION_USERNAME).queryParam("q", city1).queryParam("country", "IN")
				.queryParam("maxRow", "1");
		logger.info("Validate CityName URL: {}", builder.toUriString());
		CityDetailsResponse response = restTemplate.getForObject(builder.toUriString(), CityDetailsResponse.class);

		if (response != null && response.getGeonames().get(0).getName().equals(city1)) {
			String aa = response.getGeonames().get(0).getName();
			String cityName;
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				cityName = objectMapper.writeValueAsString(aa);
				logger.info("City Name from Geonames: {}", cityName);
				String responseS = objectMapper.writeValueAsString(response);
				logger.info("Validate City Name Response: {}", responseS);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return true;
		} else {
			logger.info("Failed to Validate the City Name.");
			return false;
		}
	}

	public String convertUnixTimestampToDateTime(long timestamp) {
		LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		String formattedDateTime = dateTime.format(formatter);
		return formattedDateTime;
	}

	public double converkelvinToCelsius(double kelvin) {
		double Celsius;
		Celsius = (kelvin - 273.15);
		DecimalFormat df = new DecimalFormat("#.##");
		String formatted = df.format(Celsius);
		return Double.parseDouble(formatted);
	}

	public String decimaltoDMS(double value) {
		int degree = (int) value;
		double minutesDouble = (value - degree) * 60;
		int minutes = (int) minutesDouble;
		double seconds = (minutesDouble - minutes) * 60;
		return String.format("%d°%d'%.2f''", degree, minutes, seconds);
	}

	@Override
	public GeonamesRes getCityCityDetails(CityDetailsReq req) {
		CityDetailsResponse midresponses = new CityDetailsResponse();
		GeonamesRes response = new GeonamesRes();
		HeadRes success = new HeadRes();
		Geonames setresponse = new Geonames();
		String city1 = req.getCity();
		boolean isValid = validateCity(city1);
		if (isValid == true) {
			try {
				String country = req.getCountry();
				UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(constant.VALIDATION_URL)
						.queryParam("username", constant.VALIDATION_USERNAME).queryParam("q", city1)
						.queryParam("country", country).queryParam("maxRow", "1");
				logger.info("Validate CityName URL: {}", builder.toUriString());
				midresponses = restTemplate.getForObject(builder.toUriString(), CityDetailsResponse.class);
				setresponse = midresponses.getGeonames().get(0);

			} catch (Exception e) {

			}
			success.setCode("1");
			success.setMessage("Success");
			response.setGeonames(setresponse);
			response.setHead(success);
			return response;
		} else {
			HeadRes error = new HeadRes();
			error.setCode("404");
			error.setMessage("city not found or Invalid");
			response.setHead(error);
			return response;
		}

	}

}
