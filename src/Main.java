import br.com.sistemaDeCompra.modelos.Loja;
import br.com.sistemaDeCompra.modelos.Pessoa;
import br.com.sistemaDeCompra.modelos.Produto;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        //Criação da pessoa
        Pessoa vanina = new Pessoa("Vanina", 24, "Feminino");
        vanina.gerarCartao("07", "1234");
        Pessoa thallys = new Pessoa("Thallys", 23, "Masculino");
        thallys.gerarCartao("08", "1234");

        ArrayList<Pessoa> clientes = new ArrayList<Pessoa>();
        clientes.add(vanina);
        clientes.add(thallys);

        //Criação das lojas
        ArrayList<Loja> lojas = new ArrayList<Loja>();

        Loja americanas = new Loja("Americanas");

        Loja havan = new Loja("Havan");
        Loja vfLookinhos = new Loja("VF Lookinhos");

        lojas.add(americanas);
        lojas.add(havan);
        lojas.add(vfLookinhos);

        //Criação de produtos para as lojas
        Produto camisa = new Produto("Camisa", 10);
        Produto amendoin = new Produto("Amendoin", 4.99);
        Produto copo = new Produto("Copo", 20.99);
        Produto bola = new Produto("Bola", 130);
        Produto biquini = new Produto("Biquini", 199.99);

        //Adicionando produtos nas lojas
        americanas.adicionarProduto(amendoin);
        americanas.adicionarProduto(camisa);
        americanas.adicionarProduto(copo);
        americanas.adicionarProduto(bola);
        americanas.adicionarProduto(biquini);

        vfLookinhos.adicionarProduto(biquini);

        havan.adicionarProduto(amendoin);
        havan.adicionarProduto(camisa);
        havan.adicionarProduto(copo);
        havan.adicionarProduto(bola);
        havan.adicionarProduto(biquini);

        //Loop de compras
        int opcao = 1;
        int pessoa;
        int  loja;
        String produtoDesejado;
        ArrayList<Produto> compras = new ArrayList<Produto>();

        System.out.println(clientes);
        System.out.println("Escolha a pessoa para realizar a compra [Informar posicao(1,2)]: ");
        pessoa = input.nextInt()-1;

        System.out.println("Informe o novo limite do cartão: ");
        double novoLimite = input.nextDouble();
        clientes.get(pessoa).solicitarAlteracaoLimite(novoLimite);

        while (opcao !=0){

            System.out.println("""
                    1 - Comprar
                    2 - Ajustar cartão
                    0 - Sair
                    """);
            opcao = input.nextInt();

            if (opcao == 1){
                System.out.println("Lojas disponíveis: ");
                System.out.println(lojas);
                System.out.println("Escolha a loja para realizar a compra [Informar posicao](1,2,3): ");
                loja = input.nextInt()-1;

                if (loja <= lojas.size() && loja >= 0) {
                    ArrayList<Produto> produtos = new ArrayList<Produto>();
                    produtos = lojas.get(loja).getProdutos();
                    System.out.println("Produtos disponíveis: ");
                    System.out.println(produtos);
                    System.out.println("Informe o nome do produto desejado: ");
                    produtoDesejado = input.next();

                    for (Produto produto: produtos){
                        if (produto.getNome().equalsIgnoreCase(produtoDesejado)){
                            System.out.println("Informe a senha do cartão: ");
                            String senhaDoCartao;
                            senhaDoCartao = input.next();
                            boolean validadorDeCompra = clientes.get(pessoa).comprar(lojas.get(loja), produto, senhaDoCartao);
                            if (validadorDeCompra) {
                                compras.add(produto);
                            }else {
                                opcao = 0;
                            }

                        }
                    }
                }else {
                    System.out.println("Loja invalida!");
                }
            } else if (opcao == 2) {
                System.out.println("Escolha uma opção: ");
                System.out.println("""
                        1 - Alterar senha
                        0 - Sair
                        """);
                int opcaoCartao = input.nextInt();

                if (opcaoCartao == 1) {
                    //Código para alterar senha
                    System.out.println("Informe a senha atual do cartao: ");
                    String senhaAtual = input.next();
                    System.out.println("Informe a nova senha: ");
                    String senhaNova = input.next();
                    clientes.get(pessoa).alterarSenha(senhaAtual, senhaNova);
                } else {
                    break;
                }

            }
        }

        compras = clientes.get(pessoa).extratoCompras();
        Collections.sort(compras);

        System.out.println("""
                **************************
                Compras realizadas:
                """);
        for (Produto produto: compras) {
            System.out.println(produto);
        }

        System.out.println("\n**************************");

        System.out.println("\nSaldo do cartao: " + clientes.get(pessoa).consultarLimite());

        }

}


