package com.br.encurtador.url.service;

import com.br.encurtador.url.domain.Url;
import com.br.encurtador.url.repository.UrlRepository;
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

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class UrlServiceTest {

    @InjectMocks
    private UrlService urlService;

    @Mock
    private UrlRepository urlRepositoryMock;

    @BeforeEach
    void setUp() {

        BDDMockito.when(urlRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(UrlCreator.createValidUrl()));

        BDDMockito.when(urlRepositoryMock.findByNewUrl(ArgumentMatchers.anyString()))
                .thenReturn(List.of(UrlCreator.createValidUrl()));

        BDDMockito.when(urlRepositoryMock.save(ArgumentMatchers.any(Url.class)))
                .thenReturn(UrlCreator.createValidUrl());

    }

    @Test
    @DisplayName("findById return url when successful")
    void findById_ReturnsUrl_WhenSuccessful() {
        Long expectedId = UrlCreator.createValidUrl().getId();

        Url url = urlService.findById(1L);

        Assertions.assertThat(url).isNotNull();

        Assertions.assertThat(url.getId()).isNotNull().isEqualTo(expectedId);

    }

    @Test
    @DisplayName("findByNewUrl return url when successful")
    void findByNewUrl_ReturnsUrl_WhenSuccessful() {
        String newUrl = UrlCreator.createValidUrl().getNewUrl();

        List<Url> url = urlService.findByCode(newUrl);

        Assertions.assertThat(url).isNotNull();

        Assertions.assertThat(url.get(0).getNewUrl()).isNotNull().isNotEmpty();

    }

    @Test
    @DisplayName("Save returns url when successful")
    void save_ReturnsUrl_WhenSuccessful() {
        Long expectedId = UrlCreator.createValidUrl().getId();

        Url url = urlService.saveUrl(UrlCreator.createUrlToBeSaved());

        Assertions.assertThat(url.getUrl()).isNotNull().isEqualTo(UrlCreator.createValidUrl().getUrl());

    }








}
