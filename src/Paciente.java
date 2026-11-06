
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Paciente {

    // Gera senhas sequenciais automaticamente
    private static int proximaSenha = 1;

    private final int senha;
    private final String nome;
    private final String cpf;
    private final LocalDateTime horaChegada;

    public Paciente(String nome, String cpf) {
        this.senha = proximaSenha++;
        this.nome = nome;
        this.cpf = cpf;
        this.horaChegada = LocalDateTime.now();
    }

    public int getSenha() {
        return senha;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDateTime getHoraChegada() {
        return horaChegada;
    }

    @Override
    public String toString() {
        DateTimeFormatter formato =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return "Senha: " + senha
            + " | Nome: " + nome
            + " | CPF: " + cpf
            + " | Chegada: " + horaChegada.format(formato);
    }
}
