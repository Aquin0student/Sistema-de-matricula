package DAO;

import Models.Aluno;

import java.util.ArrayList;

public class AlunoDAO {
    private ArrayList<Aluno> alunos = new ArrayList<>();

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public boolean verificarLogin(String nome, int senha) {
        for (Aluno aluno : alunos) {
            if (aluno.getNome().equals(nome) && aluno.getSenha() == senha) {
                return true;
            }
        }
        return false;
    }

    public Aluno buscarAlunoPorNome(String nome) {
        for (Aluno aluno : alunos) {
            if (aluno.getNome().equalsIgnoreCase(nome)) {
                return aluno;
            }
        }
        return null;
    }


    public ArrayList<Aluno> listarAlunos() {
        return alunos;
    }
}
