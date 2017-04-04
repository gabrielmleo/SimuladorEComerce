package com.example.gabrielmoraes.simuladorecomerce.util;

import java.text.NumberFormat;

/**
 * Created by gabri on 03/04/2017.
 */

public class Util {


    public static String currencyFormater(String value){
        String formatedString = value.substring(0,(value.length()-2)) + "." + value.substring((value.length()-2),value.length());

        return NumberFormat.getCurrencyInstance().format(Double.parseDouble(formatedString));
    }
}
