import DAO.AlunoDAO;
import DAO.UniversidadeDAO;
import Models.Aluno;
import Models.Universidade;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UniversidadeDAO universidadeDAO = new UniversidadeDAO();
        Scanner scan = new Scanner(System.in);
        AlunoDAO alunoDAO = new AlunoDAO();

        if (verificarUniversidade()) {
            System.out.println("\nInsira o nome da universidade ");
            String nome = scan.nextLine();
            Universidade universidade = universidadeDAO.buscarUniversidade(nome);
            if (universidade != null) {
                System.out.println("\nInsira a senha da universidade ");
                int senha = scan.nextInt();
                if (universidade.getSenha() == senha) {
                    menu(universidade);
                }
            }else{
                System.out.println("\nUniversidade nao encontrada ");
            }
        } else {
            System.out.println("\nNão há universidades cadastradas, prosseguindo para criar uma");
            Universidade universidade = criarUniversidade();
            menu(universidade);
        }
    }

    public static boolean verificarUniversidade() {
        UniversidadeDAO universidadeDAO = new UniversidadeDAO();
        return !universidadeDAO.carregarUniversidades().isEmpty();
    }

    public static Universidade criarUniversidade() {
        UniversidadeDAO universidadeDAO = new UniversidadeDAO();
        System.out.println("\nDigite o nome da Universidade: ");
        Scanner scan = new Scanner(System.in);
        String nome = scan.nextLine();
        System.out.println("\nInsira a senha numerica do administrativo: ");
        int senha = scan.nextInt();
        Universidade universidade = new Universidade(nome);
        universidade.setSenha(senha);
        universidadeDAO.salvarUniversidade(universidade);
        return universidade;
    }

    public static void menu(Universidade universidade) {
        System.out.println("Bem-vindo ao sistema de matrículas!");
        System.out.println("1. Cadastrar Aluno");
        System.out.println("2. Visualizar Alunos");
        System.out.println("3. Sair");

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
}
