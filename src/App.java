import java.util.ArrayList;
import java.util.Scanner;

import Alunos.Aluno;

public class App {
    static ArrayList<Aluno> alunos = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Cadastrar aluno");
            System.out.println("2. Lançar notas");
            System.out.println("3. Lançar N3");
            System.out.println("4. Mostrar todos os alunos");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1 -> cadastrarAluno();
                case 2 -> lancarNotas();
                case 3 -> lancarN3();
                case 4 -> mostrarAlunos();
                case 5 -> {
                    System.out.println("Saindo do programa...");
                    scanner.close(); // Fecha o Scanner
                }
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }

    static void cadastrarAluno() {
        System.out.print("Nome do aluno: ");
        String nome = scanner.nextLine();
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();
        alunos.add(new Aluno(nome, matricula));
        System.out.println("Aluno cadastrado com sucesso!");
    }

    static void lancarNotas() {
        System.out.print("Informe a matrícula do aluno: ");
        String matricula = scanner.nextLine();
        Aluno aluno = buscarAluno(matricula);
        if (aluno != null) {
            System.out.print("Nota N1: ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Entrada inválida. Digite um número.");
                scanner.next(); // Descarta a entrada inválida
            }
            aluno.setN1(scanner.nextDouble());
            scanner.nextLine(); // Limpa o buffer

            System.out.print("Nota da prova N2: ");
            aluno.setN2Prova(scanner.nextDouble());
            System.out.print("Nota do trabalho N2: ");
            aluno.setN2Trabalho(scanner.nextDouble());
            aluno.calcularMedia();
            System.out.println("Notas lançadas e média calculada!");
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    static void lancarN3() {
        System.out.print("Informe a matrícula do aluno para N3: ");
        String matricula = scanner.nextLine();
        Aluno aluno = buscarAluno(matricula);
        if (aluno != null && aluno.getStatus().equals("Prova Final")) {
            System.out.print("Nota da N3: ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Entrada inválida. Digite um número.");
                scanner.next(); // Descarta a entrada inválida
            }
            aluno.setN3(scanner.nextDouble());
            scanner.nextLine(); // Limpa o buffer após capturar o número
            aluno.calcularNotaFinalComN3();
            System.out.println("Nota N3 lançada e status atualizado!");
        } else {
            System.out.println("Aluno não encontrado ou não precisa de N3.");
        }
    }

    static void mostrarAlunos() {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }
        for (Aluno aluno : alunos) {
            System.out.println("-------------------------");
            System.out.println(aluno);
        }
    }

    static Aluno buscarAluno(String matricula) {
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equalsIgnoreCase(matricula)) {
                return aluno;
            }
        }
        return null;
    }
}
