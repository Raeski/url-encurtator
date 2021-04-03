package com.br.encurtador.url.request;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UrlPostRequest {

    private String url;

    private String createdAt;

    private String newUrl;

}
