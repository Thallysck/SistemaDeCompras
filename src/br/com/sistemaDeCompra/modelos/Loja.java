package br.com.sistemaDeCompra.modelos;

import java.util.ArrayList;

public class Loja {
    String nome;
    ArrayList<Produto> produtos = new ArrayList<>();

    public Loja(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void adicionarProduto(Produto produto){
        this.produtos.add(produto);
    }
    
    public boolean validarProduto(String nomeProduto){
        boolean validacao = false;
        for (Produto produto: produtos) {
            if (produto.getNome().equalsIgnoreCase(nomeProduto)){
                validacao = true;
                break;
            }
        }
            return validacao;
    }

    public Boolean vender(Cartao cartao, Produto produto, String senha){
        if (this.validarProduto(produto.getNome())){
            boolean validador;
            validador = cartao.passarNaMaquineta(produto, senha);
            return validador;
        } else {
            System.out.println("Produto inexistente!");
            return false;
        }
    }

    @Override
    public String toString() {
        return "Nome: " + nome;
    }
}


