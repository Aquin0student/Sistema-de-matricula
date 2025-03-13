import DAO.AlunoDao;
import DAO.UniversidadeDao;
import Models.Universidade;

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
        System.out.println("Bem-vindo ao sistema da universidade " + universidade.getNome() + "!");
        System.out.println("1. Gerenciar alunos");
        System.out.println("2. Gerenciar cursos");
        System.out.println("3. Gerenciar professores");
        System.out.println("4. Gerenciar disciplinas");
        System.out.println("5. Sair");

        Scanner scan = new Scanner(System.in);
        int opcao = scan.nextInt();

        switch(opcao) {
            case 1:
                System.out.println("Cadastro de Aluno ainda não implementado.");
                break;
            case 2:
                System.out.println("Visualização de Alunos ainda não implementada.");
                break;
            case 3:
                System.out.println("Saindo...");
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                menu(universidade);
        }
    }

    public static void gerenciarAlunos() {
        Scanner scan = new Scanner(System.in);
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("Gerencia de alunos: ");
            System.out.println("Criar aluno: ");
            System.out.println("Cancelar matricula de aluno: ");
        }
    }
}
