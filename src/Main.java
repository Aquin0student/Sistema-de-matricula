import Controller.ProfessorController;
import DAO.*;
import Enums.StatusDisciplina;
import Enums.StatusMatricula;
import Enums.TipoDisciplina;
import Models.*;

import static Controller.ProfessorController.criarProfessor;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UniversidadeDao universidadeDAO = new UniversidadeDao();
        Scanner scan = new Scanner(System.in);
        AlunoDao alunoDAO = new AlunoDao();
        Universidade universidade = new Universidade();
        MatriculaDao matriculaDAO = new MatriculaDao();
        ArrayList<Matricula> matriculas = new ArrayList<>();
        matriculas = matriculaDAO.listarMatriculas();
        for (Matricula matricula : matriculas) {
            System.out.println(matricula.getNumero());
        }
        menuLogin();

    }

    public static boolean verificarUniversidade() {
        UniversidadeDao universidadeDAO = new UniversidadeDao();
        return !universidadeDAO.carregarUniversidades().isEmpty();
    }

    public static boolean verificarSenha(Universidade universidade) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nInsira a senha da universidade ");
        int senha = scan.nextInt();
        return universidade.getSenha() == senha;
    }


    public static Universidade criarUniversidade() {
        UniversidadeDao universidadeDAO = new UniversidadeDao();
        System.out.println("\nDigite o nome da Universidade: ");
        Scanner scan = new Scanner(System.in);
        String nome = scan.nextLine();
        System.out.println("\nInsira a senha numerica do administrativo: ");
        int senha = scan.nextInt();
        Universidade universidade = new Universidade(nome);
        universidade.setSenha(senha);
        universidadeDAO.salvarDados(universidade);
        return universidade;
    }

    public static void menuAdmnistrativo(Universidade universidade) {
        Scanner scan = new Scanner(System.in);
        int opcao;
        do{
            System.out.println("Bem-vindo ao sistema administrativo da universidade " + universidade.getNome() + "!");
            System.out.println("1. Gerenciar alunos");
            System.out.println("2. Gerenciar cursos");
            System.out.println("3. Gerenciar professores");
            System.out.println("4. Gerenciar disciplinas");
            System.out.println("5. Sair");

            opcao = scan.nextInt();

            switch(opcao) {
                case 1:
                    gerenciarAlunos();
                    break;
                case 2:
                    System.out.println("Visualização de cursos ainda não implementada.");
                    esperar();
                    limparConsole();
                    break;
                case 3:
                    gerenciarProfessores();
                    break;

                case 4:
                    gerenciarDisciplinas();
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }while(opcao != 5);

    }

    public static void menuAlunos(Aluno aluno) {
        Scanner scan = new Scanner(System.in);
        int opcao;
        SistemaDeCobrancas sistemaDeCobrancas = new SistemaDeCobrancas();
        sistemaDeCobrancas.cobrarAluno(aluno);

        do{
            System.out.println("\nBem vindo ao menu universitario: ");
            System.out.println("1. Verificar status da matricula ");
            System.out.println("2. Verificar disciplinas matriculadas ");
            System.out.println("3. Realizar matricula ");
            System.out.println("4. Cancelar matricula na disciplina");
            System.out.println("5. Sair");
            opcao = scan.nextInt();

            switch(opcao) {
                case 1:
                    verificarStatusMatricula(aluno);
                    break;
                case 2:
                    verificarDisciplinasMatriculadas(aluno);
                    break;

                case 3:
                    realizarMatricula(aluno);
                    break;

                case 4:
                    cancelarMatriculaNaDisciplina(aluno);
                    break;

                case 5:
                    System.out.println("Saindo...");
                    System.exit(0);

                default:
                    System.out.println("Opcao invalida. Tente novamente.");
                    break;
            }

        }while (opcao != 0);
    }

    public static void menuLogin(){
        Scanner scan = new Scanner(System.in);
        int opcao;

        do{
            System.out.println("\nEscolha o tipo de usuario: ");
            System.out.println("1. Aluno");
            System.out.println("2. Professor");
            System.out.println("3. Secretaria");
            opcao = scan.nextInt();

            switch(opcao) {
                case 1:
                    loginAlunos();
                    break;

                case 2:
                    break;1

                case 3:
                    loginSecretaria();
                    break;

                default:
                    System.out.println("Opcao invalida. Tente novamente.");
                    break;
            }

        }while(opcao != 0);
    }

    public static void loginAlunos() {
        Scanner scan = new Scanner(System.in);
        MatriculaDao matriculaDAO = new MatriculaDao();
        Matricula matricula;
        boolean acesso = false;
        do{
            System.out.println("\nInsira o numero de matricula: ");
            int numMatricula = scan.nextInt();
            matricula = matriculaDAO.buscarMatriculaPorNumero(numMatricula);

            if(matricula == null){
                System.out.println("Deseja voltar ao menu principal? (S/N)");
                String opcao = scan.next();
                if(opcao.equals("S")){
                    break;
                }
            }else{
                System.out.println("\n Insira a senha: ");
                int senha = scan.nextInt();
                if (matricula.getAluno().getSenha() == senha) {
                    acesso = true;
                    menuAlunos(matricula.getAluno());
                }else{
                    System.out.println("\n Senha incorreta: ");
                    System.out.println("Deseja voltar ao menu principal? (S/N)");
                    String opcao = scan.next();
                    if(opcao.equals("S")){
                        break;
                    }
                }
            }


        }while(!acesso);


    }

    public static void loginSecretaria() {
        Scanner scan = new Scanner(System.in);
        UniversidadeDao universidadeDAO = new UniversidadeDao();
        Universidade universidade = new Universidade();

        if (verificarUniversidade()) {
            boolean acesso = false;
            while (!acesso) {
                System.out.println("\nInsira o nome da universidade ");
                String nome = scan.nextLine();
                universidade = universidadeDAO.buscarUniversidade(nome);
                if (universidade != null) {
                    if (verificarSenha(universidade)) {
                        acesso = true;
                    }else{
                        System.out.println("Senha incorreta!");
                    }

                }else{
                    System.out.println("\nUniversidade nao encontrada \nDeseja criar uma? (SIM/NAO)  ");
                    String opcao = scan.nextLine();
                    if (opcao.equalsIgnoreCase("Sim")) {
                        criarUniversidade();
                    }
                }
            }


            menuAdmnistrativo(universidade);
        } else {
            System.out.println("\nNão há universidades cadastradas, prosseguindo para criar uma");
            universidade = criarUniversidade();
            menuAdmnistrativo(universidade);
        }
    }

    public static void gerenciarAlunos() {
        Scanner scan = new Scanner(System.in);

        AlunoDao alunoDAO = new AlunoDao();
        MatriculaDao matriculaDAO = new MatriculaDao();
        Aluno aluno;
        int opcao;

        do{
            System.out.println("Gerencia de alunos: ");
            System.out.println("1- Criar aluno: ");
            System.out.println("2- Cancelar matricula de aluno: ");
            System.out.println("3- Ativar matricula de aluno: ");
            System.out.println("4- Voltar: ");

            opcao = scan.nextInt();

            switch (opcao) {
                case 1:
                    aluno = criarAluno();
                    Matricula matricula = criarMatricula();
                    aluno.setMatricula(matricula);
                    matricula.setAluno(aluno);
                    alunoDAO.adicionarAluno(aluno);
                    matriculaDAO.adicionarMatricula(matricula);
                    esperar();
                    limparConsole();
                    break;
                case 2:
                    aluno = buscarAlunoPorMatricula();
                    if (aluno == null) {
                        break;
                    }
                    cancelarMatricula(aluno);
                    esperar();
                    limparConsole();
                    break;

                case 3:
                    aluno = buscarAlunoPorMatricula();
                    if (aluno == null) {
                        break;
                    }
                    ativarMatricula(aluno);
                    esperar();
                    limparConsole();
                    break;

                case 4:
                    System.out.println("Voltando...");
                    esperar();
                    limparConsole();
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }while (opcao != 4);
    }

    public static Matricula criarMatricula() {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        MatriculaDao matriculaDAO = new MatriculaDao();
        ArrayList<Matricula> matriculas = matriculaDAO.listarMatriculas();
        int numMatricula;
        boolean isUnique;

        do {
            numMatricula = 100000 + rand.nextInt(900000);
            isUnique = true;
            for (Matricula matricula : matriculas) {
                if (matricula.getNumero() == numMatricula) {
                    isUnique = false;
                    break;
                }
            }
        } while (!isUnique);
        Matricula matricula = new Matricula(numMatricula);
        System.out.println("Número de matrícula único gerado: " + numMatricula);
        return matricula;
    }

    public static Aluno criarAluno() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira o nome do Aluno: ");
        String nome = scan.nextLine();
        System.out.println("Insira a senha numerica do Aluno: ");
        int senha = scan.nextInt();
        Aluno aluno = new Aluno(nome);
        aluno.setSenha(senha);

        return aluno;
    }

    public static Aluno buscarAlunoPorMatricula(){
        AlunoDao alunoDAO = new AlunoDao();
        Scanner scan = new Scanner(System.in);
        Aluno aluno = null;

        while (aluno == null) {
            System.out.println("Insira o nome do aluno: ");
            String nomeAluno = scan.next();
            aluno = alunoDAO.buscarAlunoPorNome(nomeAluno);
            if(aluno == null){
                System.out.println("Deseja voltar ao menuAdmnistrativo? (S/N)");
                String opcao = scan.next();
                if (opcao.equalsIgnoreCase("S")) {
                    break;
                }
            }


        }
        return aluno;
    }

    public static void esperar(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void limparConsole() {
        for (int i = 0; i < 50; ++i) {
            System.out.println();
        }
    }

    public static void cancelarMatricula(Aluno aluno) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Deseja cancelar a matricula do aluno " + aluno.getNome() + "?  (S/N)");
        String opcao = scan.next();
        if (opcao.equalsIgnoreCase("S")) {
            aluno.getMatricula().setStatusMatricula(StatusMatricula.CANCELADA);
            System.out.println("Matricula " + aluno.getMatricula().getNumero() + " cancelada");
        }
    }

    public static void ativarMatricula(Aluno aluno) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Deseja ativar a matricula do aluno " + aluno.getNome() + "?  (S/N)");
        String opcao = scan.next();
        if (opcao.equalsIgnoreCase("S")) {
            aluno.getMatricula().setStatusMatricula(StatusMatricula.ATIVA);
            System.out.println("Matricula " + aluno.getMatricula().getNumero() + " ativada");
        }
    }

    // Menu Professor

    public static void gerenciarProfessores() {
        Scanner scan = new Scanner(System.in);

        AlunoDao alunoDAO = new AlunoDao();
        MatriculaDao matriculaDAO = new MatriculaDao();
        Aluno aluno;
        int opcao;

        do{
            System.out.println("Gerencia de professores: ");
            System.out.println("1- Adiciona professor: ");
            System.out.println("2- Listar professor: ");
            System.out.println("2- Remover professor: ");
            System.out.println("4- Voltar: ");

            opcao = scan.nextInt();

            switch (opcao) {
                case 1:
                    Professor professor = criarProfessor();
                    esperar();
                    limparConsole();
                    break;
                case 2:
                    ProfessorController professorController = new ProfessorController();
                    professorController.listarProfessores();
                    break;

                case 3:
                    System.out.println("Voltando...");
                    esperar();
                    limparConsole();
                    break;

                case 4:
                    System.out.println("Voltando...");
                    esperar();
                    limparConsole();
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }while (opcao != 4);
    }

    public static void gerenciarCursos() {
        Scanner scan = new Scanner(System.in);
        int opcao;
        do{
            System.out.println("Gerencia de cursos: ");
            System.out.println("1- Adicionar curso: ");
            System.out.println("2- Adicionar disciplina ao curso: ");
            System.out.println("3- Listar cursos: ");
            System.out.println("4- Voltar: ");
            opcao = scan.nextInt();

            switch (opcao) {
                case 1:
                    criarCurso();
                    break;

                case 2:
                    adicionarDisciplinaAoCurso();
                    break;

            }
        }while (opcao != 4);

    }

    public static void gerenciarDisciplinas() {
        Scanner scan = new Scanner(System.in);
        int opcao;

        do{
            System.out.println("Gerencia de disciplinas: ");
            System.out.println("1- Criar disciplina: ");
            System.out.println("2- Verificar matriculas para a disciplina: ");
            System.out.println("3- Listar disciplinas: ");
            System.out.println("4- Voltar: ");
            opcao = scan.nextInt();

            switch (opcao) {
                case 1:
                    criarDisciplina();
                    break;

                case 2:
                    verificarMatriculasDisciplina();
                    break;

                case 3:
                    listarDisciplinas();
                    break;
            }
        }while (opcao != 4);
    }

    public static void criarCurso(){
        Scanner scan = new Scanner(System.in);
        CursoDao cursoDAO = new CursoDao();
        System.out.println("Digite o nome do curso: ");
        Curso curso = new Curso(scan.next());
        System.out.println("Digite o total de creditos do curso " + curso.getNome());
        curso.setNumCreditos(scan.nextInt());

        cursoDAO.adicionarCurso(curso);
        System.out.println("Curso " + curso.getNome() + " adicionado com sucesso!");
    }

    public static void adicionarDisciplinaAoCurso(){
        Scanner scan = new Scanner(System.in);
    }

    public static void criarDisciplina(){
        Scanner scan = new Scanner(System.in);
        DisciplinaDao disciplinaDAO = new DisciplinaDao();
        System.out.println("Digite o nome do disciplina: ");
        Disciplina disciplina = new Disciplina(scan.next());
        System.out.println("Digite o total de creditos da disciplina " + disciplina.getNome() + " :");
        disciplina.setNumCreditos(scan.nextInt());
        System.out.println("Digite o tipo de disciplina: (OBRIGATORIA): 1 (OPTATIVA): 2 )");
        int tipo = scan.nextInt();
        if (tipo == 1) {
            disciplina.setTipoDisciplina(TipoDisciplina.OBRIGATORIA);
        }else{
            disciplina.setTipoDisciplina(TipoDisciplina.OPTATIVA);
        }

        DisciplinaDao disciplinaDao = new DisciplinaDao();
        disciplinaDao.adicionarDisciplina(disciplina);
        System.out.println("Disciplina adicionada com sucesso!");
    }

    public static void verificarMatriculasDisciplina(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o nome do disciplina: ");
        DisciplinaDao disciplinaDAO = new DisciplinaDao();
        Disciplina disciplina = disciplinaDAO.buscarDisciplinaPorNome(scan.next());
        if (disciplina == null) {
            System.out.println("Disciplina nao encontrada!");
        }else{
            ArrayList<Matricula> matriculas = disciplina.getMatriculas();
            int totalMatriculas = matriculas.size();
            for (Matricula matricula : matriculas) {
                System.out.println(matricula.getNumero());
            }
            System.out.println("Total de matriculas: " + totalMatriculas);

            if(totalMatriculas < 3){
                System.out.println("Não há alunos suficientes para a disciplina " + disciplina.getNome());
                System.out.println("A disciplina " + disciplina.getNome() + "estara desativa ate a quantidade minima de alunos matriculados for atingida ");
                disciplina.setStatusDisciplina(StatusDisciplina.CANCELADA);
            }else{
                disciplina.setStatusDisciplina(StatusDisciplina.ATIVA);
            }
        }
    }

    public static void listarDisciplinas(){
        DisciplinaDao disciplinaDAO = new DisciplinaDao();
        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        disciplinas = disciplinaDAO.listarDisciplinas();
        for (Disciplina disciplina : disciplinas) {
            System.out.println(disciplina.getNome());
        }
    }

    public static void verificarStatusMatricula(Aluno aluno){
        Matricula matricula = aluno.getMatricula();
        StatusMatricula statusMatricula = matricula.getStatusMatricula();
        System.out.println("Status atual da matricula " + statusMatricula);
        esperar();
        limparConsole();
    }

    public static void verificarDisciplinasMatriculadas(Aluno aluno){
        DisciplinaDao disciplinaDAO = new DisciplinaDao();
        Matricula matricula = aluno.getMatricula();
        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        disciplinas = matricula.getDisciplinas();

        if (disciplinas.isEmpty()) {
            System.out.println("Não ha nenhuma disciplina matriculada!");
        }else{
            for (Disciplina disciplina : disciplinas) {
                System.out.println(disciplina.getNome());
            }
        }

        esperar();
        limparConsole();
    }

    public static void realizarMatricula(Aluno aluno){
        Matricula matricula = aluno.getMatricula();
        MatriculaDao matriculaDao = new MatriculaDao();
        DisciplinaDao disciplinaDAO = new DisciplinaDao();
        Scanner scan = new Scanner(System.in);
        ArrayList<Disciplina> disciplinas = disciplinaDAO.listarDisciplinas();
        String opcao;
        do{
            System.out.println("Lista de disciplinas: ");
            for(Disciplina disciplina : disciplinas){
                System.out.println(disciplina.getNome());
            }

            System.out.println("Escolha uma disciplina: (ou SAIR para encerrar) ");
            opcao = scan.nextLine();

            for(Disciplina disciplina : disciplinas){
                if(opcao.equalsIgnoreCase(disciplina.getNome())){
                    matricula.adicionarDisciplina(disciplina);
                    disciplina.adicionarMatricula(matricula);

                }
            }

        }while(opcao.equalsIgnoreCase("SAIR"));
    }

    public static void cancelarMatriculaNaDisciplina(Aluno aluno){
        Scanner scan = new Scanner(System.in);
        Matricula matricula = aluno.getMatricula();
        MatriculaDao matriculaDao = new MatriculaDao();
        DisciplinaDao disciplinaDAO = new DisciplinaDao();
        ArrayList<Disciplina> disciplinas = matricula.getDisciplinas();
        String opcao;
        do{
            System.out.println("Lista de disciplinas matriculadas: ");
            for(Disciplina disciplina : disciplinas){
                System.out.println(disciplina.getNome());
            }

            System.out.println("Escolha uma disciplina: (ou SAIR para encerrar) ");
            opcao = scan.nextLine();

            for(Disciplina disciplina : disciplinas){
                if(opcao.equalsIgnoreCase(disciplina.getNome())){
                    matricula.removerDisciplina(disciplina);
                    disciplina.removerMatricula(matricula);
                    System.out.println("Matricula removida com sucesso!");
                }
            }

        }while (opcao.equalsIgnoreCase("SAIR"));
        esperar();
        limparConsole();
    }


}