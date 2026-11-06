package ui;

public class InterfaceConsole {

    private static final String RESET = "\u001B[0m";
    private static final String AZUL = "\u001B[38;5;75m";
    private static final String CIANO = "\u001B[38;5;80m";
    private static final String VERDE = "\u001B[38;5;114m";
    private static final String AMARELO = "\u001B[38;5;221m";
    private static final String VERMELHO = "\u001B[38;5;203m";
    private static final String CINZA = "\u001B[38;5;245m";
    private static final String BRANCO = "\u001B[97m";
    private static final String NEGRITO = "\u001B[1m";

    private static final String LINHA =
            "============================================================";

    private static final String LINHA_FINA =
            "------------------------------------------------------------";

    private InterfaceConsole() {
        // Classe utilitaria.
    }

    public static void exibirTitulo() {
        System.out.println();

        System.out.println(AZUL + "  " + LINHA + RESET);

        System.out.println(
                AZUL + "  ||" + RESET
                + CIANO + NEGRITO
                + "                 CLINICA VIDA+                         "
                + RESET + AZUL + "||" + RESET
        );

        System.out.println(
                AZUL + "  ||" + RESET
                + BRANCO
                + "          SISTEMA DE FILAS DE ATENDIMENTO              "
                + RESET + AZUL + "||" + RESET
        );

        System.out.println(AZUL + "  " + LINHA + RESET);

        System.out.println(
                CINZA
                + "       Recepcao  >  Triagem  >  Consultas medicas"
                + RESET
        );

        System.out.println();
    }

    public static void exibirMenu(
            int aguardandoRecepcao,
            int aguardandoConsulta,
            int limiteConsultas,
            int totalAtendidos) {

        exibirPainel("RESUMO DO ATENDIMENTO");

        System.out.printf(
                "  %-27s %s%3d%s%n",
                "Pacientes na recepcao:",
                AZUL + NEGRITO,
                aguardandoRecepcao,
                RESET
        );

        System.out.printf(
                "  %-27s %s%3d/%-3d%s%n",
                "Fila de consultas:",
                CIANO + NEGRITO,
                aguardandoConsulta,
                limiteConsultas,
                RESET
        );

        System.out.printf(
                "  %-27s %s%3d%s%n",
                "Consultas concluidas:",
                VERDE + NEGRITO,
                totalAtendidos,
                RESET
        );

        System.out.println(CINZA + "  " + LINHA_FINA + RESET);
        System.out.println(AZUL + NEGRITO + "  MENU PRINCIPAL" + RESET);
        System.out.println();

        opcao("1", "Retirar senha",
                "Cadastrar paciente na recepcao");

        opcao("2", "Chamar proximo na recepcao",
                "Encaminhar paciente para consultas");

        opcao("3", "Chamar para consulta",
                "Registrar atendimento");

        opcao("4", "Listar fila de atendimento",
                "Pacientes aguardando na recepcao");

        opcao("5", "Listar fila de consultas",
                "Pacientes aguardando o medico");

        opcao("6", "Ver proximos pacientes",
                "Consultar o primeiro de cada fila");

        opcao("7", "Relatorio geral",
                "Estatisticas e tempo medio de espera");

        opcao("8", "Demonstrar excecoes Queue",
                "Exemplo de remove() e element()");

        System.out.println();
        System.out.println(VERMELHO + "  [0]" + RESET
                + " Sair do sistema");

        System.out.println();
        System.out.println(CINZA + "  " + LINHA_FINA + RESET);

        System.out.print(
                CIANO + NEGRITO + "  Escolha uma opcao > " + RESET
        );
    }

    public static void exibirMenu() {
        exibirMenu(0, 0, 5, 0);
    }

    private static void opcao(
            String numero,
            String titulo,
            String descricao) {

        System.out.printf(
                "  %s[%s]%s %-29s %s%s%n",
                CIANO + NEGRITO,
                numero,
                RESET,
                titulo,
                CINZA,
                descricao + RESET
        );
    }

    public static void sucesso(String mensagem) {
        System.out.println();
        System.out.println(
                VERDE + NEGRITO + "  [OK] " + RESET
                + VERDE + mensagem + RESET
        );
    }

    public static void aviso(String mensagem) {
        System.out.println();
        System.out.println(
                AMARELO + NEGRITO + "  [ATENCAO] " + RESET
                + AMARELO + mensagem + RESET
        );
    }

    public static void erro(String mensagem) {
        System.out.println();
        System.out.println(
                VERMELHO + NEGRITO + "  [ERRO] " + RESET
                + VERMELHO + mensagem + RESET
        );
    }

    public static void separador() {
        System.out.println(CINZA + "  " + LINHA_FINA + RESET);
    }

    public static void exibirPainel(String titulo) {
        System.out.println(AZUL + NEGRITO + "  " + titulo + RESET);
        separador();
    }

    public static void limparTela() {
        System.out.print("\u001B[2J\u001B[H");
        System.out.flush();
    }

    public static void aguardarEnter(java.util.Scanner scanner) {
        System.out.println();
        System.out.print(
                CINZA + "  Pressione Enter para voltar ao menu..."
                + RESET
        );
        scanner.nextLine();
    }

    public static void exibirCabecalhoFila(
            String titulo,
            int quantidade) {

        System.out.println();
        System.out.println(AZUL + "  " + LINHA + RESET);
        System.out.println(AZUL + NEGRITO + "  " + titulo + RESET);

        System.out.printf(
                "  Pacientes aguardando: %s%d%s%n",
                CIANO + NEGRITO,
                quantidade,
                RESET
        );

        System.out.println(CINZA + "  " + LINHA_FINA + RESET);
    }

    public static void exibirPaciente(
            int senha,
            String nome,
            String cpf,
            String horaChegada) {

        System.out.printf(
                "  %s SENHA #%03d %s%n",
                AZUL + NEGRITO,
                senha,
                RESET
        );

        System.out.printf("  %-12s %s%n", "Paciente:", nome);
        System.out.printf("  %-12s %s%n", "CPF:", cpf);
        System.out.printf("  %-12s %s%n", "Chegada:", horaChegada);

        System.out.println(CINZA + "  " + LINHA_FINA + RESET);
    }
}