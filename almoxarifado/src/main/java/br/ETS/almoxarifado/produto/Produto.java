package br.ETS.almoxarifado.produto;

import java.util.Objects;

public class Produto {
    private int id;
    private String produto;
    private String partNumber;
    private String divisao;
    private int quantidade;

    public Produto(DadosProduto data) {
        this.id = data.id();
        this.produto = data.produto();
        this.partNumber = data.partNumber();
        this.divisao = data.divisao();
        this.quantidade = data.quantidade();
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getDivisao() {
        return divisao;
    }

    public void setDivisao(String divisao) {
        this.divisao = divisao;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return id == produto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", produto='" + produto + '\'' +
                ", partNumber='" + partNumber + '\'' +
                ", divisao='" + divisao + '\'' +
                ", quantidade=" + quantidade +
                '}';
    }
}
