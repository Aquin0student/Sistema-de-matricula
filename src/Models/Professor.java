package Models;

import Enums.TipoEntidade;

import java.util.ArrayList;

public class Professor {
    private String nome;
    private ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
    private TipoEntidade tipo = TipoEntidade.PROFESSOR;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }



}
