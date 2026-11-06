
package ui;

public class InterfaceConsole {

    // Cores ANSI
    private static final String RESET = "\u001B[0m";
    private static final String AZUL = "\u001B[94m";
    private static final String VERDE = "\u001B[92m";
    private static final String AMARELO = "\u001B[93m";
    private static final String VERMELHO = "\u001B[91m";
    private static final String CINZA = "\u001B[90m";

    public static void exibirTitulo() {
        System.out.println();
        System.out.println(AZUL + "========================================" + RESET);
        System.out.println(AZUL + "       SISTEMA DE ATENDIMENTO" + RESET);
        System.out.println(AZUL + "========================================" + RESET);
        System.out.println(CINZA + "       Gerenciamento de pacientes" + RESET);
        System.out.println();
    }

    public static void exibirMenu() {
        System.out.println(AZUL + "--------------- MENU -------------------" + RESET);
        System.out.println("  1  | Cadastrar paciente");
        System.out.println("  2  | Atender na recepcao");
        System.out.println("  3  | Chamar para consulta");
        System.out.println("  4  | Listar fila da recepcao");
        System.out.println("  5  | Listar fila de consultas");
        System.out.println("  6  | Consultar proximos");
        System.out.println("  7  | Relatorio");
        System.out.println("  8  | Demonstrar excecoes");
        System.out.println("  0  | Sair");
        System.out.println(AZUL + "----------------------------------------" + RESET);
        System.out.print("  Escolha uma opcao: ");
    }

    public static void sucesso(String mensagem) {
        System.out.println(VERDE + "[OK] " + mensagem + RESET);
    }

    public static void aviso(String mensagem) {
        System.out.println(AMARELO + "[AVISO] " + mensagem + RESET);
    }

    public static void erro(String mensagem) {
        System.out.println(VERMELHO + "[ERRO] " + mensagem + RESET);
    }

    public static void separador() {
        System.out.println(CINZA + "----------------------------------------" + RESET);
    }
}
