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

    // Fila 1: pacientes aguardando atendimento na recepcao.
    private final Queue<Paciente> filaAtendimento =
            new LinkedList<>();

    // Fila 2: pacientes aguardando consulta medica.
    private final Queue<Paciente> filaConsultas =
            new ArrayDeque<>();

    // Lista dos pacientes que ja foram chamados para consulta.
    private final List<Paciente> atendidos =
            new ArrayList<>();

    private static final int LIMITE_CONSULTAS = 5;

    private static final DateTimeFormatter FORMATO_DATA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private long tempoTotalEsperaSegundos = 0;

    // Cadastra um paciente na fila de atendimento.
    public void retirarSenha(String nome, String cpf) {
        Paciente paciente = new Paciente(nome, cpf);

        filaAtendimento.offer(paciente);

        InterfaceConsole.sucesso(
                "Paciente cadastrado com sucesso!"
        );

        exibirDadosPaciente(paciente);
    }

    // Transfere o proximo paciente da recepcao para consultas.
    public void atenderRecepcao() {

        if (filaAtendimento.isEmpty()) {
            InterfaceConsole.aviso(
                    "Nao ha pacientes aguardando na recepcao."
            );
            return;
        }

        // A fila de consultas nao pode ultrapassar o limite.
        if (filaConsultas.size() >= LIMITE_CONSULTAS) {
            InterfaceConsole.aviso(
                    "Fila de consultas cheia! Limite de "
                    + LIMITE_CONSULTAS + " pacientes."
            );
            return;
        }

        Paciente paciente = filaAtendimento.poll();

        if (paciente == null) {
            InterfaceConsole.aviso(
                    "Nao foi possivel retirar o paciente da recepcao."
            );
            return;
        }

        filaConsultas.offer(paciente);

        InterfaceConsole.sucesso(
                "Paciente encaminhado para a fila de consultas!"
        );

        exibirDadosPaciente(paciente);
    }

    // Chama o proximo paciente da fila de consultas.
    public void chamarConsulta() {

        Paciente paciente = filaConsultas.poll();

        if (paciente == null) {
            InterfaceConsole.aviso(
                    "Nao ha pacientes aguardando consulta."
            );
            return;
        }

        // Calcula o tempo desde a chegada ate a chamada para consulta.
        Duration tempoEspera = Duration.between(
                paciente.getHoraChegada(),
                LocalDateTime.now()
        );

        long segundos = Math.max(0, tempoEspera.getSeconds());

        tempoTotalEsperaSegundos += segundos;
        atendidos.add(paciente);

        InterfaceConsole.sucesso(
                "Paciente chamado para consulta!"
        );

        exibirDadosPaciente(paciente);

        System.out.println(
                "  Tempo de espera: " + segundos + " segundos"
        );
    }

    // Lista os pacientes sem alterar a ordem da fila.
    private void listarFila(
            Queue<Paciente> fila,
            String titulo) {

        InterfaceConsole.exibirCabecalhoFila(
                titulo,
                fila.size()
        );

        if (fila.isEmpty()) {
            InterfaceConsole.aviso(
                    "Nao ha pacientes nesta fila."
            );
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

    // Mostra o primeiro paciente de cada fila sem remove-lo.
    public void verProximos() {

        System.out.println("\n  PROXIMOS PACIENTES");
        InterfaceConsole.separador();

        Paciente proximoAtendimento =
                filaAtendimento.peek();

        Paciente proximaConsulta =
                filaConsultas.peek();

        System.out.println("\n  RECEPCAO");

        if (proximoAtendimento == null) {
            InterfaceConsole.aviso(
                    "Nao ha ninguem na fila!"
            );
        } else {
            exibirDadosPaciente(proximoAtendimento);
        }

        System.out.println("\n  CONSULTAS");

        if (proximaConsulta == null) {
            InterfaceConsole.aviso(
                    "Nao ha ninguem na fila!"
            );
        } else {
            exibirDadosPaciente(proximaConsulta);
        }
    }

    // Demonstra o comportamento de remove() e element()
    // quando a fila esta vazia.
    public void demonstrarExcecao() {

        Queue<Paciente> filaTeste = new LinkedList<>();

        System.out.println("\n  DEMONSTRACAO DE EXCECOES");
        InterfaceConsole.separador();

        try {
            filaTeste.element();
        } catch (NoSuchElementException e) {
            InterfaceConsole.aviso(
                    "element(): lancou NoSuchElementException "
                    + "porque a fila esta vazia."
            );
        }

        try {
            filaTeste.remove();
        } catch (NoSuchElementException e) {
            InterfaceConsole.aviso(
                    "remove(): lancou NoSuchElementException "
                    + "porque a fila esta vazia."
            );
        }
    }

    // Exibe o relatorio geral.
    public void gerarRelatorio() {

        System.out.println("\n  RELATORIO DE ATENDIMENTOS");
        InterfaceConsole.separador();

        System.out.println(
                "  Pacientes atendidos: " + atendidos.size()
        );

        System.out.println(
                "  Aguardando recepcao: "
                + filaAtendimento.size()
        );

        System.out.println(
                "  Aguardando consulta: "
                + filaConsultas.size()
                + "/" + LIMITE_CONSULTAS
        );

        double tempoMedio = atendidos.isEmpty()
                ? 0
                : (double) tempoTotalEsperaSegundos
                / atendidos.size();

        System.out.printf(
                "  Tempo medio de espera: %.2f segundos%n",
                tempoMedio
        );

        InterfaceConsole.separador();
    }

    // Centraliza a exibicao dos dados do paciente.
    private void exibirDadosPaciente(Paciente paciente) {

        InterfaceConsole.exibirPaciente(
                paciente.getSenha(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getHoraChegada().format(FORMATO_DATA)
        );
    }

    // Metodos auxiliares usados pelo menu e pelos testes.

    public int getQuantidadeAtendimento() {
        return filaAtendimento.size();
    }

    public int getQuantidadeConsultas() {
        return filaConsultas.size();
    }

    public int getQuantidadeAtendidos() {
        return atendidos.size();
    }

    public Paciente getProximoAtendimento() {
        return filaAtendimento.peek();
    }

    public Paciente getProximaConsulta() {
        return filaConsultas.peek();
    }

    public List<Paciente> getAtendidos() {
        return List.copyOf(atendidos);
    }
}