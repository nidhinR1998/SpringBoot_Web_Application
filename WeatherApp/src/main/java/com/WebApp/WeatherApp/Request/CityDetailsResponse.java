package com.WebApp.WeatherApp.Request;

import java.util.List;

import com.WebApp.WeatherApp.Pojo.Geonames;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityDetailsResponse {
	private int totalResultsCount;
	private List<Geonames>geonames;

}
