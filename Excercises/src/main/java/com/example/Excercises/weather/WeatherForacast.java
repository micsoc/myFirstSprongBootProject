package com.example.Excercises.weather;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;





public class WeatherForacast {

    public  static Weather readWeather() {
    Weather weather = new Weather();
        String icon = "";
        String url = "http://api.openweathermap.org/data/2.5/weather?q=Warsaw,pl" +
                "&APPID=4c1cb8c0ad8d4a75c5ca249179487061&lang=pl&units=metric";

        try {
            URL link = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) link.openConnection();
            System.out.println(connection.getResponseCode());

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(bufferedReader);
            JSONArray wetarr = (JSONArray) jsonObject.get("weather");
            JSONObject currentWeather = (JSONObject) wetarr.get(0);

            weather.setDescription((String) currentWeather.get("description"));
            icon = (String) currentWeather.get("icon");

            JSONObject numerals = (JSONObject) jsonObject.get("main");
            weather.setTemperature((double)numerals.get("temp"));
            weather.setHumidity((long) numerals.get("humidity"));
            weather.setPressure((long) numerals.get("pressure"));


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        weather.setImageUrl("http://openweathermap.org/img/wn/" + icon + "@2x.png");
        return weather;
    }

}
