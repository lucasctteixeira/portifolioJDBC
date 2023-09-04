package conta;

import cliente.Cliente;
import cliente.DadosCadastroCliente;

import java.math.BigDecimal;
import java.util.Scanner;

public class PizzariaAplicattion {

    private static ContaService service = new ContaService();
    private static Scanner teclado = new Scanner(System.in).useDelimiter("\n");

    public static void main(String[] args) {

    var opcao = exibirMenu();
        while (opcao != 6) {
        try {
            switch (opcao) {
                case 1:
                    abrirConta();
                    break;
                case 2:
                    consultarSaldo();
                    break;
                case 3:
                    depositarConta();
                    break;
                case 4:
                    exibirCardapio();
                    break;
                case 5:
                    deletarConta();
                    break;
            }
        } catch (RegraDeNegocioException e) {
            System.out.println("Erro: " +e.getMessage());
            System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu");
            teclado.next();
        }
        opcao = exibirMenu();
    }

        var opcaoCardapio = exibirCardapio();
        while (opcaoCardapio != 3) {
            try {
                switch (opcaoCardapio) {
                    case 1:
                        abrirConta();
                        break;
                    case 2:
                        exibirMenu();
                        break;
                }
            } catch (RegraDeNegocioException e) {
                System.out.println("Erro: " +e.getMessage());
                System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu");
                teclado.next();
            }
            opcaoCardapio = exibirCardapio();
        }

}

    private static void abrirConta(){
        System.out.println("Digite o nome da conta");
        var usuario = teclado.next();

        System.out.println("Digite o seu nome");
        var nome = teclado.next();

        System.out.println("Digite o seu CPF");
        var cpf = teclado.nextInt();

        System.out.println("Digite o seu endereco");
        var endereco = teclado.next();

        System.out.println("Digite o seu telefone");
        var telefone = teclado.nextInt();


        DadosCadastroCliente dados = new DadosCadastroCliente(nome, cpf, telefone, endereco);
        Cliente cliente = new Cliente(dados);

        Conta conta = new Conta(usuario, BigDecimal.ZERO);

        service.abrir(cliente, conta);
        System.out.println("Conta aberta com sucesso!");
        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();

    }

    private static void consultarSaldo(){
        System.out.println("Digite o nome do usuario");
        var usuario = teclado.next();

        BigDecimal saldo = service.consultar(usuario);

            if (saldo != null) {
                System.out.println("Seu saldo é de " + saldo);
            } else {
                System.out.println("Usuário não encontrado ou saldo não disponivel.");
            }
        System.out.println("Digite uma tecla para voltar ao menu");
        teclado.next();
    }

    private static void depositarConta(){
        System.out.println("Digite o nome do usuario");
        var usuario = teclado.next();

        System.out.println("Digite o valor do deposito");
        var valorDeposito = teclado.nextBigDecimal();

        service.realizarDeposito(usuario, valorDeposito);
        System.out.println("Deposito realizado com sucesso");
        BigDecimal saldo = service.consultar(usuario);

        System.out.println("Seu saldo novo é de: " + saldo);

        System.out.println("Digite uma tecla para voltar ao menu");
        teclado.next();
    }

    private static void deletarConta(){
        System.out.println("Digite o nome do usuario");
        var usuario = teclado.next();

        service.deleteConta(usuario);
        System.out.println("Conta deletada com sucesso");

        System.out.println("Digite uma tecla para voltar ao menu");
        teclado.next();
    }


    private static int exibirCardapio() {
        System.out.println("""
                PIZZARIA ERECHIM - Cardapio:
                
                - Pizza de Bacon: 75 R$
                - Pizza de Strogonof: 90 R$
                - Pizza de Quatro Queijos: 60 R$
                - Pizza de Camarão: 100 R$
                - Pizza de Coração: 80 R$
                - Pizza de Chocolate: 80 R$
                - Pizza de Sorvete: 88 R$
                
                1 - Fazer Pedido
                2 - Voltar
                3 - Sair
                """);
        return teclado.nextInt();
    }

    private static int exibirMenu() {
        System.out.println("""
                PIZZARIA ERECHIM - ESCOLHA UMA OPÇÃO:
                1 - Criar conta
                2 - Consultar saldo
                3 - Depositar na Conta
                4 - Exibir Cardapio
                5 - Encerrar conta
                6 - Sair
                """);
        return teclado.nextInt();
    }

}
