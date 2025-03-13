package DAO;

import Models.Aluno;

import java.io.*;
import java.util.ArrayList;




public class AlunoDao extends AbstractDao<Aluno> {
    private ArrayList<Aluno> alunos = new ArrayList<>();
    private static final String FILE_PATH = "alunos.dat";
    private static AlunoDao instancia;

    public AlunoDao() {
        super(FILE_PATH);
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
        salvarDados();
    }

    public static AlunoDao getInstance() {
        if (instancia == null) {
            synchronized (AlunoDao.class) {
                if (instancia == null) {
                    instancia = new AlunoDao();
                }
            }
        }
        return instancia;
    }

    public boolean verificarLogin(String nome, int senha) {
        carregarDados();
        for (Aluno aluno : alunos) {
            if (aluno.getNome().equals(nome) && aluno.getSenha() == senha) {
                return true;
            }
        }
        return false;
    }

    public void salvarDados() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(alunos);
        } catch (IOException e) {
            System.out.println("❌ Erro ao salvar os dados: " + e.getMessage());
        }
    }

    public void carregarDados() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            alunos = (ArrayList<Aluno>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠️ Nenhum dado encontrado ou erro ao carregar os dados.");
        }
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
        carregarDados();
        return alunos;
    }
}
