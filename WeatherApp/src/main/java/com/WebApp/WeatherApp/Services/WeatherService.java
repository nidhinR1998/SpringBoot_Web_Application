package com.WebApp.WeatherApp.Services;

import com.WebApp.WeatherApp.Request.CityDetailsReq;
import com.WebApp.WeatherApp.Request.WeatherDataReq;
import com.WebApp.WeatherApp.Responses.GeonamesRes;
import com.WebApp.WeatherApp.Responses.WeatherDataRes;

public interface WeatherService {

	public WeatherDataRes getWeatherData(WeatherDataReq req);

	public GeonamesRes getCityCityDetails(CityDetailsReq req);
	

	

}
