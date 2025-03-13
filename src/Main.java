import Controller.ProfessorController;
import DAO.AlunoDao;
import DAO.MatriculaDao;
import DAO.UniversidadeDao;
import DAO.ProfessorDao;
import Enums.StatusMatricula;
import Models.Aluno;
import Models.Matricula;
import Models.Universidade;
import Models.Professor;
import static Controller.ProfessorController.criarProfessor;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static Controller.ProfessorController.criarProfessor;

public class Main {
    public static void main(String[] args) {
        UniversidadeDao universidadeDAO = new UniversidadeDao();
        Scanner scan = new Scanner(System.in);
        AlunoDao alunoDAO = new AlunoDao();
        Universidade universidade = new Universidade();
        MatriculaDao matriculaDAO = new MatriculaDao();
        ArrayList<Matricula> matriculas = new ArrayList<>();
        matriculas = matriculaDAO.listarMatriculas();
        for (Matricula matricula : matriculas) {
            System.out.println(matricula.getNumero());
        }
        menuLogin();

    }

    public static boolean verificarUniversidade() {
        UniversidadeDao universidadeDAO = new UniversidadeDao();
        return !universidadeDAO.carregarUniversidades().isEmpty();
    }

    public static boolean verificarSenha(Universidade universidade) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nInsira a senha da universidade ");
        int senha = scan.nextInt();
        return universidade.getSenha() == senha;
    }


    public static Universidade criarUniversidade() {
        UniversidadeDao universidadeDAO = new UniversidadeDao();
        System.out.println("\nDigite o nome da Universidade: ");
        Scanner scan = new Scanner(System.in);
        String nome = scan.nextLine();
        System.out.println("\nInsira a senha numerica do administrativo: ");
        int senha = scan.nextInt();
        Universidade universidade = new Universidade(nome);
        universidade.setSenha(senha);
        universidadeDAO.salvarDados(universidade);
        return universidade;
    }

    public static void menuAdmnistrativo(Universidade universidade) {
        Scanner scan = new Scanner(System.in);
        int opcao;
        do{
            System.out.println("Bem-vindo ao sistema administrativo da universidade " + universidade.getNome() + "!");
            System.out.println("1. Gerenciar alunos");
            System.out.println("2. Gerenciar cursos");
            System.out.println("3. Gerenciar professores");
            System.out.println("4. Gerenciar disciplinas");
            System.out.println("5. Sair");

            opcao = scan.nextInt();

            switch(opcao) {
                case 1:
                    gerenciarAlunos();
                    break;
                case 2:
                    System.out.println("Visualização de cursos ainda não implementada.");
                    esperar();
                    limparConsole();
                    break;
                case 3:
                    gerenciarProfessores();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }while(opcao != 5);

    }

    public static void menuAlunos() {
        Scanner scan = new Scanner(System.in);
        int opcao;

        System.out.println("chegou no menu de alunos");
    }

    public static void menuLogin(){
        Scanner scan = new Scanner(System.in);
        int opcao;

        do{
            System.out.println("\nEscolha o tipo de usuario: ");
            System.out.println("1. Aluno");
            System.out.println("2. Professor");
            System.out.println("3. Secretaria");
            opcao = scan.nextInt();

            switch(opcao) {
                case 1:
                    loginAlunos();
                    break;

                case 2:
                    break;

                case 3:
                    loginSecretaria();
                    break;

                default:
                    System.out.println("Opcao invalida. Tente novamente.");
                    break;
            }

        }while(opcao != 0);
    }

    public static void loginAlunos() {
        Scanner scan = new Scanner(System.in);
        MatriculaDao matriculaDAO = new MatriculaDao();
        Matricula matricula;
        boolean acesso = false;
        do{
            System.out.println("\nInsira o numero de matricula: ");
            int numMatricula = scan.nextInt();
            matricula = matriculaDAO.buscarMatriculaPorNumero(numMatricula);

            if(matricula == null){
                System.out.println("Deseja voltar ao menu principal? (S/N)");
                String opcao = scan.next();
                if(opcao.equals("S")){
                    break;
                }
            }else{
                System.out.println("\n Insira a senha: ");
                int senha = scan.nextInt();
                if (matricula.getAluno().getSenha() == senha) {
                    acesso = true;
                    menuAlunos();
                }
            }


        }while(!acesso);


    }

    public static void loginSecretaria() {
        Scanner scan = new Scanner(System.in);
        UniversidadeDao universidadeDAO = new UniversidadeDao();
        Universidade universidade = new Universidade();

        if (verificarUniversidade()) {
            boolean acesso = false;
            while (!acesso) {
                System.out.println("\nInsira o nome da universidade ");
                String nome = scan.nextLine();
                universidade = universidadeDAO.buscarUniversidade(nome);
                if (universidade != null) {
                    if (verificarSenha(universidade)) {
                        acesso = true;
                    }else{
                        System.out.println("Senha incorreta!");
                    }

                }else{
                    System.out.println("\nUniversidade nao encontrada \nDeseja criar uma? (SIM/NAO)  ");
                    String opcao = scan.nextLine();
                    if (opcao.equalsIgnoreCase("Sim")) {
                        criarUniversidade();
                    }
                }
            }


            menuAdmnistrativo(universidade);
        } else {
            System.out.println("\nNão há universidades cadastradas, prosseguindo para criar uma");
            universidade = criarUniversidade();
            menuAdmnistrativo(universidade);
        }
    }

    public static void gerenciarAlunos() {
        Scanner scan = new Scanner(System.in);

        AlunoDao alunoDAO = new AlunoDao();
        MatriculaDao matriculaDAO = new MatriculaDao();
        Aluno aluno;
        int opcao;

        do{
            System.out.println("Gerencia de alunos: ");
            System.out.println("1- Criar aluno: ");
            System.out.println("2- Cancelar matricula de aluno: ");
            System.out.println("3- Ativar matricula de aluno: ");
            System.out.println("4- Voltar: ");

            opcao = scan.nextInt();

            switch (opcao) {
                case 1:
                    aluno = criarAluno();
                    Matricula matricula = criarMatricula();
                    aluno.setMatricula(matricula);
                    matricula.setAluno(aluno);
                    alunoDAO.adicionarAluno(aluno);
                    matriculaDAO.adicionarMatricula(matricula);
                    esperar();
                    limparConsole();
                    break;
                case 2:
                    aluno = buscarAluno();
                    if (aluno == null) {
                        break;
                    }
                    cancelarMatricula(aluno);
                    esperar();
                    limparConsole();
                    break;

                case 3:
                    aluno = buscarAluno();
                    if (aluno == null) {
                        break;
                    }
                    ativarMatricula(aluno);
                    esperar();
                    limparConsole();
                    break;

                case 4:
                    System.out.println("Voltando...");
                    esperar();
                    limparConsole();
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }while (opcao != 4);
    }

    public static Matricula criarMatricula() {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        MatriculaDao matriculaDAO = new MatriculaDao();
        ArrayList<Matricula> matriculas = matriculaDAO.listarMatriculas();
        int numMatricula;
        boolean isUnique;

        do {
            numMatricula = 100000 + rand.nextInt(900000);
            isUnique = true;
            for (Matricula matricula : matriculas) {
                if (matricula.getNumero() == numMatricula) {
                    isUnique = false;
                    break;
                }
            }
        } while (!isUnique);
        Matricula matricula = new Matricula(numMatricula);
        System.out.println("Número de matrícula único gerado: " + numMatricula);
        return matricula;
    }

    public static Aluno criarAluno() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira o nome do Aluno: ");
        String nome = scan.nextLine();
        System.out.println("Insira a senha numerica do Aluno: ");
        int senha = scan.nextInt();
        Aluno aluno = new Aluno(nome);
        aluno.setSenha(senha);

        return aluno;
    }

    public static Aluno buscarAluno(){
        AlunoDao alunoDAO = new AlunoDao();
        Scanner scan = new Scanner(System.in);
        Aluno aluno = null;

        while (aluno == null) {
            System.out.println("Insira o nome do aluno: ");
            String nomeAluno = scan.next();
            aluno = alunoDAO.buscarAlunoPorNome(nomeAluno);
            if(aluno == null){
                System.out.println("Deseja voltar ao menuAdmnistrativo? (S/N)");
                String opcao = scan.next();
                if (opcao.equalsIgnoreCase("S")) {
                    break;
                }
            }


        }
        return aluno;
    }

    public static void esperar(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void limparConsole() {
        for (int i = 0; i < 50; ++i) {
            System.out.println();
        }
    }

    public static void cancelarMatricula(Aluno aluno) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Deseja cancelar a matricula do aluno " + aluno.getNome() + "?  (S/N)");
        String opcao = scan.next();
        if (opcao.equalsIgnoreCase("S")) {
            aluno.getMatricula().setStatusMatricula(StatusMatricula.CANCELADA);
            System.out.println("Matricula " + aluno.getMatricula().getNumero() + " cancelada");
        }
    }

    public static void ativarMatricula(Aluno aluno) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Deseja ativar a matricula do aluno " + aluno.getNome() + "?  (S/N)");
        String opcao = scan.next();
        if (opcao.equalsIgnoreCase("S")) {
            aluno.getMatricula().setStatusMatricula(StatusMatricula.ATIVA);
            System.out.println("Matricula " + aluno.getMatricula().getNumero() + " ativada");
        }
    }

    // Menu Professor

    public static void gerenciarProfessores() {
        Scanner scan = new Scanner(System.in);

        AlunoDao alunoDAO = new AlunoDao();
        MatriculaDao matriculaDAO = new MatriculaDao();
        Aluno aluno;
        int opcao;

        do{
            System.out.println("Gerencia de professores: ");
            System.out.println("1- Adiciona professor: ");
            System.out.println("2- Listar professor: ");
            System.out.println("2- Remover professor: ");
            System.out.println("4- Voltar: ");

            opcao = scan.nextInt();

            switch (opcao) {
                case 1:
                    Professor professor = criarProfessor();
                    esperar();
                    limparConsole();
                    break;
                case 2:
                    ProfessorController professorController = new ProfessorController();
                    professorController.listarProfessores();
                    break;

                case 3:
                    System.out.println("Voltando...");
                    esperar();
                    limparConsole();
                    break;

                case 4:
                    System.out.println("Voltando...");
                    esperar();
                    limparConsole();
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }while (opcao != 4);
    }

}