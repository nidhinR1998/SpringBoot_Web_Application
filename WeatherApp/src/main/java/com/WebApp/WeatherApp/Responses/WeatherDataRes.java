package com.WebApp.WeatherApp.Responses;

import java.util.List;

import com.WebApp.WeatherApp.Pojo.Clouds;
import com.WebApp.WeatherApp.Pojo.Coord;
import com.WebApp.WeatherApp.Pojo.Main;
import com.WebApp.WeatherApp.Pojo.Main2;
import com.WebApp.WeatherApp.Pojo.Sys;
import com.WebApp.WeatherApp.Pojo.Sys2;
import com.WebApp.WeatherApp.Pojo.Weather;
import com.WebApp.WeatherApp.Pojo.Wind;

public class WeatherDataRes {

	private HeadRes head;
	private Coord coord;
	private List<Weather> weather;
	private String base;
	private Main main;
	private Main2 main2;
	private int visibility;
	private Wind wind;
	private Clouds clouds;
	private int dt;
	private Sys sys;
	private Sys2 sysTwo;
	private int timezone;
	private int id;
	private String name;
	private int cod;

	public HeadRes getHead() {
		return head;
	}

	public void setHead(HeadRes head) {
		this.head = head;
	}

	public Coord getCoord() {
		return coord;
	}

	public void setCoord(Coord coord) {
		this.coord = coord;
	}

	public List<Weather> getWeather() {
		return weather;
	}

	public void setWeather(List<Weather> weather) {
		this.weather = weather;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public int getVisibility() {
		return visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}

	public Wind getWind() {
		return wind;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

	public Clouds getClouds() {
		return clouds;
	}

	public void setClouds(Clouds clouds) {
		this.clouds = clouds;
	}

	public int getDt() {
		return dt;
	}

	public void setDt(int dt) {
		this.dt = dt;
	}

	public Sys getSys() {
		return sys;
	}

	public void setSys(Sys sys) {
		this.sys = sys;
	}

	public int getTimezone() {
		return timezone;
	}

	public void setTimezone(int timezone) {
		this.timezone = timezone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public Sys2 getSysTwo() {
		return sysTwo;
	}

	public void setSysTwo(Sys2 sysTwo) {
		this.sysTwo = sysTwo;
	}

	public Main2 getMain2() {
		return main2;
	}

	public void setMain2(Main2 main2) {
		this.main2 = main2;
	}

}

// Angular weather-data.model.ts file
/*
 * export interface WeatherData { coord: { lon: number; lat: number; }
 * 
 * main: { temp: number; feels_like: number; temp_min: number; temp_max: number;
 * pressure: number; humidity: number; sea_level: number; grnd_level: number; }
 * visibility: number;
 * 
 * wind: { speed: number; deg: number; gust: number;
 * 
 * } dt: number;
 * 
 * sys: { type: number; id: number; sunrise: number; sunset: number; } }
 */