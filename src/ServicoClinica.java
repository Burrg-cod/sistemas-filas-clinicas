
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import ui.InterfaceConsole;

public class ServicoClinica {

    private final Queue<Paciente> filaAtendimento = new LinkedList<>();
    private final Queue<Paciente> filaConsultas = new ArrayDeque<>();
    private final List<Paciente> atendidos = new ArrayList<>();

    private static final int LIMITE_CONSULTAS = 5;

    private static final DateTimeFormatter FORMATO_DATA =
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private long tempoTotalEsperaSegundos = 0;

    // Cadastra um novo paciente na fila de atendimento
    public void retirarSenha(String nome, String cpf) {
        Paciente paciente = new Paciente(nome, cpf);
        filaAtendimento.offer(paciente);

        InterfaceConsole.sucesso("Paciente cadastrado com sucesso!");
        exibirDadosPaciente(paciente);
    }

    // Encaminha o proximo paciente da recepcao para consultas
    public void atenderRecepcao() {
        if (filaAtendimento.isEmpty()) {
            InterfaceConsole.aviso("Nao ha pacientes aguardando na recepcao.");
            return;
        }

        // Verifica o limite antes de remover da primeira fila
        if (filaConsultas.size() >= LIMITE_CONSULTAS) {
            InterfaceConsole.aviso(
                "Fila de consultas cheia! Limite de "
                + LIMITE_CONSULTAS + " pacientes."
            );
            return;
        }

        Paciente paciente = filaAtendimento.poll();
        filaConsultas.offer(paciente);

        InterfaceConsole.sucesso(
            "Paciente encaminhado para a fila de consultas!"
        );
        exibirDadosPaciente(paciente);
    }

    // Chama o proximo paciente para consulta
    public void chamarConsulta() {
        Paciente paciente = filaConsultas.poll();

        if (paciente == null) {
            InterfaceConsole.aviso("Nao ha pacientes aguardando consulta.");
            return;
        }

        // Calcula o tempo entre a chegada e a chamada para consulta
        Duration tempoEspera = Duration.between(
            paciente.getHoraChegada(),
            LocalDateTime.now()
        );

        long segundos = Math.max(0, tempoEspera.getSeconds());

        tempoTotalEsperaSegundos += segundos;
        atendidos.add(paciente);

        InterfaceConsole.sucesso("Paciente chamado para consulta!");
        exibirDadosPaciente(paciente);

        System.out.println(
            "  Tempo de espera: " + segundos + " segundos"
        );
    }

    // Exibe os pacientes de uma fila sem alterar sua ordem
    private void listarFila(Queue<Paciente> fila, String titulo) {
        InterfaceConsole.exibirCabecalhoFila(titulo, fila.size());

        if (fila.isEmpty()) {
            InterfaceConsole.aviso("Nao ha pacientes nesta fila.");
            return;
        }

        for (Paciente paciente : fila) {
            exibirDadosPaciente(paciente);
        }
    }

    public void listarAtendimento() {
        listarFila(
            filaAtendimento,
            "PACIENTES AGUARDANDO RECEPCAO"
        );
    }

    public void listarConsultas() {
        listarFila(
            filaConsultas,
            "PACIENTES AGUARDANDO CONSULTA"
        );
    }

    // Consulta os proximos pacientes sem remove-los das filas
    public void verProximos() {
        System.out.println("\n  PROXIMOS PACIENTES");
        InterfaceConsole.separador();

        Paciente proximoAtendimento = filaAtendimento.peek();
        Paciente proximaConsulta = filaConsultas.peek();

        System.out.println("\n  RECEPCAO");

        if (proximoAtendimento == null) {
            InterfaceConsole.aviso("Nao ha ninguem na fila!");
        } else {
            exibirDadosPaciente(proximoAtendimento);
        }

        System.out.println("\n  CONSULTAS");

        if (proximaConsulta == null) {
            InterfaceConsole.aviso("Nao ha ninguem na fila!");
        } else {
            exibirDadosPaciente(proximaConsulta);
        }
    }

    // Demonstra o tratamento de excecoes da interface Queue
    public void demonstrarExcecao() {
        Queue<Paciente> filaTeste = new LinkedList<>();

        System.out.println("\n  DEMONSTRACAO DE EXCECOES");
        InterfaceConsole.separador();

        try {
            filaTeste.element();
        } catch (NoSuchElementException e) {
            InterfaceConsole.aviso(
                "element(): lancou NoSuchElementException porque a fila esta vazia."
            );
        }

        try {
            filaTeste.remove();
        } catch (NoSuchElementException e) {
            InterfaceConsole.aviso(
                "remove(): lancou NoSuchElementException porque a fila esta vazia."
            );
        }
    }

    // Exibe o relatorio geral do sistema
    public void gerarRelatorio() {
        System.out.println("\n  RELATORIO DE ATENDIMENTOS");
        InterfaceConsole.separador();

        System.out.println(
            "  Pacientes atendidos: " + atendidos.size()
        );

        System.out.println(
            "  Aguardando recepcao: " + filaAtendimento.size()
        );

        System.out.println(
            "  Aguardando consulta: " + filaConsultas.size()
            + "/" + LIMITE_CONSULTAS
        );

        double tempoMedio = atendidos.isEmpty()
            ? 0
            : (double) tempoTotalEsperaSegundos / atendidos.size();

        System.out.printf(
            "  Tempo medio de espera: %.2f segundos%n",
            tempoMedio
        );

        InterfaceConsole.separador();
    }

    // Centraliza a exibicao dos dados de um paciente
    private void exibirDadosPaciente(Paciente paciente) {
        InterfaceConsole.exibirPaciente(
            paciente.getSenha(),
            paciente.getNome(),
            paciente.getCpf(),
            paciente.getHoraChegada().format(FORMATO_DATA)
        );
    }
}
