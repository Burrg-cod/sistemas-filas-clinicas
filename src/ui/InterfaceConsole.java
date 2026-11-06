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
    System.out.println(AZUL + "  CLINICA | CONTROLE DE FILAS" + RESET);
    System.out.println(CINZA + "  --------------------------------" + RESET);
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

    public static void limparTela() {
        System.out.print("\033[2J\033[3J\033[H");
        System.out.flush();
    }

    public static void aguardarEnter(java.util.Scanner scanner) {
        System.out.println();
        System.out.println(CINZA + "Pressione Enter para voltar ao menu..." + RESET);
        scanner.nextLine();
    }
    
public static void exibirCabecalhoFila(String titulo, int quantidade) {
    System.out.println();
    System.out.println(AZUL + "  " + titulo + RESET);
    System.out.println(CINZA + "  ----------------------------------------" + RESET);
    System.out.println("  Pacientes na fila: " + quantidade);
    System.out.println();
}

public static void exibirPaciente(
        int senha,
        String nome,
        String cpf,
        String horaChegada) {

    System.out.printf(AZUL + "  Senha #%03d%n" + RESET, senha);
    System.out.println("  Nome:    " + nome);
    System.out.println("  CPF:     " + cpf);
    System.out.println("  Chegada: " + horaChegada);
    System.out.println(CINZA + "  ----------------------------------------" + RESET);
}

}
