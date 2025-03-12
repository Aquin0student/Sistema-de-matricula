package Models;

import java.util.ArrayList;

public class Universidade {
    private String nome;
    private ArrayList<Aluno> alunos = new ArrayList<Aluno>();
    private ArrayList<Curso> cursos = new ArrayList<Curso>();
    private ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
    private ArrayList<Professor> professores = new ArrayList<Professor>();

    public Universidade() {}

    public Universidade(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Professor> getProfessores() {
        return professores;
    }

    public void adicionarProfessor(Professor professor) {
        professores.add(professor);
    }

    public ArrayList<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void adicionarCursos(Curso curso) {
        cursos.add(curso);
    }


}
