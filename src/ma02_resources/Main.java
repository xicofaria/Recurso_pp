package ma02_resources;

import ma02_resources.participants.Student;
import ma02_resources.project.*;
import ma02_resources.project.exceptions.IllegalNumberOfParticipantType;
import ma02_resources.project.exceptions.IllegalNumberOfTasks;
import ma02_resources.project.exceptions.ParticipantAlreadyInProject;
import ma02_resources.project.exceptions.TaskAlreadyInProject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws TaskAlreadyInProject, IllegalNumberOfTasks, ParticipantAlreadyInProject, IllegalNumberOfParticipantType {
        // Crie uma instância da classe CBLManager
        CBLManager cblManager = new CBLManager();

        // Crie uma instância da classe ProjectTemplate com o caminho do arquivo JSON
        ProjectTemplate projectTemplate = new ProjectTemplate("/run/media/xicosec/Stuff/PP/Recurso/Recurso/src/ma02_resources/resources/project_template.json");

        // Leia o arquivo JSON e crie uma lista de tarefas
        List<Task> tasks = projectTemplate.createTasks();

        // Verifique a existência da classe InitialTestingValues e instancie-a se existir
        try {
            Class.forName("ma02_resources.InitialTestingValues");
            InitialTestingValues initialTestingValues = new InitialTestingValues();
            Edition edition = initialTestingValues.getEdition();
            cblManager.addEdition(edition);

            // Aqui você pode usar os métodos de initialTestingValues conforme necessário
        } catch (ClassNotFoundException e) {
            System.out.println("A classe InitialTestingValues não foi encontrada.");
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao instanciar a classe InitialTestingValues: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        // Crie uma instância das classes de menu
        AdminMenu adminMenu = new AdminMenu(cblManager);
        StudentMenu studentMenu = new StudentMenu(cblManager);
        ListsMenu listsMenu = new ListsMenu(cblManager);

        while (!exit) {
            System.out.println("Menu:");
            System.out.println("1. Menu do Administrador");
            System.out.println("2. Menu do Estudante");
            System.out.println("3. Menu de Listas");
            System.out.println("0. Sair");

            int option = scanner.nextInt();
            scanner.nextLine();  // consumir o caractere de nova linha restante

            switch (option) {
                case 1:
                    // Mostrar o menu do administrador
                    adminMenu.showMenu();
                    break;
                case 2:
                    // Mostrar o menu do estudante
                    // Solicitar informações antes de mostrar o menu do estudante
                    System.out.println("Digite o e-mail do estudante:");
                    String studentEmail = scanner.nextLine();
                    System.out.println("Digite o nome do estudante:");
                    String studentName = scanner.nextLine();
                    System.out.println("Digite o nome da edição:");
                    String editionName = scanner.nextLine();
                    System.out.println("Digite o nome do projeto:");
                    String projectName = scanner.nextLine();

                    // informações para o menu do estudante
                    studentMenu.setStudentEmail(studentEmail);
                    studentMenu.setStudentName(studentName);
                    studentMenu.setEditionName(editionName);
                    studentMenu.setProjectName(projectName);

                    // Mostrar o menu do estudante

                    studentMenu.showMenu();
                    break;
                case 3:
                    // Mostrar o menu de listas
                    listsMenu.showMenu();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }
}
