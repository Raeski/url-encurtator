package com.br.encurtador.url.service;

import com.br.encurtador.url.domain.Url;
import com.br.encurtador.url.mapper.UrlMapper;
import com.br.encurtador.url.request.UrlPostRequest;
import com.br.encurtador.url.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;

    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    private String getSaltString() {
        String SALTCHARS = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10 ) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    public Url saveUrl(Url urlPostRequest){

        try {
            Date date = new Date();
            String format = dateFormat.format(date);
            urlPostRequest.setCreatedAt(format);
            if(urlPostRequest.getUrl() != null) {
                urlPostRequest.setNewUrl(getSaltString());
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }
//        return urlRepository.save(UrlMapper.INSTANCE.toUrl(urlPostRequest));
            return urlRepository.save(urlPostRequest);
    }

    public Url findById(Long id) {
        return urlRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    public List<Url> findByCode(String newUrl) {
        return urlRepository.findByNewUrl(newUrl);
    }

}
