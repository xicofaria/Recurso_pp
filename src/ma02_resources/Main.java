package ma02_resources;

import ma02_resources.participants.*;
import ma02_resources.project.*;
import ma02_resources.project.exceptions.IllegalNumberOfTasks;
import ma02_resources.project.exceptions.TaskAlreadyInProject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws TaskAlreadyInProject, IllegalNumberOfTasks {
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

        while (!exit) {
            System.out.println("Menu:");
            System.out.println("1. Adicionar edição");
            System.out.println("2. Remover edição");
            System.out.println("3. Listar edições");
            System.out.println("4. Adicionar projeto");
            System.out.println("5. Remover projeto");
            System.out.println("6. Listar projetos");
            System.out.println("7. Adicionar tarefa");
            System.out.println("8. Remover tarefa");
            System.out.println("9. Listar tarefas");
            System.out.println("10. Adicionar submissão");
            System.out.println("11. Atribuir autoavaliação");
            System.out.println("12. Atribuir heteroavaliação");
            System.out.println("13. Gerar avaliação final");
            System.out.println("14. Exportar dados da edição ativa em CSV");
            System.out.println("15. Exportar dados da edição ativa em JSON");
            System.out.println("0. Sair");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Digite o nome da edição:");
                    String editionName = scanner.next();
                    Edition newEdition = new EditionImpl(editionName, LocalDate.now(), "Project Template", Status.INACTIVE);
                    cblManager.addEdition(newEdition);
                    System.out.println("Edição adicionada com sucesso.");
                    break;

                case 2:
                    System.out.println("Digite o nome da edição a ser removida:");
                    String editionToRemove = scanner.next();
                    cblManager.removeEdition(editionToRemove);
                    System.out.println("Edição removida com sucesso.");
                    break;

                case 3:
                    System.out.println("Edições:");
                    for (Edition ed : cblManager.getEditions()) {
                        System.out.println(ed.getName());
                    }
                    break;

                case 4:
                    System.out.println("Digite o nome da edição em que deseja adicionar o projeto:");
                    String targetEdition = scanner.next();
                    System.out.println("Digite o nome do projeto:");
                    String projectName = scanner.next();
                    System.out.println("Digite a descrição do projeto:");
                    String projectDescription = scanner.next();
                    cblManager.addProjectToEdition(targetEdition, projectName, projectDescription);
                    System.out.println("Projeto adicionado com sucesso.");
                    break;

                case 5:
                    System.out.println("Digite o nome da edição do projeto a ser removido:");
                    String editionWithProject = scanner.next();
                    System.out.println("Digite o nome do projeto a ser removido:");
                    String projectToRemove = scanner.next();
                    cblManager.removeProjectFromEdition(editionWithProject, projectToRemove);
                    System.out.println("Projeto removido com sucesso.");
                    break;

                case 6:
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

                case 7:
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


                case 8:
                    System.out.println("Digite o nome do projeto da tarefa a ser removida:");
                    String projectNameForTaskRemoval = scanner.next();
                    System.out.println("Digite o título da tarefa a ser removida:");
                    String taskTitleForRemoval = scanner.next();

                    Project projectForTaskRemoval = cblManager.getActiveEdition().getProject(projectNameForTaskRemoval);
                    if (projectForTaskRemoval != null) {
                        Task taskToRemove = projectForTaskRemoval.getTask(taskTitleForRemoval);
                        if (taskToRemove != null) {
                            projectForTaskRemoval.removeTask(taskTitleForRemoval);
                            System.out.println("Tarefa removida com sucesso.");
                        } else {
                            System.out.println("Tarefa não encontrada.");
                        }
                    } else {
                        System.out.println("Projeto não encontrado.");
                    }
                    break;

                case 9:
                    System.out.println("Digite o nome do projeto cujas tarefas você deseja listar:");
                    String projectNameForTasks = scanner.next();
                    Project projectForTasks = cblManager.getActiveEdition().getProject(projectNameForTasks);
                    if (projectForTasks != null) {
                        System.out.println("Tarefas:");
                        for (Task task : projectForTasks.getTasks()) {
                            System.out.println(task.getTitle());
                        }
                    } else {
                        System.out.println("Projeto não encontrado.");
                    }
                    break;
                case 10:
                    System.out.println("Digite o nome do projeto ao qual a submissão será adicionada:");
                    String projectNameForSubmission = scanner.next();
                    System.out.println("Digite o título da tarefa à qual a submissão será adicionada:");
                    String taskTitleForSubmission = scanner.next();
                    System.out.println("Digite o e-mail do estudante que está fazendo a submissão:");
                    String studentEmailForSubmission = scanner.next();
                    System.out.println("Digite o texto da submissão:");
                    String submissionText = scanner.next();

                    Student studentForSubmission = (Student) cblManager.getParticipant(studentEmailForSubmission);
                    if (studentForSubmission != null) {
                        Project projectForSubmission = cblManager.getActiveEdition().getProject(projectNameForSubmission);
                        if (projectForSubmission != null) {
                            Submission submission = new SubmissionImpl(LocalDateTime.now(), studentForSubmission, submissionText);
                            cblManager.addSubmissionToActiveEdition(projectNameForSubmission, taskTitleForSubmission, submission);
                            System.out.println("Submissão adicionada com sucesso.");
                        } else {
                            System.out.println("Projeto não encontrado.");
                        }
                    } else {
                        System.out.println("Estudante não encontrado.");
                    }
                    break;

                case 11:
                    System.out.println("Digite o e-mail do estudante para atribuir autoavaliação:");
                    String studentEmailForSelfEvaluation = scanner.next();
                    System.out.println("Digite a nota de autoavaliação:");
                    double selfEvaluationGrade = scanner.nextDouble();

                    Student studentForSelfEvaluation = (Student) cblManager.getParticipant(studentEmailForSelfEvaluation);
                    if (studentForSelfEvaluation != null) {
                        cblManager.assignSelfEvaluation(studentForSelfEvaluation, selfEvaluationGrade);
                        System.out.println("Autoavaliação atribuída com sucesso.");
                    } else {
                        System.out.println("Estudante não encontrado.");
                    }
                case 12:
                    System.out.println("Digite o e-mail do estudante para atribuir heteroavaliação:");
                    String studentEmailForPeerEvaluation = scanner.next();
                    System.out.println("Digite a nota de heteroavaliação:");
                    double peerEvaluationGrade = scanner.nextDouble();

                    Student studentForPeerEvaluation = (Student) cblManager.getParticipant(studentEmailForPeerEvaluation);
                    if (studentForPeerEvaluation != null) {
                        cblManager.assignPeerEvaluation(studentForPeerEvaluation, peerEvaluationGrade);
                        System.out.println("Heteroavaliação atribuída com sucesso.");
                    } else {
                        System.out.println("Estudante não encontrado.");
                    }
                    break;

                case 13:
                    System.out.println("Digite o e-mail do estudante para gerar a avaliação final:");
                    String studentEmailForFinalEvaluation = scanner.next();

                    Student studentForFinalEvaluation = (Student) cblManager.getParticipant(studentEmailForFinalEvaluation);
                    if (studentForFinalEvaluation != null) {
                        double finalEvaluation = cblManager.generateFinalEvaluation(studentForFinalEvaluation);
                        System.out.println("Avaliação final: " + finalEvaluation);
                    } else {
                        System.out.println("Estudante não encontrado.");
                    }
                    break;
                case 14:
                    cblManager.exportDataCSV();
                    break;
                case 15:
                    cblManager.exportDataJSON();
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
