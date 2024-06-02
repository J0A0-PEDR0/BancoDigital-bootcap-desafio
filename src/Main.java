import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Cliente cliente = new Cliente();

        try {
            System.out.println("Digite seu nome:");
            String nome = br.readLine();
            cliente.setNome(nome);
        } catch (IOException e) {
            System.out.println("Erro ao ler o nome.");
            return;
        }

        try {
            System.out.println("Digite a senha em quatro números:");
            String senhaInput = br.readLine();
            if (senhaInput.length() != 4) {
                System.out.println("A senha deve ter exatamente 4 caracteres.");
                return;
            }
            cliente.setSenha(senhaInput);
        } catch (IOException e) {
            System.out.println("Erro ao ler a senha.");
            return;
        }

        Conta cc = new ContaCorrente(cliente);
        Conta poupanca = new ContaPoupanca(cliente);

        try {
            System.out.println("Digite o valor a ser depositado:");
            String valorDeposito = br.readLine();
            Integer valor = Integer.parseInt(valorDeposito);

            System.out.println("Digite sua senha:");
            String senhaDepositoInput = br.readLine();

            if (!senhaDepositoInput.equals(cliente.getSenha())) {
                System.out.println("Senha incorreta. Operação cancelada.");
                return;
            }

            cc.depositar(valor);

        } catch (NumberFormatException e) {
            System.out.println("Erro em converter o valor do depósito.");
            return;
        } catch (IOException e) {
            System.out.println("Erro ao ler o valor do depósito.");
            return;
        }

        try {
            System.out.println("Digite o valor de transferência para conta poucança:");
            String valorTransferencia = br.readLine();
            Integer valorTransferenciaInt = Integer.parseInt(valorTransferencia);

            System.out.println("Digite sua senha:");
            String senhaTransferenciaInput = br.readLine();

            if (!senhaTransferenciaInput.equals(cliente.getSenha())) {
                System.out.println("Senha incorreta. Operação cancelada.");
                return;
            }

            cc.transferir(valorTransferenciaInt, poupanca);
        } catch (NumberFormatException e) {
            System.out.println("Erro em converter o valor a ser transferido.");
            return;
        } catch (IOException e) {
            System.out.println("Erro ao ler o valor a ser transferido.");
            return;
        }

        cc.imprimirExtrato();
        poupanca.imprimirExtrato();
    }
}

