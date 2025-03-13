import DAO.AlunoDao;
import DAO.UniversidadeDao;
import Models.Universidade;
import Controllers.AlunoController;
import Models.Matricula;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UniversidadeDao universidadeDAO = new UniversidadeDao();
        AlunoController alunoController = new AlunoController();
        Scanner scan = new Scanner(System.in);
        Universidade universidade = new Universidade();

        if (verificarUniversidade()) {
            boolean acesso = false;
            while (!acesso) {
                System.out.println("\nInsira o nome da universidade: ");
                String nome = scan.nextLine();
                universidade = universidadeDAO.buscarUniversidade(nome);
                if (universidade != null) {
                    if (verificarSenha(universidade)) {
                        acesso = true;
                    } else {
                        System.out.println("Senha incorreta!");
                    }
                } else {
                    System.out.println("\nUniversidade não encontrada. Deseja criar uma? (SIM/NAO): ");
                    String opcao = scan.nextLine();
                    if (opcao.equalsIgnoreCase("Sim")) {
                        universidade = criarUniversidade();
                    }
                }
            }
            menu(universidade, alunoController, scan);
        } else {
            System.out.println("\nNão há universidades cadastradas, prosseguindo para criar uma.");
            universidade = criarUniversidade();
            menu(universidade, alunoController, scan);
        }
    }

    public static boolean verificarUniversidade() {
        UniversidadeDao universidadeDAO = new UniversidadeDao();
        return !universidadeDAO.carregarUniversidades().isEmpty();
    }

    public static boolean verificarSenha(Universidade universidade) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nInsira a senha da universidade: ");
        int senha = scan.nextInt();
        return universidade.getSenha() == senha;
    }

    public static Universidade criarUniversidade() {
        UniversidadeDao universidadeDAO = new UniversidadeDao();
        Scanner scan = new Scanner(System.in);
        System.out.println("\nDigite o nome da Universidade: ");
        String nome = scan.nextLine();
        System.out.println("\nInsira a senha numérica do administrativo: ");
        int senha = scan.nextInt();
        Universidade universidade = new Universidade(nome);
        universidade.setSenha(senha);
        universidadeDAO.salvarDados(universidade);
        return universidade;
    }

    public static void menu(Universidade universidade, AlunoController alunoController, Scanner scan) {
        System.out.println("Bem-vindo ao sistema da universidade " + universidade.getNome() + "!");
        System.out.println("1. Gerenciar alunos");
        System.out.println("2. Gerenciar cursos");
        System.out.println("3. Gerenciar professores");
        System.out.println("4. Gerenciar disciplinas");
        System.out.println("5. Sair");

        int opcao = scan.nextInt();
        scan.nextLine();
        switch (opcao) {
            case 1:
                System.out.print("Digite o número da matrícula: ");
                String numMatricula = scan.nextLine();
                Matricula matricula = new Matricula(numMatricula);
                alunoController.criarAluno(matricula);
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
                menu(universidade, alunoController, scan);
        }
    }
}
