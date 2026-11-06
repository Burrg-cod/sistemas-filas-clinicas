# Sistema de Filas - Clínica

projeto desenvolvido em Java para a disciplina de Estruturas de Dados do curso de Ciência da Computação.

o sistema simula o atendimento de uma clínica utilizando duas filas: uma para a recepção e outra para as consultas médicas. As filas seguem a ordem de chegada dos pacientes (FIFO).

## Funcionalidades

Cadastro de pacientes com geração automática de senha.
Atendimento na recepção e encaminhamento para consultas.
Limite de 5 pacientes na fila de consultas.
Listagem dos pacientes em cada fila.
Consulta do próximo paciente de cada fila.
Relatório com quantidade de atendidos e tempo médio de espera.
Tratamento de filas vazias.

## Como executar

É necessário ter o Java JDK 21 instalado.

## Clone o repositório e acesse a pasta do projeto:


git clone https://github.com/Burrg-cod/sistema-filas-clinica.git
cd sistema-filas-clinica


## Compile os arquivos Java:

javac src/*.java


## Execute o programa:

java -cp src Main


## O sistema será iniciado no terminal, onde é possível escolher as operações pelo menu numérico.

## Estrutura do projeto

```text
src/
├── Paciente.java
├── ServicoClinica.java
└── Main.java
```

## Tecnologias

Java 21, utilizando `Queue`, `LinkedList`, `ArrayDeque` e `ArrayList`.