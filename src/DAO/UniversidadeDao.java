package DAO;

import Models.Disciplina;
import Models.Universidade;
import java.io.*;
import java.util.ArrayList;

public class UniversidadeDao {
    private static final String FILE_NAME = "universidade_data.dat";
    private ArrayList<Universidade> universidades = new ArrayList<>();
    private static UniversidadeDao instancia;

    public void adicionarUniversidade(Universidade universidade) {
        universidades.add(universidade);
        salvarDados();
    }

    public static UniversidadeDao getInstance() {
        if (instancia == null) {
            synchronized (UniversidadeDao.class) {
                if (instancia == null) {
                    instancia = new UniversidadeDao();
                }
            }
        }
        return instancia;
    }


    // Salva a lista de universidades no arquivo
    public void salvarUniversidade(Universidade universidade) {
        ArrayList<Universidade> universidades = carregarUniversidades();
        universidades.add(universidade);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(universidades);
            System.out.println("Universidade salva com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar universidade: " + e.getMessage());
        }
    }

    public void adicionarUniversidade(Universidade universidade) {
        universidades.add(universidade);
        salvarUniversidade(universidade);
    }
    // Carrega todas as universidades do arquivo
    public ArrayList<Universidade> carregarUniversidades() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Universidade>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>(); // Retorna uma lista vazia caso o arquivo não exista ou esteja vazio
        }
    }

    // Busca uma universidade pelo nome
    public Universidade buscarUniversidade(String nome) {
        ArrayList<Universidade> universidades = carregarUniversidades();
        for (Universidade universidade : universidades) {
            if (universidade.getNome().equalsIgnoreCase(nome)) {
                return universidade;
            }
        }
        return null; // Retorna null se não encontrar
    }

    // Remove uma universidade pelo nome
    public boolean removerUniversidade(String nome) {
        ArrayList<Universidade> universidades = carregarUniversidades();
        for (Universidade universidade : universidades) {
            if (universidade.getNome().equalsIgnoreCase(nome)) {
                universidades.remove(universidade);
                salvarListaUniversidades(universidades);
                return true;
            }
        }
        return false;
    }

    // Método auxiliar para salvar a lista de universidades
    private void salvarListaUniversidades(ArrayList<Universidade> universidades) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(universidades);
        } catch (IOException e) {
            System.err.println("Erro ao salvar lista de universidades: " + e.getMessage());
        }
    }
}
