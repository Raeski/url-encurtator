package com.br.encurtador.url.repository;

import com.br.encurtador.url.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UrlRepository extends JpaRepository<Url, Long> {

    List<Url> findByNewUrl(String newUrl);
}
