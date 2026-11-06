# Sistema de Filas - Clínica

Projeto desenvolvido em Java para a disciplina de Estruturas de Dados do curso de Ciência da Computação.

O sistema simula o atendimento de uma clínica utilizando duas filas: uma para a recepção e outra para as consultas médicas. As filas seguem a ordem de chegada dos pacientes (FIFO).

## Funcionalidades

- Cadastro de pacientes com geração automática de senha
- Atendimento na recepção e encaminhamento para consultas
- Limite de 5 pacientes na fila de consultas
- Listagem dos pacientes em cada fila
- Consulta do próximo paciente de cada fila
- Relatório com quantidade de atendidos e tempo médio de espera
- Tratamento de filas vazias
- Menu com resumo das filas atualizado em tempo real
- Interface colorida com suporte a cores ANSI 256 e negrito

## Como executar

É necessário ter o Java JDK 21 instalado.

### Clone o repositório e acesse a pasta do projeto:

```bash
git clone https://github.com/Burrg-cod/sistema-filas-clinica.git
cd sistema-filas-clinica
```

### Compile os arquivos Java:

```bash
mkdir out
javac -cp src src/*.java src/ui/*.java -d out
```

### Execute o programa:

```bash
java -cp out Main
```

### Execute os testes:

```bash
javac -cp src src/*.java src/ui/*.java tests/*.java -d out
java -cp out TestesClinica
```

## Estrutura do projeto

```text
src/
├── ui/
│   └── InterfaceConsole.java
├── Main.java
├── Paciente.java
└── ServicoClinica.java
tests/
└── TestesClinica.java
```

## Tecnologias

Java 21, utilizando `Queue`, `LinkedList`, `ArrayDeque` e `ArrayList`.