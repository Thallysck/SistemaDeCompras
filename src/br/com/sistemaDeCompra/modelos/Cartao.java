package br.com.sistemaDeCompra.modelos;

import java.util.ArrayList;
import java.util.List;

public class Cartao {
    private String numero;
    private String senha;
    private double limite;
    private boolean bloqueado;
    private ArrayList<Produto> compras;

    public Cartao(String numero, String senha, double limite, boolean bloqueado) {
        this.numero = numero;
        this.senha = senha;
        this.limite = limite;
        this.bloqueado = bloqueado;
        this.compras = new ArrayList<>();
    }

    public ArrayList<Produto> getCompras() {
        return compras;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public boolean passarNaMaquineta(Produto produto, String senha){
        if (isBloqueado() || getLimite() < produto.getValor() || !senha.equals(this.senha)){
            System.out.println("Não é possível realizar compra!");
            return false;
        } else {
            this.setLimite(this.getLimite() - produto.getValor());
            System.out.println("Compra realizada com sucesso!");
            compras.add(produto);
            return true;
        }
    }

    public void alterarSenhaCartao(Cartao cartao, String senhaAtual, String senhaNova){
        if (senhaAtual.equals(cartao.getSenha()) && !senhaAtual.equals(senhaNova) && (!senhaAtual.isEmpty() || !senhaNova.isEmpty())){
            cartao.setSenha(senhaNova);
            System.out.println("Senha atualizada com sucesso!");
        }else {
            System.out.println("Impossível realizar essa operação");
        }
    }

    public void alterarLimiteCartao(Cartao cartao, double novoLimite){
        if (novoLimite > 0) {
            cartao.setLimite(novoLimite);
        } else {
            System.out.println("Informe um valor maior que 0");
        }
    }
}
