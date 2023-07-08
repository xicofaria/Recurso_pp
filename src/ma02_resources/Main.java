package ma02_resources;

import ma02_resources.participants.*;
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
        ProjectTemplate projectTemplate = new ProjectTemplate("/run/media/xicosec/Stuff/PP/Recurso/Recurso/src/ma02_resources/project_template.json");

        // Leia o arquivo JSON e crie uma lista de tarefas
        List<Task> tasks = projectTemplate.createTasks();

        // Crie uma nova edição e adicione-a ao CBLManager
        Edition edition = new EditionImpl("Edition Name", LocalDate.now(), "Project Template", Status.ACTIVE);
        cblManager.addEdition(edition);

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

            switch (option) {
                case 1:
                    // Mostrar o menu do administrador
                    adminMenu.showMenu();
                    break;
                case 2:
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
