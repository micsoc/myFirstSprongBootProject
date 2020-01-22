package com.example.Excercises.controllers;

import com.example.Excercises.Book;
import com.example.Excercises.News;
import com.example.Excercises.NewsReader;
import com.example.Excercises.Post;
import com.example.Excercises.currency.Currency;
import com.example.Excercises.currency.CurrencyExchanger;
import com.example.Excercises.currency.CurrencyType;
import com.example.Excercises.weather.WeatherForacast;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FrontEndController {
    List<Book> books = new ArrayList<>();

    @GetMapping("/")
    String index(ModelMap map) {

        map.put("image", "static/bckgrnd.jpg");
        return "index";
    }

    @GetMapping("/ex1")
    public String getFirstPage(ModelMap map) {

        map.put("sampleText", "Sample text");
        map.put("a", 123);
        map.put("b", 456);

        return "firstPage";
    }

    @GetMapping("/ex2")
    String getSeondPage(ModelMap map) {
        map.put("books", books);
        map.put("newBook", new Book());
        return "secondPage";
    }

    @PostMapping("/ex2")
    String postSecond(@ModelAttribute Book book) {
        books.add(book);
        return "postSecond";
    }

    @GetMapping("/ex3")
    String getThirdPage(ModelMap map) {
        map.put("bookList", books);
        map.put("reset", books = new ArrayList<>());
        return "thirdPage";
    }

    @GetMapping("/ex4")
    String getFourthPage(ModelMap map) {
        map.put("post", new Post());
        return "fourthPage";
    }

    @PostMapping("/ex4")
    String postFourth(@ModelAttribute Post post) {

        return "postFourth";
    }

    @GetMapping("/weather")
String getWeather(ModelMap map) {
        map.put("weather", WeatherForacast.readWeather());
        return "weather";
    }
    @GetMapping("/news")
    String getNews(ModelMap map) {

        List<News> list = NewsReader.readNews();
        map.put("news", list);

        return "news";
    }

    @GetMapping("/exchanger")
    String exchanger(ModelMap map) {
        map.put("exchanger", new CurrencyExchanger());
        return "exchanger";
    }

    @PostMapping("/exchanger")
    String postEx(@ModelAttribute CurrencyExchanger exchanger, ModelMap map) {
        exchanger.exchange();
        map.put("exchanger",exchanger);
        System.out.println(exchanger.getAmount());
        System.out.println(exchanger.getRatio());
        System.out.println(exchanger.getPayment());
        return "postEx";
    }

}

