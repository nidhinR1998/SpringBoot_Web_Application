package com.WebApp.WeatherApp.Responses;

import com.WebApp.WeatherApp.Pojo.Geonames;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class GeonamesRes {
	private HeadRes head;
	private Geonames geonames;
}
