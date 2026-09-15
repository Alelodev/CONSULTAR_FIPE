package br.com.angelo.fipe.service;

import tools.jackson.databind.ObjectMapper;

import java.util.List;

public class ConverteDados implements IConverteDados{
    ObjectMapper map = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        return map.readValue(json, classe);
    }

    @Override
    public <T> List<T> obterLista(String json, Class<T> classe) {
        var tipoLista = map.getTypeFactory().constructCollectionType(List.class, classe);
        return map.readValue(json, tipoLista);
    }
}
