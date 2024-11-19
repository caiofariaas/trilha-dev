package br.ETS.almoxarifado;

import br.ETS.almoxarifado.produto.DadosProduto;

import br.ETS.almoxarifado.produto.ProdutoService;

import java.util.Scanner;

public class Main {
    private static ProdutoService produtoService = new ProdutoService();
    private static Scanner sc = new Scanner(System.in);

    private static int exibirMenu(){
        System.out.println("Almoxarifado ETS");
        System.out.println("""
                Selecione uma opção
                1 -> Inserir novo produto
                2 -> Remover Produto
                3 -> Listar Produtos
                4 -> Adicionar quantidade
                5 -> Remover quantidade
                0 -> Encerrar aplicação
                """);
        return Integer.parseInt(sc.nextLine());
    }

    public static void main(String[] args) {
        var opt = exibirMenu();

        while (opt!= 0){
            try {
                switch (opt){
                    case 1 -> createProduto();
                    case 2 -> delProduto();
                    case 3 -> getAllProdutos();
                    case 4 -> addQtdProduto();
                    case 5 -> rmQuantidadeProduto();
                }
            }
            catch (RegraDaAplicacaoException e){
                System.out.println(e.getMessage());
                System.out.println("Pressione ENTER para voltar ao menu principal");
                sc.nextLine();
            }
            opt = exibirMenu();
        }
    }

    private static void createProduto(){
        System.out.println("""
                \nInsira o ID do produto que deseja cadastrar:
                """);
        var id = Integer.parseInt(sc.nextLine());
        System.out.println("""
                \nInsira o nome do produto:
                """);
        var produto = sc.nextLine();
        System.out.println("""
                \nInsira o PartNumber:
                """);
        var partNumber = sc.nextLine();
        System.out.println("""
                \nInsira a divisão:
                """);
        var divisao = sc.nextLine();
        System.out.println("""
                \nInsira a quantidade disponível:
                """);
        var qtd = Integer.parseInt(sc.nextLine());

        produtoService.adicionar(new DadosProduto(id, produto, partNumber, divisao, qtd));

        System.out.println("Produto: " + produto + "\n");
    }

    private static void getAllProdutos(){
        var produtos = produtoService.buscarProdutos();
        produtos.forEach(System.out::println);
        System.out.println("Pressione 'ENTER' para voltar ao menu principal");
        sc.nextLine();
    }

    private static void addQtdProduto(){
        System.out.println("Digite o Id" );
        var id = Integer.parseInt(sc.nextLine());
        System.out.println("Digite a quantidade que deseja:");
        var qtd = Integer.parseInt(sc.nextLine());

        produtoService.addQtdProduto(id, qtd);
        System.out.printf("A quantidade de %d foi adicionada do produto com id %d\n", qtd, id);
        System.out.println("Pressione 'ENTER' para voltar ao menu principal");
        sc.nextLine();
    }

    private static void rmQuantidadeProduto(){
        System.out.println("Digite o Id" );
        var id = Integer.parseInt(sc.nextLine());
        System.out.println("Digite a quantidade que deseja:");
        var qtd = Integer.parseInt(sc.nextLine());

        produtoService.rmQtdProduto(id, qtd);

        System.out.printf("A quantidade de %d foi removida do produto com id %d \n", qtd, id);
        System.out.println("Pressione 'ENTER' para voltar ao menu principal");
        sc.nextLine();
    }

    private static  void delProduto(){
        System.out.println("Digite o id do produto: ");
        var id = Integer.parseInt(sc.nextLine());
        produtoService.delProduto(id);

        System.out.printf("O produto com Id %d foi removido!\n", id);

        System.out.println("Pressione 'ENTER' para voltar ao menu principal");
        sc.nextLine();
    }
}