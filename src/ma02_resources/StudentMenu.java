package ma02_resources;

import ma02_resources.CBLManager;
import ma02_resources.participants.Student;
import ma02_resources.project.Edition;
import ma02_resources.project.Project;
import ma02_resources.project.Status;
import ma02_resources.project.Submission;
import ma02_resources.project.SubmissionImpl;
import ma02_resources.project.Task;

import java.time.LocalDateTime;
import java.util.Scanner;

public class StudentMenu {
    private CBLManager cblManager;

    public StudentMenu(CBLManager cblManager) {
        this.cblManager = cblManager;
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Menu Estudante:");
            System.out.println("1. Adicionar submissão");
            System.out.println("2. Listar tarefa");
            System.out.println("3. Listar todas as tarefas");
            System.out.println("4. Progresso do projeto");
            System.out.println("5. Obter todas as submissões");
            System.out.println("6. Atribuir autoavaliação");
            System.out.println("0. Sair");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (option) {
                case 1:
                    // Adicionar submissão
                    Edition activeEdition = cblManager.getActiveEdition();
                    if (activeEdition == null || activeEdition.getStatus() != Status.ACTIVE) {
                        System.out.println("Esta edição não está ativa, você não pode adicionar submissões");
                        break;
                    }
                    System.out.println("Digite o nome do projeto ao qual a submissão será adicionada:");
                    String projectNameForSubmission = scanner.nextLine();
                    System.out.println("Digite o título da tarefa à qual a submissão será adicionada:");
                    String taskTitleForSubmission = scanner.nextLine();
                    System.out.println("Digite o e-mail do estudante que está fazendo a submissão:");
                    String studentEmailForSubmission = scanner.nextLine();
                    System.out.println("Digite o texto da submissão:");
                    String submissionText = scanner.nextLine();

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
                case 2:
                    // Listar tarefa
                    System.out.println("Digite o nome do projeto cujas tarefas você deseja listar:");
                    String projectNameForTasks = scanner.nextLine();
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
                case 3:
                    // Listar todas as tarefas
                    System.out.println("Digite o nome da edição cujas tarefas você deseja listar:");
                    String editionNameForTasks = scanner.nextLine();
                    Edition editionForTasks = cblManager.getEdition(editionNameForTasks);
                    if (editionForTasks != null) {
                        for (Project project : editionForTasks.getProjects()) {
                            for (Task task : project.getTasks()) {
                                System.out.println("Tarefa: " + task.getTitle() + " (Projeto: " + project.getName() + ")");
                            }
                        }
                    } else {
                        System.out.println("Edição não encontrada.");
                    }
                    break;
                case 4:
                    // Progresso do projeto
                    System.out.println("Digite o nome do projeto para obter o progresso:");
                    String projectForProgress = scanner.nextLine();
                    Project projectToGetProgress = cblManager.getActiveEdition().getProject(projectForProgress);
                    if (projectToGetProgress != null) {
                        System.out.println(cblManager.getProjectProgress(projectToGetProgress));
                    } else {
                        System.out.println("Projeto não encontrado.");
                    }
                    break;
                case 5:
                    // Obter todas as submissões
                    System.out.println("Digite o nome do projeto para obter todas as submissões:");
                    String projectNameForSubmissions = scanner.nextLine();
                    Project projectForSubmissions = cblManager.getActiveEdition().getProject(projectNameForSubmissions);
                    if (projectForSubmissions != null) {
                        for (Task task : projectForSubmissions.getTasks()) {
                            System.out.println("Submissões para a tarefa: " + task.getTitle());
                            for (Submission submission : task.getSubmissions()) {
                                System.out.println("Submissão de " + submission.getStudent().getName() + ": " + submission.getText());
                            }
                        }
                    } else {
                        System.out.println("Projeto não encontrado.");
                    }
                    break;
                case 6:
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
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
