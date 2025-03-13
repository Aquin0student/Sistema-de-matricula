package DAO;

import Models.Matricula;
import java.io.*;
import java.util.ArrayList;

public class MatriculaDao extends AbstractDao {
    private ArrayList<Matricula> matriculas = new ArrayList<>();
    private static final String FILE_PATH = "matriculas.dat";
    private static MatriculaDao instancia;

    public MatriculaDao() {
        super(FILE_PATH);
    }

    public void adicionarMatricula(Matricula matricula) {
        matriculas.add(matricula);
        salvarDados();
    }


    public static MatriculaDao getInstance() {
        if (instancia == null) {
            synchronized (MatriculaDao.class) {
                if (instancia == null) {
                    instancia = new MatriculaDao();
                }
            }
        }
        return instancia;
    }


    public void salvarDados() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(matriculas);
        } catch (IOException e) {
            System.out.println("❌ Erro ao salvar os dados: " + e.getMessage());
        }
    }

    public void carregarDados() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            matriculas = (ArrayList<Matricula>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠️ Nenhum dado encontrado ou erro ao carregar os dados.");
        }
    }

    // Atenção com esse metodo //
    public Matricula buscarMatriculaPorAluno(String aluno) {
        for (Matricula matricula : matriculas) {
            // Supondo que a classe Matricula possua um método getAluno() que retorna o nome do aluno
            if (matricula.getAluno().getNome().equalsIgnoreCase(aluno)) {
                return matricula;
            }
        }
        return null;
    }

    public ArrayList<Matricula> listarMatriculas() {
        carregarDados();
        return matriculas;
    }

    public Matricula buscarMatriculaPorNumero(int numero) {
        for (Matricula matricula : matriculas) {
            if (matricula.getNumero() == numero) {
                return matricula;
            }
        }
        System.out.println("Matricula inexistente");
        return null;
    }
}
