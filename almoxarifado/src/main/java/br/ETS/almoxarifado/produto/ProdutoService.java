package br.ETS.almoxarifado.produto;

import br.ETS.almoxarifado.RegraDaAplicacaoException;
import br.ETS.almoxarifado.connection.ConnectionFactory;

import java.sql.Connection;
import java.util.ArrayList;

public class ProdutoService {
    private ArrayList<Produto> produtos = new ArrayList<>();
    private ConnectionFactory connectionFactory;

    public  ProdutoService(){
        this.connectionFactory = new ConnectionFactory();
    }

    public void adicionar(DadosProduto data){
        var produto = new Produto(data);

        if(produtos.contains(produto)){
            throw new RegraDaAplicacaoException("Produto já existente");
        }

        Connection connection = connectionFactory.recuperarConexao();
        new ProdutoDAO(connection).salvar(data);
    }

    public ArrayList<Produto> buscarProdutos(){
        Connection connection = connectionFactory.recuperarConexao();
        return new ProdutoDAO(connection).listar();
    }

    public Produto buscarProdutoPorId(int id) {
        Connection connection = connectionFactory.recuperarConexao();
        Produto produto = new ProdutoDAO(connection).listarPorId(id);

        if(produto != null){
            return produto;
        }
        throw new RegraDaAplicacaoException("Produto não encontrado!");
    }

    public void addQtdProduto(int id, int quantidade){
        if(quantidade <= 0){
            throw new RegraDaAplicacaoException("A quantidade não pode ser menor ou igual a 0!");
        }

        var produto = buscarProdutoPorId(id);
        Connection connection = connectionFactory.recuperarConexao();

        new ProdutoDAO(connection).alterar(
                produto.getId(),
                (produto.getQuantidade() + quantidade));
    }

    public void rmQtdProduto(int id, int quantidade){
        var produto = buscarProdutoPorId(id);

        if(quantidade <= 0){
            throw new RegraDaAplicacaoException("A quantidade não pode ser menor ou igual a 0!");
        }
        if(quantidade > produto.getQuantidade()){
            throw new RegraDaAplicacaoException("O numero inserido é maior que o número disponível no estoque!");
        }
        Connection connection = connectionFactory.recuperarConexao();

        new ProdutoDAO(connection).alterar(
                produto.getId(),
                (produto.getQuantidade() - quantidade));
    }

    public void delProduto(int id){
        Connection connection = connectionFactory.recuperarConexao();
        new ProdutoDAO(connection).deletar(id);
    }
}
