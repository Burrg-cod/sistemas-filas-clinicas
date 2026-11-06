import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class TestesClinica {

    private static int aprovados = 0;
    private static int reprovados = 0;

    public static void main(String[] args) {

        executar(
                "Fila vazia",
                TestesClinica::testarFilaVazia
        );

        executar(
                "Fila de consultas cheia",
                TestesClinica::testarFilaCheia
        );

        executar(
                "Fluxo completo",
                TestesClinica::testarFluxoCompleto
        );

        executar(
                "Excecao remove()",
                TestesClinica::testarExcecao
        );

        executar(
                "Ordem FIFO",
                TestesClinica::testarFIFO
        );

        System.out.println("\n===== RESULTADO DOS TESTES =====");
        System.out.println("Aprovados: " + aprovados);
        System.out.println("Reprovados: " + reprovados);

        if (reprovados > 0) {
            System.exit(1);
        }
    }

    private static void executar(
            String nome,
            Runnable teste) {

        try {
            teste.run();
            aprovados++;

            System.out.println("[PASSOU] " + nome);

        } catch (AssertionError | RuntimeException e) {
            reprovados++;

            System.out.println(
                    "[FALHOU] " + nome + ": " + e.getMessage()
            );
        }
    }

    private static void verificar(
            boolean condicao,
            String mensagem) {

        if (!condicao) {
            throw new AssertionError(mensagem);
        }
    }

    private static void testarFilaVazia() {

        ServicoClinica clinica = new ServicoClinica();

        clinica.atenderRecepcao();
        clinica.chamarConsulta();

        verificar(
                clinica.getQuantidadeAtendimento() == 0,
                "Fila de atendimento deveria estar vazia"
        );

        verificar(
                clinica.getQuantidadeConsultas() == 0,
                "Fila de consultas deveria estar vazia"
        );
    }

    private static void testarFilaCheia() {

        ServicoClinica clinica = new ServicoClinica();

        for (int i = 1; i <= 6; i++) {
            clinica.retirarSenha(
                    "Paciente " + i,
                    "CPF" + i
            );
        }

        for (int i = 1; i <= 6; i++) {
            clinica.atenderRecepcao();
        }

        verificar(
                clinica.getQuantidadeConsultas() == 5,
                "Fila de consultas deve ter 5 pacientes"
        );

        verificar(
                clinica.getQuantidadeAtendimento() == 1,
                "Sexto paciente deve permanecer na recepcao"
        );

        verificar(
                clinica.getProximoAtendimento()
                        .getNome()
                        .equals("Paciente 6"),
                "Paciente incorreto permaneceu na recepcao"
        );
    }

    private static void testarFluxoCompleto() {

        ServicoClinica clinica = new ServicoClinica();

        clinica.retirarSenha("Joao", "123");

        verificar(
                clinica.getQuantidadeAtendimento() == 1,
                "Paciente nao entrou na recepcao"
        );

        clinica.atenderRecepcao();

        verificar(
                clinica.getQuantidadeAtendimento() == 0,
                "Paciente nao saiu da recepcao"
        );

        verificar(
                clinica.getQuantidadeConsultas() == 1,
                "Paciente nao entrou na fila de consultas"
        );

        clinica.chamarConsulta();

        verificar(
                clinica.getQuantidadeConsultas() == 0,
                "Paciente nao saiu da fila de consultas"
        );

        verificar(
                clinica.getQuantidadeAtendidos() == 1,
                "Paciente nao foi registrado como atendido"
        );
    }

    private static void testarExcecao() {

        Queue<String> fila = new LinkedList<>();

        boolean excecaoCapturada = false;

        try {
            fila.remove();

        } catch (NoSuchElementException e) {
            excecaoCapturada = true;
        }

        verificar(
                excecaoCapturada,
                "remove() deveria lancar "
                + "NoSuchElementException"
        );
    }

    private static void testarFIFO() {

        ServicoClinica clinica = new ServicoClinica();

        clinica.retirarSenha("Joao", "111");
        clinica.retirarSenha("Maria", "222");
        clinica.retirarSenha("Pedro", "333");

        verificar(
                clinica.getProximoAtendimento()
                        .getNome()
                        .equals("Joao"),
                "Joao deveria ser o primeiro da recepcao"
        );

        clinica.atenderRecepcao();
        clinica.atenderRecepcao();
        clinica.atenderRecepcao();

        verificar(
                clinica.getProximaConsulta()
                        .getNome()
                        .equals("Joao"),
                "Joao deveria ser o primeiro da consulta"
        );

        clinica.chamarConsulta();
        clinica.chamarConsulta();
        clinica.chamarConsulta();

        String[] ordemEsperada = {
            "Joao",
            "Maria",
            "Pedro"
        };

        for (int i = 0; i < ordemEsperada.length; i++) {

            verificar(
                    clinica.getAtendidos()
                            .get(i)
                            .getNome()
                            .equals(ordemEsperada[i]),
                    "Ordem FIFO incorreta na posicao " + i
            );
        }
    }
}