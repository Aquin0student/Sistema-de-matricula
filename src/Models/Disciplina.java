package Models;

import Enums.StatusDisciplina;
import Enums.TipoDisciplina;

import java.io.Serializable;
import java.util.ArrayList;

public class Disciplina implements Serializable {
    private String nome;
    private ArrayList<Professor> professores= new ArrayList<Professor>();
    private ArrayList<Matricula> matriculas = new ArrayList<Matricula>();
    private StatusDisciplina statusDisciplina;
    private TipoDisciplina tipoDisciplina;
    private int numCreditos;

    public Disciplina(String nome) {
        this.nome = nome;
        this.statusDisciplina = StatusDisciplina.ATIVA;

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

    public void setProfessores(ArrayList<Professor> professores) {
        this.professores = professores;
    }

    public StatusDisciplina getStatusDisciplina() {
        return statusDisciplina;
    }

    public void setStatusDisciplina(StatusDisciplina statusDisciplina) {
        this.statusDisciplina = statusDisciplina;
    }

    public void adicionarMatricula(Matricula matricula) {
        matriculas.add(matricula);
    }

    public void removerMatricula(Matricula matricula) {
        matriculas.remove(matricula);
    }

    public boolean estaAtiva(){
        return statusDisciplina == StatusDisciplina.ATIVA;
    }

    public boolean estaCheia(){
        return matriculas.size() == 60;
    }

    public int getNumCreditos() {
        return numCreditos;
    }

    public void setNumCreditos(int numCreditos) {
        this.numCreditos = numCreditos;
    }

    public TipoDisciplina getTipoDisciplina() {
        return tipoDisciplina;
    }

    public void setTipoDisciplina(TipoDisciplina tipoDisciplina) {
        this.tipoDisciplina = tipoDisciplina;
    }

    public ArrayList<Matricula> getMatriculas() {
        return matriculas;
    }
}
