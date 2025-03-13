package Models;

import DAO.AlunoDao;
import DAO.DisciplinaDao;

import java.util.ArrayList;

public class SistemaDeCobrancas {
    AlunoDao alunoDao = new AlunoDao();
    DisciplinaDao disciplinaDao = new DisciplinaDao();

    public SistemaDeCobrancas() {}

    public void cobrarAluno(Aluno aluno) {
        ArrayList<Disciplina> disciplinas;
        Matricula matricula = aluno.getMatricula();
        disciplinas = matricula.getDisciplinas();

        if(!disciplinas.isEmpty()){
            System.out.println("Aluno " + aluno.getNome() + "esta sendo cobrado pelas disciplinas:");
        }
        for(Disciplina disciplina : disciplinas){

            System.out.println(disciplina.getNome());
        }
    }
}
