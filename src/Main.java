import DAO.AlunoDAO;
import Models.Aluno;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AlunoDAO alunoDAO = new AlunoDAO();

        alunoDAO.carregarDados(); // Carrega os dados ao iniciar

        System.out.print("Deseja cadastrar um novo aluno? (s/n): ");
        if (sc.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Digite o nome do aluno: ");
            String nome = sc.nextLine();

            System.out.print("Digite a senha: ");
            int senha = sc.nextInt();

            Aluno novoAluno = new Aluno(nome);
            novoAluno.setSenha(senha);
            alunoDAO.adicionarAluno(novoAluno);
            System.out.println("✅ Aluno cadastrado com sucesso!");
        }

        System.out.print("Digite seu nome para login: ");
        String nomeLogin = sc.next();

        System.out.print("Digite sua senha: ");
        int senhaLogin = sc.nextInt();

        if (alunoDAO.verificarLogin(nomeLogin, senhaLogin)) {
            System.out.println("✅ Login bem-sucedido! Bem-vindo, " + nomeLogin);
        } else {
            System.out.println("❌ Falha no login! Verifique seu nome ou senha.");
        }
    }
}
