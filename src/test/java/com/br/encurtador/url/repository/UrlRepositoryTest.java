package com.br.encurtador.url.repository;

import com.br.encurtador.url.domain.Url;
import com.br.encurtador.url.service.UrlService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

@DataJpaTest
@DisplayName("Tests for Url Repository")
class UrlRepositoryTest {

    @Autowired
    private UrlRepository urlRepository;

    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    @DisplayName("Save url when Successful")
    void save_PersistUrl_WhenSuccessful(){
        Url urlToBeSaved = createUrl();

        Url urlSaved = this.urlRepository.save(urlToBeSaved);

        Assertions.assertThat(urlSaved).isNotNull();

        Assertions.assertThat(urlSaved.getId()).isNotNull();

        Assertions.assertThat(urlSaved.getUrl()).isEqualTo(urlToBeSaved.getUrl());

        Assertions.assertThat(urlSaved.getNewUrl()).isEqualTo(urlToBeSaved.getNewUrl());
    }

    @Test
    @DisplayName("Save Url is empty when Successful")
    void save_PersistUrlIsEmpty_WhenSuccessful() {
        Url urlToBeSaved = createUrl();

        Url urlSaved = this.urlRepository.save(urlToBeSaved);

        urlSaved.setUrl("");

        Assertions.assertThat(urlSaved).isNotNull();

        Assertions.assertThat(urlSaved.getId()).isNotNull();

        Assertions.assertThat(urlSaved.getUrl()).isEqualTo(urlToBeSaved.getUrl());

        Assertions.assertThat(urlSaved.getNewUrl()).isEqualTo(urlToBeSaved.getNewUrl());
    }

    @Test
    @DisplayName("Find By New Url returns list of URL when successful")
    void findByNewUrl_ReturnsListOfUrl_WhenSuccessful(){
        Url urlToBeSaved = createUrl();

        Url urlSaved = this.urlRepository.save(urlToBeSaved);

        String url = urlSaved.getUrl();

        List<Url> byUrl = this.urlRepository.findByNewUrl(url);

        Assertions.assertThat(byUrl).isNotNull();
    }

    @Test
    @DisplayName("Find By New Url returns empty list of URL when successful")
    void findByNewUrl_ReturnsEmpty_WhenUrlIsNotFound(){
        List<Url> byUrl = this.urlRepository.findByNewUrl("12");

        Assertions.assertThat(byUrl).isEmpty();
    }




    private Url createUrl() {
        return Url.builder()
                .url("https://www.instagram.com/?hl=pt-br")
                .newUrl(getSaltString())
                .createdAt(dateNow())
                .build();
    }

    private String getSaltString() {
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


    private String dateNow() {
        Date date = new Date();
        String format = dateFormat.format(date);
        return format;
    }



}
