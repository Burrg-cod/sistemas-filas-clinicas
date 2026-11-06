
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

public class ServicoClinica {

    private final Queue<Paciente> filaAtendimento = new LinkedList<>();
    private final Queue<Paciente> filaConsultas = new ArrayDeque<>();
    private final List<Paciente> atendidos = new ArrayList<>();

    private static final int LIMITE_CONSULTAS = 5;

    // Cadastra paciente na fila de atendimento
    public void retirarSenha(String nome, String cpf) {
        Paciente paciente = new Paciente(nome, cpf);
        filaAtendimento.offer(paciente);

        System.out.println("Paciente cadastrado: " + paciente);
    }

    // Transfere paciente da recepcao para consultas
    public void atenderRecepcao() {
        if (filaAtendimento.isEmpty()) {
            System.out.println("Fila de atendimento vazia!");
            return;
        }

        if (filaConsultas.size() >= LIMITE_CONSULTAS) {
            System.out.println("Fila de consultas cheia!");
            return;
        }

        Paciente paciente = filaAtendimento.poll();
        filaConsultas.offer(paciente);

        System.out.println("Encaminhado para consulta: " + paciente);
    }

    // Chama o proximo paciente para o medico
    public void chamarConsulta() {
        Paciente paciente = filaConsultas.poll();

        if (paciente == null) {
            System.out.println("Nenhum paciente aguardando consulta!");
            return;
        }

        atendidos.add(paciente);
        System.out.println("Paciente chamado: " + paciente);
    }

    // Lista os pacientes de uma fila
    public void listarFila(Queue<Paciente> fila) {
        if (fila.isEmpty()) {
            System.out.println("Fila vazia!");
            return;
        }

        for (Paciente paciente : fila) {
            System.out.println(paciente);
        }
    }

    public void listarAtendimento() {
        listarFila(filaAtendimento);
    }

    public void listarConsultas() {
        listarFila(filaConsultas);
    }

    // Consulta o proximo sem remover
    public void verProximos() {
        System.out.println("Proximo na recepcao: "
            + filaAtendimento.peek());

        System.out.println("Proximo na consulta: "
            + filaConsultas.peek());
    }

    // Demonstra tratamento de excecao com remove/element
    public void demonstrarExcecao() {
        Queue<Paciente> filaTeste = new LinkedList<>();

        try {
            filaTeste.element();
        } catch (NoSuchElementException e) {
            System.out.println("element(): fila vazia!");
        }

        try {
            filaTeste.remove();
        } catch (NoSuchElementException e) {
            System.out.println("remove(): fila vazia!");
        }
    }

    public void gerarRelatorio() {
        System.out.println("===== RELATORIO =====");
        System.out.println("Pacientes atendidos: " + atendidos.size());
        System.out.println("Na recepcao: " + filaAtendimento.size());
        System.out.println("Aguardando consulta: " + filaConsultas.size());
    }
}
