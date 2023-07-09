package ma02_resources;

import ma02_resources.participants.Participant;
import ma02_resources.project.Edition;
import ma02_resources.project.Project;
import ma02_resources.project.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListsMenu {
    private CBLManager cblManager;
    private Scanner scanner;

    public ListsMenu(CBLManager cblManager) {
        this.cblManager = cblManager;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("Menu de Listas:");
            System.out.println("1. Listar estudantes em uma edição");
            System.out.println("2. Listar projetos com mais de N submissões em uma edição");
            System.out.println("3. Listar projetos concluídos em uma edição");
            System.out.println("0. Voltar");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Digite o nome da edição:");
                    String editionName = scanner.nextLine();
                    Edition edition = cblManager.getEdition(editionName);
                    if (edition != null) {
                        Participant[] students = cblManager.getStudentsInEdition(edition);
                        for (Participant student : students) {
                            System.out.println(student.getName());
                        }
                    } else {
                        System.out.println("Edição não encontrada.");
                    }
                    break;


                case 2:
                    System.out.println("Digite o nome da edição:");
                    String editionNameForSubmissions = scanner.nextLine();
                    System.out.println("Digite o número mínimo de submissões:");
                    int n = scanner.nextInt();
                    Edition editionForSubmissions = cblManager.getEdition(editionNameForSubmissions);
                    if (editionForSubmissions != null) {
                        Project[] projects = cblManager.getProjectsWithMoreThanNSubmissions(editionForSubmissions, n);
                        for (Project project : projects) {
                            System.out.println(project.getName());
                        }
                    } else {
                        System.out.println("Edição não encontrada.");
                    }
                    break;

                case 3:
                    System.out.println("Digite o nome da edição:");
                    String editionNameForCompletion = scanner.nextLine();
                    Edition editionForCompletion = cblManager.getEdition(editionNameForCompletion);
                    if (editionForCompletion != null) {
                        Project[] completedProjects = cblManager.getCompletedProjectsInEdition(editionForCompletion);
                        for (Project project : completedProjects) {
                            System.out.println(project.getName());
                        }
                    } else {
                        System.out.println("Edição não encontrada.");
                    }
                    break;

                case 0:
                    exit = true;
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
