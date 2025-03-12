package DAO;

import Models.Disciplina;
import java.io.*;
import java.util.ArrayList;

public class DisciplinaDao extends AbstractDao {
    private ArrayList<Disciplina> disciplinas = new ArrayList<>();
    private static final String FILE_PATH = "disciplinas.dat";

    public DisciplinaDao() {
        super(FILE_PATH);
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
        salvarDados();
    }

    public void salvarDados() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(disciplinas);
        } catch (IOException e) {
            System.out.println("❌ Erro ao salvar os dados: " + e.getMessage());
        }
    }

    public void carregarDados() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            disciplinas = (ArrayList<Disciplina>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠️ Nenhum dado encontrado ou erro ao carregar os dados.");
        }
    }

    public Disciplina buscarDisciplinaPorNome(String nome) {
        for (Disciplina disciplina : disciplinas) {
            if (disciplina.getNome().equalsIgnoreCase(nome)) {
                return disciplina;
            }
        }
        return null;
    }

    public ArrayList<Disciplina> listarDisciplinas() {
        carregarDados();
        return disciplinas;
    }
}
