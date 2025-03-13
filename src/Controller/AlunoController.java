package Controller;

import DAO.AlunoDao;
import Models.Aluno;
import Models.Matricula;
import Models.Disciplina;
import Enums.TipoDisciplina;
import java.util.Scanner;

public class AlunoController {
    private AlunoDao alunoDao;
    private Scanner scan;

    public AlunoController() {
        this.alunoDao = AlunoDao.getInstance();
        this.scan = new Scanner(System.in);
    }

    public Aluno criarAluno(Matricula matricula) {
        System.out.print("Digite o nome do aluno: ");
        String nome = scan.nextLine();
        System.out.print("Deseja adicionar disciplinas ao currículo? (SIM/NAO): ");
        String opcao = scan.nextLine();
        while (opcao.equalsIgnoreCase("SIM")) {
            System.out.print("Digite o nome da disciplina: ");
            String nomeDisciplina = scan.nextLine();
            System.out.print("Digite o tipo da disciplina (1 - Obrigatória, 2 - Optativa): ");
            int tipo = scan.nextInt();
            scan.nextLine();
            Disciplina disciplina = new Disciplina(nomeDisciplina);

            disciplina.setTipoDisciplina(tipo == 1 ? TipoDisciplina.OBRIGATORIA : TipoDisciplina.OPTATIVA);
            matricula.adicionarDisciplina(disciplina);
            System.out.print("Deseja adicionar outra disciplina? (SIM/NAO): ");
            opcao = scan.nextLine();
        }
        Aluno aluno = new Aluno(nome);
        aluno.setMatricula(matricula);
        System.out.print("Digite uma senha numérica para o aluno: ");
        int senha = scan.nextInt();
        scan.nextLine();
        aluno.setSenha(senha);
        alunoDao.adicionarAluno(aluno);
        System.out.println("Aluno criado com sucesso!");
        return aluno;
    }
}
