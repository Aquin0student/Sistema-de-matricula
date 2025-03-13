package Models;

import Enums.TipoEntidade;

import java.io.Serializable;
import java.util.ArrayList;

public class Professor implements Serializable {
    private String nome;
    private ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
    private int matricula;
    private int senha;
    private TipoEntidade tipo = TipoEntidade.PROFESSOR;

    public Professor(String nome, int matricula, int senha) {
        this.nome = nome;
        this.matricula = matricula;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int setSenha(int senha) {
        return this.senha = senha;
    }

    public ArrayList<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public int getMatricula() {
        return matricula;
    }

    public int setMatricula(int matricula) {
        this.matricula = matricula;
        return matricula;
    }
}
