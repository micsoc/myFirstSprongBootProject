package com.example.Excercises.currency;

import lombok.Data;

@Data
public class ExchangeRequest {

    CurrencyType sell;
    CurrencyType buy;
    double amount;

}
