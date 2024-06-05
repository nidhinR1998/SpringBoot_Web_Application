
export interface WeatherData {
    coord: Coord;
    weather: Weather[];
    base: string;
    main: Main;
    main2: Main2;
    visibility: number;
    wind: Wind;
    clouds: Clouds;
    dt: number;
    sys: Sys;
    sysTwo: Sys2;
    timezone: number;
    id: number;
    name: string;
    cod: number;
}
export interface Coord {
    lon: number;
    lat: number;
}
export interface Weather {
    id: number;
    main: string;
    description: string;
    icon: string;
    icon2: string;
}
export interface Main {
    temp: number;
    feels_like: number;
    temp_min: number;
    temp_max: number;
    pressure: number;
    humidity: number;
    sea_level: number;
    grnd_level: number;
}
export interface Main2 {
    temp: number;
    feels_like: number;
    temp_min: number;
    temp_max: number;
    pressure: number;
    humidity: number;
    sea_level: number;
    grnd_level: number;
}
export interface Wind {
    speed: number;
    deg: number;
    gust: number;
}
export interface Clouds {
    all: number;
}
export interface Sys {
    country: string;
    sunrise: number;
    sunset: number;
}
export interface Sys2 {
    country: string;
    sunrise: string;
    sunset: string;
}

