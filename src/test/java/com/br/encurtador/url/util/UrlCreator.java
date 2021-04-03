package com.br.encurtador.url.util;

import com.br.encurtador.url.domain.Url;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class UrlCreator {

    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static Url createUrlToBeSaved() {
        return Url.builder()
                .url("https://www.google.com/")
                .newUrl(getSaltString())
                .createdAt(dateNow())
                .build();
    }

    public static Url createValidUrl() {
        return Url.builder()
                .id(1L)
                .url("https://www.google.com/")
                .newUrl(getSaltString())
                .createdAt(dateNow())
                .build();
    }


    public static String getSaltString() {
        String SALTCHARS = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10 ) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }


    public static String dateNow() {
        Date date = new Date();
        String format = dateFormat.format(date);
        return format;
    }
}
