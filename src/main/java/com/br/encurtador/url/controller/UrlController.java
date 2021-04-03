package com.br.encurtador.url.controller;

import com.br.encurtador.url.domain.Url;
import com.br.encurtador.url.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequestMapping("/url")
@RestController
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;


    @PostMapping
    public ResponseEntity<Url> save(@RequestBody Url urlPostRequest) {
        return new ResponseEntity<>(urlService.saveUrl(urlPostRequest), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public void redirectById(@PathVariable Long id, HttpServletResponse httpServletResponse) throws IOException {
        Url byId = urlService.findById(id);
        httpServletResponse.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        httpServletResponse.setHeader("Location", byId.getUrl());
        httpServletResponse.setHeader("Connection", "close");
    }

    @GetMapping(path = "/find/{newUrl}")
    public void findByCode(@PathVariable String newUrl, HttpServletResponse httpServletResponse) {
        List<Url> byCode = urlService.findByCode(newUrl);
        httpServletResponse.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        httpServletResponse.setHeader("Location", byCode.get(0).getUrl());
        httpServletResponse.setHeader("Connection", "close");
    }
}
