package br.com.angelo.fipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosMarcas(@JsonAlias("code") String codigo,
                          @JsonAlias("name") String nome) {
}
