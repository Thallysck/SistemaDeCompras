package br.com.sistemaDeCompra.modelos;

import java.util.ArrayList;
import java.util.List;

public class Pessoa {
    private String nome;
    private int idade;
    private String sexo;
    private Cartao cartao;

    public Pessoa(String nome, int idade, String sexo) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void gerarCartao(String numeroCartao, String senha){
        this.cartao = new Cartao(numeroCartao,senha, 1000, false);
    }

    public Boolean comprar(Loja loja, Produto produto, String senha){
        return loja.vender(this.cartao, produto, senha);
    }

    public void alterarSenha(String senhaAtual, String senhaNova){
        this.cartao.alterarSenhaCartao(this.cartao, senhaAtual, senhaNova);
    }

    public void solicitarAlteracaoLimite(double novoLimite){
        this.cartao.alterarLimiteCartao(this.cartao, novoLimite);
    }

    public double consultarLimite(){
        return this.cartao.getLimite();
    }

    public List<Produto> extratoCompras(){
        return this.cartao.getCompras();
    }

    @Override
    public String toString() {
        return "Nome: " + nome;
    }
}
