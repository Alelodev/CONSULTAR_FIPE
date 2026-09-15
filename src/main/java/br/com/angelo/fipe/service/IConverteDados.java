package br.com.angelo.fipe.service;

import java.util.List;

public interface IConverteDados {
    //<T> é o TIPO GENERICO e T representa o TIPO DO RETORNO
    //Ex: Filme filme = obterDados(json);
    <T> T obterDados(String json, Class<T> classe);
    <T> List<T> obterLista(String json, Class<T> classe);
    }
