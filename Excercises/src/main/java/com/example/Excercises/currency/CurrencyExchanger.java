package com.example.Excercises.currency;

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

public class CurrencyExchanger {

    Currency sell = new Currency();

    Currency buy = new Currency();
    double amount;
    double ratio;
    double payment;



    public void exchange() {

        if (!sell.getCurrency().equals(CurrencyType.PLN)) {
            sell.setCourse(downloadCourse(sell.getCurrency()));
        } else {
            sell.setCourse(1.0);
        }
        if (!buy.getCurrency().equals(CurrencyType.PLN)) {
            buy.setCourse(downloadCourse(buy.getCurrency()));

        } else {
            buy.setCourse(1.0);
        }

        ratio = Math.round((sell.getCourse() / buy.getCourse()) * 1000) / 1000.0;
        payment = Math.round((amount * ratio) * 100) / 100.0;

    }


    public double downloadCourse(CurrencyType currency) {
        String url = "http://api.nbp.pl/api/exchangerates/rates/a/" + currency;
        double course = 0;
        try {
            URL sellLink = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) sellLink.openConnection();
            System.out.println(connection.getResponseCode());

            BufferedReader sellBufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            JSONParser parser = new JSONParser();

            JSONObject jsonObject = (JSONObject) parser.parse(sellBufferedReader);
            JSONArray array = (JSONArray) jsonObject.get("rates");
            JSONObject mid = (JSONObject) array.get(0);

            course = ((double) mid.get("mid"));
            course = Math.round(course * 1000) / 1000.0;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return course;
    }

    public Currency getSell() {
        return sell;
    }

    public Currency getBuy() {
        return buy;
    }

    public double getAmount() {
        return amount;
    }

    public double getRatio() {
        return ratio;
    }

    public double getPayment() {
        return payment;
    }

    public void setSell(Currency sell) {
        this.sell = sell;
    }

    public void setSellCurrency(CurrencyType sell) {
        this.sell.setCurrency(sell);
    }

    public void setBuy(Currency buy) {
        this.buy = buy;
    }

    public void setBuyCurrency(CurrencyType buy) {
        this.buy.setCurrency(buy);
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }
}
