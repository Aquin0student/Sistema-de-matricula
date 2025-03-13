package Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Curso implements Serializable {
    String nome;
    int numCreditos;
    ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumCreditos() {
        return numCreditos;
    }
    public void setNumCreditos(int numCreditos) {
        this.numCreditos = numCreditos;
    }

    public ArrayList<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void adicionarDisciplinas(ArrayList<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public void removerDisciplinas(Disciplina disciplina) {
        this.disciplinas.remove(disciplina);
    }
}
