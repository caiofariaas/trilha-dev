package br.ETS.almoxarifado.produto;

import br.ETS.almoxarifado.RegraDaAplicacaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoDAO {
    private Connection connection;

    public ProdutoDAO(Connection connection){
        this.connection = connection;
    }
    public void salvar(DadosProduto data){
        String sql = """
                INSERT INTO tb_materiais_diretos(ID,PRODUTO,PARTNUMBER,DIVISAO,QUANTIDADE)
                VALUES(?,?,?,?,?);
                """;

        var produto = new Produto(data);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, data.id());
            preparedStatement.setString(2, data.produto());
            preparedStatement.setString(3, data.partNumber());
            preparedStatement.setString(4, data.divisao());
            preparedStatement.setInt(5, data.quantidade());

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Produto> listar(){
        ArrayList<Produto> produtos = new ArrayList<>();

        String sql = """
                SELECT * FROM tb_materiais_diretos
                """;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String produto = resultSet.getNString(2);
                String partNumber = resultSet.getNString(3);
                String divisao = resultSet.getNString(4);
                int qtd = resultSet.getInt(5);

                DadosProduto dadosProduto = new DadosProduto(id, produto, partNumber, divisao, qtd);
                Produto produto1 = new Produto(dadosProduto);

                produtos.add(produto1);
            }
            preparedStatement.close();
            connection.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return produtos;
    }

    public Produto listarPorId(int id){
        String sql = """
                SELECT * FROM tb_materiais_diretos WHERE id = ?
                """;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Produto produto = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int numeroID = resultSet.getInt(1);
                String nomeProduto = resultSet.getNString(2);
                String partNumber = resultSet.getNString(3);
                String divisao = resultSet.getNString(4);
                int quantidade = resultSet.getInt(5);
                
                DadosProduto dadosProduto = new DadosProduto(numeroID, nomeProduto, partNumber, divisao, quantidade);
                produto = new Produto(dadosProduto);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return produto;
    }
    public void alterar(int id, int quantidade){
        PreparedStatement preparedStatement;
        String sql = "UPDATE tb_materiais_diretos SET quantidade = ? WHERE id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, quantidade);
            preparedStatement.setInt(2, id);

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void deletar(int id){
        String sql = "DELETE FROM tb_materiais_diretos WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

            preparedStatement.close();
            connection.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
