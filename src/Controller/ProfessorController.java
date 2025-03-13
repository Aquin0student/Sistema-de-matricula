package Controller;

import Models.Professor;
import DAO.ProfessorDao;
import java.util.ArrayList;
import java.util.Scanner;

public class ProfessorController {
    public static Professor criarProfessor() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira o nome do professor: ");
        String nome = scan.nextLine();
        System.out.println("Insira a matrícula do professor: ");
        int matricula = scan.nextInt();
        System.out.println("Insira a senha numerica do professor: ");
        int senha = scan.nextInt();
        Professor professor = new Professor(nome, matricula, senha);
        ProfessorDao.getInstance().adicionarProfessor(professor);
        return professor;
    }

    public ArrayList<Professor> listarTodosProfessores() {
        ArrayList<Professor> professores = ProfessorDao.getInstance().listarProfessores();
        return new ArrayList<>(professores);
    }

    public void listarProfessores() {
        ArrayList<Professor> professores = new ArrayList<>(ProfessorDao.getInstance().listarProfessores());
        if (professores.isEmpty()) {
            System.out.println("Nenhum professor cadastrado.");
        } else {
            System.out.println("\n=== Lista de Professores (Nome e Matrícula) ===");
            System.out.println(" Nº | Nome                | Matrícula ");
            System.out.println("---------------------------------------");
            int i = 1;
            for (Professor professor : professores) {
                System.out.printf(" %2d | %-20s | %-10d\n", i, professor.getNome(), professor.getMatricula());
                i++;
            }
        }
    }
}
