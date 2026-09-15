package br.com.angelo.fipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosPreco(@JsonAlias("price") String preco,
                         @JsonAlias("model") String modelo,
                         @JsonAlias("modelYear") String anoDoModelo,
                         @JsonAlias("referenceMonth") String mesDeReferencia
                         ) {

}
