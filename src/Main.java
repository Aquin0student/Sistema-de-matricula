import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int opcao = -1;
        Scanner scan = new Scanner(System.in);
        int matricula, senha;

        while (opcao != 0) {
            System.out.println("\nMenu de Login");
            System.out.println("\nSelecione seu usuario");
            System.out.println("\n1 - Aluno");
            System.out.println("\n2 - Secretaria");
            System.out.println("\n3 - Professor");
            System.out.println("\n0 - Sair");
            opcao = scan.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("\nInsira a matricula do aluno: ");
                    matricula = scan.nextInt();
                    System.out.println("\nInsira a senha do aluno: ");
                    senha = scan.nextInt();

                case 2:
                    System.out.println("\nInsira a matricula do aluno: ");
                    matricula = scan.nextInt();
                    System.out.println("\nInsira a senha do aluno: ");
                    senha = scan.nextInt();
                case 3:
                    System.out.println("\nInsira a matricula do aluno: ");
                    matricula = scan.nextInt();
                    System.out.println("\nInsira a senha do aluno: ");
                    senha = scan.nextInt();

            }

        }
    }
}