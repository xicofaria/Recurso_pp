package ma02_resources;

import ma02_resources.participants.Participant;
import ma02_resources.project.Edition;
import ma02_resources.project.Project;
import ma02_resources.project.Task;
import ma02_resources.project.TaskImpl;
import ma02_resources.project.exceptions.IllegalNumberOfParticipantType;
import ma02_resources.project.exceptions.IllegalNumberOfTasks;
import ma02_resources.project.exceptions.ParticipantAlreadyInProject;
import ma02_resources.project.exceptions.TaskAlreadyInProject;

import java.time.LocalDate;
import java.util.Scanner;

public class ProjectMenu {
    private CBLManager cblManager;
    private Scanner scanner;

    public ProjectMenu(CBLManager cblManager) {
        this.cblManager = cblManager;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() throws TaskAlreadyInProject, IllegalNumberOfTasks, ParticipantAlreadyInProject, IllegalNumberOfParticipantType {
        boolean exit = false;

        while (!exit) {
            System.out.println("Gerenciar projetos:");
            System.out.println("1. Adicionar projeto");
            System.out.println("2. Remover projeto");
            System.out.println("3. Adicionar participante a um projeto");
            System.out.println("4. Remover participante de um projeto");
            System.out.println("5. Adicionar tarefa a um projeto");
            System.out.println("6. Obter participante");
            System.out.println("7. Listar todos os projetos");
            System.out.println("8. Obter progresso de um projeto");
            System.out.println("9. Obter projeto por nome");
            System.out.println("10. Obter projetos inacabados");
            System.out.println("0. Voltar");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    // Adicionar projeto
                    System.out.println("Digite o nome da edição em que deseja adicionar o projeto:");
                    String targetEdition = scanner.next();
                    System.out.println("Digite o nome do projeto:");
                    String projectNameToAdd = scanner.next();
                    System.out.println("Digite a descrição do projeto:");
                    String projectDescription = scanner.next();
                    cblManager.addProjectToEdition(targetEdition, projectNameToAdd, projectDescription);
                    System.out.println("Projeto adicionado com sucesso.");
                    break;
                case 2:
                    // Remover projeto
                    System.out.println("Digite o nome da edição do projeto a ser removido:");
                    String editionWithProject = scanner.next();
                    System.out.println("Digite o nome do projeto a ser removido:");
                    String projectToRemove = scanner.next();
                    cblManager.removeProjectFromEdition(editionWithProject, projectToRemove);
                    System.out.println("Projeto removido com sucesso.");
                    break;

                case 3:
                    // Adicionar participante a um projeto
                    System.out.println("Digite o nome do projeto ao qual o participante será adicionado:");
                    String projectName = scanner.nextLine();
                    System.out.println("Digite o e-mail do participante:");
                    String participantEmail = scanner.nextLine();
                    Project project = cblManager.getActiveEdition().getProject(projectName);
                    Participant participant = cblManager.getParticipant(participantEmail);
                    if (project != null && participant != null) {
                        project.addParticipant(participant);
                        System.out.println("Participante adicionado com sucesso.");
                    } else {
                        System.out.println("Projeto ou participante não encontrado.");
                    }
                    break;
                case 4:
                    // Remover participante de um projeto
                    System.out.println("Digite o nome do projeto do qual o participante será removido:");
                    String projectToRemoveParticipant = scanner.nextLine();
                    System.out.println("Digite o e-mail do participante a ser removido:");
                    String participantToRemoveEmail = scanner.nextLine();
                    Project projectToRemoveFrom = cblManager.getActiveEdition().getProject(projectToRemoveParticipant);
                    if (projectToRemoveFrom != null) {
                        projectToRemoveFrom.removeParticipant(participantToRemoveEmail);
                        System.out.println("Participante removido com sucesso.");
                    } else {
                        System.out.println("Projeto não encontrado.");
                    }
                    break;

                case 5:
                    // Adicionar tarefa a um projeto
                    System.out.println("Digite o nome do projeto ao qual a tarefa será adicionada:");
                    String projectNameForTask = scanner.next();
                    System.out.println("Digite o título da tarefa:");
                    String taskTitle = scanner.next();
                    System.out.println("Digite a descrição da tarefa:");
                    String taskDescription = scanner.next();
                    System.out.println("Digite a duração da tarefa em dias:");
                    int taskDuration = scanner.nextInt();

                    Project projectForTask = cblManager.getActiveEdition().getProject(projectNameForTask);
                    if (projectForTask != null) {
                        Task newTask = new TaskImpl(LocalDate.now(), taskDuration, taskTitle, taskDescription);
                        projectForTask.addTask(newTask);
                        System.out.println("Tarefa adicionada com sucesso.");
                    } else {
                        System.out.println("Projeto não encontrado.");
                    }
                    break;
                case 6:
                    // Obter participante
                    System.out.println("Digite o e-mail do participante que deseja obter:");
                    String participantEmailToGet = scanner.nextLine();
                    Participant participantToGet = cblManager.getParticipant(participantEmailToGet);
                    if (participantToGet != null) {
                        System.out.println("Participante: " + participantToGet.getName());
                    } else {
                        System.out.println("Participante não encontrado.");
                    }
                    break;
                case 7:
                    // Listar todos os projetos
                    System.out.println("Digite o nome da edição cujos projetos você deseja listar:");
                    String editionNameForProjects = scanner.next();
                    Edition editionForProjects = cblManager.getEdition(editionNameForProjects);
                    if (editionForProjects != null) {
                        System.out.println("Projetos:");
                        for (Project pr : editionForProjects.getProjects()) {
                            System.out.println(pr.getName());
                        }
                    } else {
                        System.out.println("Edição não encontrada.");
                    }
                    break;

                case 8:
                    // Obter progresso de um projeto
                    System.out.println("Digite o nome do projeto para obter o progresso:");
                    String projectForProgress = scanner.nextLine();
                    Project projectToGetProgress = cblManager.getActiveEdition().getProject(projectForProgress);
                    if (projectToGetProgress != null) {
                        System.out.println(cblManager.getProjectProgress(projectToGetProgress));
                    } else {
                        System.out.println("Projeto não encontrado.");
                    }
                    break;
                case 9:
                    // Obter projeto por nome
                    System.out.println("Digite o nome do projeto que deseja obter:");
                    String projectNameToGet = scanner.nextLine();
                    Project projectToGet = cblManager.getActiveEdition().getProject(projectNameToGet);
                    if (projectToGet != null) {
                        System.out.println("Nome do projeto: " + projectToGet.getName());
                    } else {
                        System.out.println("Projeto não encontrado.");
                    }
                    break;
                case 10:
                    // Obter projetos inacabados
                    System.out.println("Projetos inacabados:");
                    for (Project unfinishedProject : cblManager.getProjectsWithMissingSubmissionsFromActiveEdition()) {
                        System.out.println(unfinishedProject.getName());
                    }
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
