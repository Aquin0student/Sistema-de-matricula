package DAO;

import Models.Professor;

import java.io.*;
import java.util.ArrayList;

public class ProfessorDao extends AbstractDao {
    private ArrayList<Professor> professores = new ArrayList<>();
    public static final String FILE_PATH = "professores.dat";

    public ProfessorDao(){
        super(FILE_PATH);
    }

    public void adicionarProfessor(Professor professor){
        professores.add(professor);
        salvarDados();
    }

    public void removerProfessor(Professor professor){
        professores.remove(professor);
        salvarDados();
    }

    public void salvarDados() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(professores);
        } catch (IOException e) {
            System.out.println("❌ Erro ao salvar os dados: " + e.getMessage());
        }
    }

    public void carregarDados() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            professores = (ArrayList<Professor>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠️ Nenhum dado encontrado ou erro ao carregar os dados.");
        }
    }

    public Professor buscarProfessorPorNome(String Professor) {
        for (Professor professor : professores) {
            if (professor.getNome().equalsIgnoreCase(Professor)) {
                return professor;
            }
        }
        return null;
    }

}
