import java.util.Scanner;
import ui.InterfaceConsole;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ServicoClinica clinica = new ServicoClinica();

        int opcao = -1;

        do {
            InterfaceConsole.exibirTitulo();
            InterfaceConsole.exibirMenu();

            String entrada = scanner.nextLine();

            try {
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                InterfaceConsole.erro("Digite um numero valido!");
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.print("\nNome do paciente: ");
                    String nome = scanner.nextLine();

                    System.out.print("CPF do paciente: ");
                    String cpf = scanner.nextLine();

                    if (nome.isBlank() || cpf.isBlank()) {
                        InterfaceConsole.erro(
                            "Nome e CPF sao obrigatorios!"
                        );
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
                    InterfaceConsole.sucesso("Sistema encerrado.");
                    break;

                default:
                    InterfaceConsole.aviso("Opcao invalida!");
            }

            if (opcao != 0) {
                InterfaceConsole.separador();
            }

        } while (opcao != 0);

        scanner.close();
    }
}
