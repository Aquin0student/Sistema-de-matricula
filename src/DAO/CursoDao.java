package DAO;


import Models.Aluno;
import Models.Curso;

import java.io.*;
import java.util.ArrayList;

public class CursoDao extends AbstractDao{
    private ArrayList<Curso> cursos = new ArrayList<>();
    private static final String FILE_PATH = "cursos.dat";

    public CursoDao(){
        super(FILE_PATH);
    }

    public void adicionarCurso(Curso curso){
        cursos.add(curso);

    }

    public void salvarDados() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(cursos);
        } catch (IOException e) {
            System.out.println("❌ Erro ao salvar os dados: " + e.getMessage());
        }
    }

    public void carregarDados() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            cursos = (ArrayList<Aluno>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠️ Nenhum dado encontrado ou erro ao carregar os dados.");
        }
    }

    public Curso buscarCursoPorNome(String Curso) {
        for (Curso curso : cursos) {
            if (curso.getNome().equalsIgnoreCase(Curso)) {
                return curso;
            }
        }
        return null;
    }

    public ArrayList<Curso> listarCursos(){
        carregarDados();
        return cursos;
    }
}
