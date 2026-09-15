package br.com.angelo.fipe.principal;

import br.com.angelo.fipe.model.DadosMarcas;
import br.com.angelo.fipe.model.DadosPreco;
import br.com.angelo.fipe.service.ConsultarApi;
import br.com.angelo.fipe.service.ConverteDados;

import java.util.List;
import java.util.Scanner;

public class Principal {

    ConsultarApi consultar = new ConsultarApi();
    Scanner sc = new Scanner(System.in);
    private String type = null;
    ConverteDados conversor = new ConverteDados();


    public void exibeMenu(){
        System.out.println("Qual tipo de veiculo deseja procurar?");
        System.out.println("1 - CARRO");
        System.out.println("2 - MOTO");
        System.out.println("3 - CAMINHAO");
        int tipoDeVeiculo;
        tipoDeVeiculo = sc.nextInt();
        
        if(tipoDeVeiculo == 1){
            type = "cars";
        } else if (tipoDeVeiculo == 2) {
            type = "motorcycles";
        } else if (tipoDeVeiculo == 3) {
            type = "trucks";
        } else {
            System.out.println("Selecione um tipo valido");
        }

        String endereco = "https://fipe.parallelum.com.br/api/v2/" + type + "/brands";

        String json = consultar.obterDados(endereco);

        List<DadosMarcas> marcas = conversor.obterLista(json, DadosMarcas.class);

        for (DadosMarcas marca : marcas){
            System.out.println(marca.codigo() + " - " + marca.nome());
        }

        System.out.println("Qual marca deseja pesquisar?, Digite o numero: ");
        int codigoMarca = sc.nextInt();

        String enderecoModelos = endereco + "/" + codigoMarca +"/models";

        String jsonModelos = consultar.obterDados(enderecoModelos);

        List<DadosMarcas> modelos = conversor.obterLista(jsonModelos, DadosMarcas.class);

        for(DadosMarcas modelo : modelos){
            System.out.println(modelo.codigo() + "-" + modelo.nome());
        }

        sc.nextLine();
        System.out.println("Digite um trecho do nome do carro: ");
        String busca = sc.nextLine();

        modelos.stream().filter(modelo -> modelo.nome()
                .toLowerCase()
                .contains(busca.toLowerCase()))
                .forEach(modelo -> System.out.println(modelo.codigo() + "-" + modelo.nome()));

        System.out.println("Digite o código do modelo:");
        int codigoModelo = sc.nextInt();

        String enderecoAnos = enderecoModelos + "/" + codigoModelo + "/years";

        String jsonAnos = consultar.obterDados(enderecoAnos);

        List<DadosMarcas> anos = conversor.obterLista(jsonAnos, DadosMarcas.class);

        for (DadosMarcas ano : anos) {
            String enderecoAvaliacao = enderecoAnos + "/" + ano.codigo();

            String jsonAvaliacao = consultar.obterDados(enderecoAvaliacao);

            DadosPreco avaliacao = conversor.obterDados(jsonAvaliacao, DadosPreco.class);

            System.out.println("Modelo: " + avaliacao.modelo()
                    + " | Ano: " + avaliacao.anoDoModelo()
                    + " | Preço: " + avaliacao.preco()
                    + " | Referência: " + avaliacao.mesDeReferencia());
        }
    }



}
