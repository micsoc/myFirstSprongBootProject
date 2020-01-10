package com.example.Excercises;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Controll {
    List<Book> books = new ArrayList<>();

    @GetMapping("/")
    String index(ModelMap map) {

        map.put("image", "../resources/images/bckgrnd.jpg");
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

    @GetMapping("/news")
    String getNews(ModelMap map) {
        List<News> list = NewsReader.readNews();
        map.put("news", list);

        return "news";
    }
}

