package ma02_resources;

import ma02_resources.CBLManager;
import ma02_resources.participants.Student;
import ma02_resources.project.exceptions.IllegalNumberOfParticipantType;
import ma02_resources.project.exceptions.IllegalNumberOfTasks;
import ma02_resources.project.exceptions.ParticipantAlreadyInProject;
import ma02_resources.project.exceptions.TaskAlreadyInProject;

import java.util.Scanner;

public class AdminMenu {
    private CBLManager cblManager;
    private Scanner scanner;
    private EditionMenu editionMenu;
    private ProjectMenu projectMenu;

    public AdminMenu(CBLManager cblManager) {
        this.cblManager = cblManager;
        this.scanner = new Scanner(System.in);
        this.editionMenu = new EditionMenu(cblManager);
        this.projectMenu = new ProjectMenu(cblManager);
    }

    public void showMenu() throws ParticipantAlreadyInProject, IllegalNumberOfParticipantType, TaskAlreadyInProject, IllegalNumberOfTasks {
        boolean exit = false;

        while (!exit) {
            System.out.println("Menu Admin:");
            System.out.println("1. Gerenciar edições");
            System.out.println("2. Gerenciar projetos");
            System.out.println("3. Atribuir heteroavaliação");
            System.out.println("4. Gerar avaliação final");
            System.out.println("5. Exportar dados da edição ativa em CSV");
            System.out.println("6. Exportar dados da edição ativa em JSON");
            System.out.println("0. Sair");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    editionMenu.showMenu();
                    break;
                case 2:
                    projectMenu.showMenu();
                    break;
                case 3:
                    System.out.println("Digite o e-mail do estudante para atribuir heteroavaliação:");
                    String studentEmailForPeerEvaluation = scanner.nextLine();
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
                case 4:
                    System.out.println("Digite o e-mail do estudante para gerar a avaliação final:");
                    String studentEmailForFinalEvaluation = scanner.nextLine();

                    Student studentForFinalEvaluation = (Student) cblManager.getParticipant(studentEmailForFinalEvaluation);
                    if (studentForFinalEvaluation != null) {
                        double finalEvaluation = cblManager.generateFinalEvaluation(studentForFinalEvaluation);
                        System.out.println("Avaliação final: " + finalEvaluation);
                    } else {
                        System.out.println("Estudante não encontrado.");
                    }
                    break;
                case 5:
                    cblManager.exportDataCSV();
                    break;
                case 6:
                    cblManager.exportDataJSON();
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
