import { Component } from '@angular/core';
import { WeatherService } from '../weather.service';
import { Weather, WeatherData } from './weather-data.model';
@Component({
  selector: 'app-weather',
  templateUrl: './weather.component.html',
  styleUrls: ['./weather.component.css']
})
export class WeatherComponent {
  city = ' ';
  weatherData!: WeatherData;
  showWeatherData: boolean = false;

  constructor(private weatherService: WeatherService) { }
  getWeather() {
    console.log('City:', this.city);
    console.log('WeatherData:', this.weatherData);
    this.weatherService.getWeather(this.city).subscribe(
      (data) => {
        this.weatherData = data;
        // this.showWeatherData = true;
        console.log('WeatherService ResponseData:', this.weatherData);
      },
      (error) => {
        console.error('Error ferching weather data:', error);
      }
    );
  }
  submit() {
    this.getWeather();
    this.showWeatherData = true;
  }

}
