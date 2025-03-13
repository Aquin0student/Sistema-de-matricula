import DAO.AlunoDao;
import DAO.MatriculaDao;
import DAO.UniversidadeDao;
import Enums.StatusMatricula;
import Models.Aluno;
import Models.Matricula;
import Models.Universidade;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UniversidadeDao universidadeDAO = new UniversidadeDao();
        Scanner scan = new Scanner(System.in);
        AlunoDao alunoDAO = new AlunoDao();
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
            menu(universidade);
        } else {
            System.out.println("\nNão há universidades cadastradas, prosseguindo para criar uma");
            universidade = criarUniversidade();
            menu(universidade);
        }
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

    public static void menu(Universidade universidade) {
        Scanner scan = new Scanner(System.in);
        int opcao;
        do{
            System.out.println("Bem-vindo ao sistema da universidade " + universidade.getNome() + "!");
            System.out.println("1. Gerenciar alunos");
            System.out.println("2. Gerenciar cursos");
            System.out.println("3. Gerenciar professores");
            System.out.println("4. Gerenciar disciplinas");
            System.out.println("5. Sair");

            opcao = scan.nextInt();

            switch(opcao) {
                case 1:
                    gerenciarAlunos();
                case 2:
                    System.out.println("Visualização de cursos ainda não implementada.");
                    esperar();
                    limparConsole();
                    break;
                case 3:
                    System.out.println("Saindo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    menu(universidade);
            }
        }while(opcao != 5);

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

            opcao = scan.nextInt();

            switch (opcao) {
                case 1:
                    aluno = criarAluno();
                    Matricula matricula = criarMatricula();
                    aluno.setMatricula(matricula);
                    matricula.setAluno(aluno);
                    alunoDAO.adicionarAluno(aluno);
                    matriculaDAO.adicionarMatricula(matricula);
                    break;
                case 2:
                    aluno = buscarAluno();
                    if (aluno == null) {
                        break;
                    }
                    cancelarMatricula(aluno);
                    break;

                case 3:
                    aluno = buscarAluno();
                    if (aluno == null) {
                        break;
                    }


            }
        }while (opcao != 0);
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
                System.out.println("Deseja voltar ao menu? (S/N)");
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



}