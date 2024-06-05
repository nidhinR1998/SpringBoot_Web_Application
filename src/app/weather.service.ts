import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable, count } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class WeatherService {
    private apiUrl = 'http://localhost:8080/weatherApp';

    constructor(private http: HttpClient) {}

    getWeather(city: string) : Observable<any> {
        const body = { city: city };
        return this.http.post(`${this.apiUrl}/getWeather?city`,body);
    }
    
}