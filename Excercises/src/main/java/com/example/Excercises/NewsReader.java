package com.example.Excercises;

import com.fasterxml.jackson.annotation.JsonAlias;
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
import java.util.ArrayList;
import java.util.List;

public class NewsReader {
    static List<News> news = new ArrayList<>();

    public static List<News> readNews() {
        String url = "https://newsapi.org/v2/top-headlines?country=pl&" +
                "apiKey=73abe6bb38aa45079d123c419126fd5d&category=science";

        try {
            URL link = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) link.openConnection();
            System.out.println(connection.getResponseCode());

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(bufferedReader);
            JSONArray articles = (JSONArray) jsonObject.get("articles");

            for (Object article : articles) {

                JSONObject art = (JSONObject) article;
                String title = (String) art.get("description");
                String addres = (String) art.get("url");
                if (title != null && addres != null) {
                news.add(new News(title, addres));
                }
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return news;
    }
}
