
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ServicoClinica clinica = new ServicoClinica();

        int opcao = -1;

        do {
            System.out.println("\n===== SISTEMA DA CLINICA =====");
            System.out.println("1 - Retirar senha");
            System.out.println("2 - Chamar proximo na recepcao");
            System.out.println("3 - Chamar proximo para consulta");
            System.out.println("4 - Listar fila de atendimento");
            System.out.println("5 - Listar fila de consultas");
            System.out.println("6 - Ver proximo de cada fila");
            System.out.println("7 - Relatorio");
            System.out.println("8 - Demonstrar excecoes");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");

            String entrada = scanner.nextLine();

            try {
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero valido!");
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Nome do paciente: ");
                    String nome = scanner.nextLine();

                    System.out.print("CPF do paciente: ");
                    String cpf = scanner.nextLine();

                    if (nome.isBlank() || cpf.isBlank()) {
                        System.out.println("Nome e CPF sao obrigatorios!");
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
                    System.out.println("Encerrando sistema...");
                    break;

                default:
                    System.out.println("Opcao invalida!");
            }

        } while (opcao != 0);

        scanner.close();
    }
}
