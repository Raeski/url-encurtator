package com.br.encurtador.url.controller;

import com.br.encurtador.url.domain.Url;
import com.br.encurtador.url.request.UrlPostRequest;
import com.br.encurtador.url.service.UrlService;
import com.br.encurtador.url.util.UrlCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static javax.servlet.http.HttpServletResponse.SC_OK;

@ExtendWith(SpringExtension.class)
public class UrlControllerTest {

    @InjectMocks
    private UrlController urlController;

    @Mock
    private UrlService urlServiceMock;

    @BeforeEach
    void setUp() {

        BDDMockito.when(urlServiceMock.saveUrl(ArgumentMatchers.any(Url.class)))
                .thenReturn(UrlCreator.createValidUrl());

        BDDMockito.when(urlServiceMock.findByCode(ArgumentMatchers.anyString()))
                .thenReturn(List.of(UrlCreator.createValidUrl()));

        BDDMockito.when(urlServiceMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(UrlCreator.createValidUrl());

    }

    @Test
    @DisplayName("Save returns url when successful")
    void save_ReturnsUrl_WhenSuccessful() {

        Url url = urlController.save(UrlCreator.createUrlToBeSaved()).getBody();

        Assertions.assertThat(url.getUrl()).isNotNull().isEqualTo(UrlCreator.createValidUrl().getUrl());

    }



}
