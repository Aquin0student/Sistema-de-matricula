package Models;

import Enums.StatusMatricula;
import Enums.TipoDisciplina;

import java.util.ArrayList;
import java.util.Date;

public class Matricula {
    private Aluno aluno;
    private ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
    private Date dataMatricula;
    private StatusMatricula statusMatricula;
    private int obrigatoria=0;
    private int optativa=0;


    public StatusMatricula getStatusMatricula() {
        return statusMatricula;
    }

    public void setStatusMatricula(StatusMatricula statusMatricula) {
        this.statusMatricula = statusMatricula;
    }

    public Date getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(Date dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public ArrayList<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        if (disciplina.getTipoDisciplina() == TipoDisciplina.OBRIGATORIA){
            if(obrigatoria<=4){
                obrigatoria++;
                disciplinas.add(disciplina);
            }
        }else{
            if(optativa<=2){
                optativa++;
                disciplinas.add(disciplina);
            }
        }
    }

    public void removerDisciplina(Disciplina disciplina) {
        if (disciplina.getTipoDisciplina() == TipoDisciplina.OBRIGATORIA){
            obrigatoria--;
            disciplinas.remove(disciplina);
        }else{
            optativa--;
            disciplinas.remove(disciplina);
        }
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }




}
