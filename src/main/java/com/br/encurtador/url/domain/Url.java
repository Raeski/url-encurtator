package com.br.encurtador.url.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Url {

    @Id
    @GeneratedValue
    private Integer id;


    private String url;


}
