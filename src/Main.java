
import java.util.Scanner;
import ui.InterfaceConsole;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ServicoClinica clinica = new ServicoClinica();

        int opcao = -1;

        do {
            InterfaceConsole.limparTela();
            InterfaceConsole.exibirTitulo();
            InterfaceConsole.exibirMenu(
                clinica.getQuantidadeAtendimento(),
                clinica.getQuantidadeConsultas(),
                5,
                clinica.getQuantidadeAtendidos()
            );

            String entrada = scanner.nextLine();

            try {
                opcao = Integer.parseInt(entrada.trim());
            } catch (NumberFormatException e) {
                InterfaceConsole.erro("Digite um numero valido.");
                InterfaceConsole.aguardarEnter(scanner);
                continue;
            }

            InterfaceConsole.limparTela();

            switch (opcao) {
                case 1:
                    InterfaceConsole.exibirTitulo();
                    InterfaceConsole.exibirPainel("CADASTRO DE PACIENTE");

                    System.out.print("  Nome do paciente: ");
                    String nome = scanner.nextLine().trim();

                    System.out.print("  CPF do paciente: ");
                    String cpf = scanner.nextLine().trim();

                    if (nome.isBlank() || cpf.isBlank()) {
                        InterfaceConsole.erro("Nome e CPF sao obrigatorios.");
                        break;
                    }

                    clinica.retirarSenha(nome, cpf);
                    break;

                case 2:
                    clinica.atenderRecepcao();
                    break;

                case 3:
                    clinica.chamarConsulta();
                    break;

                case 4:
                    clinica.listarAtendimento();
                    break;

                case 5:
                    clinica.listarConsultas();
                    break;

                case 6:
                    clinica.verProximos();
                    break;

                case 7:
                    clinica.gerarRelatorio();
                    break;

                case 8:
                    clinica.demonstrarExcecao();
                    break;

                case 0:
                    InterfaceConsole.sucesso("Sistema encerrado. Obrigado por utilizar a Clinica Vida+.");
                    break;

                default:
                    InterfaceConsole.aviso("Opcao invalida. Escolha uma opcao de 0 a 8.");
            }

            if (opcao != 0) {
                InterfaceConsole.aguardarEnter(scanner);
            }

        } while (opcao != 0);

        scanner.close();
    }
}
