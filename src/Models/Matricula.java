package Models;

import Enums.StatusMatricula;
import Enums.TipoDisciplina;
import java.util.ArrayList;
import java.util.Date;

public class Matricula {
    private String numero;  // novo atributo para armazenar o número da matrícula
    private Aluno aluno;
    private ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
    private Date dataMatricula;
    private StatusMatricula statusMatricula;
    private int obrigatoria = 0;
    private int optativa = 0;

    // Construtor que recebe o número da matrícula
    public Matricula(String numero) {
        this.numero = numero;
        this.dataMatricula = new Date();
        // Opcional: defina um status padrão, por exemplo:
        // this.statusMatricula = StatusMatricula.ATIVA;
    }

    // Construtor padrão (caso seja necessário)
    public Matricula() {
        this.dataMatricula = new Date();
    }

    // Getter e setter para o atributo numero
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

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
        if (disciplina.getTipoDisciplina() == TipoDisciplina.OBRIGATORIA) {
            if (obrigatoria <= 4) {
                obrigatoria++;
                disciplinas.add(disciplina);
            }
        } else {
            if (optativa <= 2) {
                optativa++;
                disciplinas.add(disciplina);
            }
        }
    }

    public void removerDisciplina(Disciplina disciplina) {
        if (disciplina.getTipoDisciplina() == TipoDisciplina.OBRIGATORIA) {
            obrigatoria--;
            disciplinas.remove(disciplina);
        } else {
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
