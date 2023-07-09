package ma02_resources;

import ma02_resources.CBLManager;
import ma02_resources.project.Edition;
import ma02_resources.project.EditionImpl;
import ma02_resources.project.Status;

import java.time.LocalDate;
import java.util.Scanner;

public class EditionMenu {
    private CBLManager cblManager;
    private Scanner scanner;

    public EditionMenu(CBLManager cblManager) {
        this.cblManager = cblManager;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("Gerenciar edições:");
            System.out.println("1. Adicionar edição");
            System.out.println("2. Remover edição");
            System.out.println("3. Definir edição como ativa");
            System.out.println("4. Definir edição como inativa");
            System.out.println("5. Definir edição como fechada");
            System.out.println("6. Definir edição como cancelada");
            System.out.println("7. Listar todas as edições");
            System.out.println("8. Obter edição");
            System.out.println("9. Obter progresso da edição");
            System.out.println("10. Obter edição ativa");
            System.out.println("11. Obter edições inativas");
            System.out.println("12. Obter edições fechadas");
            System.out.println("13. Obter edições canceladas");
            System.out.println("14. Obter edições inacabadas");
            System.out.println("0. Voltar");

            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over

            switch (option) {
                case 1:
                    // Adicionar edição
                    System.out.println("Digite o nome da edição:");
                    String editionName = scanner.nextLine();
                    Edition newEdition = new EditionImpl(editionName, LocalDate.now(), "Project Template", Status.INACTIVE);
                    cblManager.addEdition(newEdition);
                    System.out.println("Edição adicionada com sucesso.");
                    break;

                case 2:
                    // Remover edição
                    System.out.println("Digite o nome da edição a ser removida:");
                    String editionToRemove = scanner.nextLine();
                    cblManager.removeEdition(editionToRemove);
                    System.out.println("Edição removida com sucesso.");
                    break;
                case 3:
                    // Definir edição como ativa
                    System.out.println("Digite o nome da edição a ser definida como ativa:");
                    String editionToSetActive = scanner.nextLine();
                    Edition editionToActive = cblManager.getEdition(editionToSetActive);
                    if (editionToActive != null) {
                        editionToActive.setStatus(Status.ACTIVE);
                        System.out.println("Edição definida como ativa com sucesso.");
                    } else {
                        System.out.println("Edição não encontrada.");
                    }
                    break;
                case 4:
                    // Definir edição como inativa
                    System.out.println("Digite o nome da edição a ser definida como inativa:");
                    String editionToSetInactive = scanner.nextLine();
                    Edition editionToInactive = cblManager.getEdition(editionToSetInactive);
                    if (editionToInactive != null) {
                        editionToInactive.setStatus(Status.INACTIVE);
                        System.out.println("Edição definida como inativa com sucesso.");
                    } else {
                        System.out.println("Edição não encontrada.");
                    }
                    break;
                case 5:
                    // Definir edição como fechada
                    System.out.println("Digite o nome da edição a ser definida como fechada:");
                    String editionToSetClosed = scanner.nextLine();
                    Edition editionToClose = cblManager.getEdition(editionToSetClosed);
                    if (editionToClose != null) {
                        editionToClose.setStatus(Status.CLOSED);
                        System.out.println("Edição definida como fechada com sucesso.");
                    } else {
                        System.out.println("Edição não encontrada.");
                    }
                    break;
                case 6:
                    // Definir edição como cancelada
                    System.out.println("Digite o nome da edição a ser definida como cancelada:");
                    String editionToSetCanceled = scanner.nextLine();
                    Edition editionToCancel = cblManager.getEdition(editionToSetCanceled);
                    if (editionToCancel != null) {
                        editionToCancel.setStatus(Status.CANCELED);
                        System.out.println("Edição definida como cancelada com sucesso.");
                    } else {
                        System.out.println("Edição não encontrada.");
                    }
                    break;
                case 7:
                    System.out.println("Edições:");
                    for (Edition ed : cblManager.getEditions()) {
                        System.out.println(ed.getName());
                    }
                    break;
                case 8:
                    // Obter edição
                    System.out.println("Digite o nome da edição que deseja obter:");
                    String editionToGet = scanner.nextLine();
                    Edition editionGot = cblManager.getEdition(editionToGet);
                    if (editionGot != null) {
                        System.out.println("Nome da edição: " + editionGot.getName());
                    } else {
                        System.out.println("Edição não encontrada.");
                    }
                    break;
                case 9:
                    // Obter progresso da edição
                    System.out.println("Digite o nome da edição para obter o progresso:");
                    String editionForProgress = scanner.nextLine();
                    Edition editionToGetProgress = cblManager.getEdition(editionForProgress);
                    if (editionToGetProgress != null) {
                        System.out.println(cblManager.getEditionProgress(editionToGetProgress));
                    } else {
                        System.out.println("Edição não encontrada.");
                    }
                    break;
                case 10:
                    // Obter edição ativa
                    Edition activeEdition = cblManager.getActiveEdition();
                    if (activeEdition != null) {
                        System.out.println("Edição ativa: " + activeEdition.getName());
                    } else {
                        System.out.println("Nenhuma edição ativa no momento.");
                    }
                    break;
                case 11:
                    // Obter edições inativas
                    System.out.println("Edições inativas:");
                    for (Edition ed : cblManager.getEditions()) {
                        if (ed.getStatus() == Status.INACTIVE) {
                            System.out.println(ed.getName());
                        }
                    }
                    break;
                case 12:
                    // Obter edições fechadas
                    System.out.println("Edições fechadas:");
                    for (Edition ed : cblManager.getEditions()) {
                        if (ed.getStatus() == Status.CLOSED) {
                            System.out.println(ed.getName());
                        }
                    }
                    break;
                case 13:
                    // Obter edições canceladas
                    System.out.println("Edições canceladas:");
                    for (Edition ed : cblManager.getEditions()) {
                        if (ed.getStatus() == Status.CANCELED) {
                            System.out.println(ed.getName());
                        }
                    }
                    break;
                case 14:
                    // Obter edições inacabadas
                    System.out.println("Edições inacabadas:");
                    for (Edition ed : cblManager.getEditions()) {
                        if (ed.getStatus() != Status.CLOSED && ed.getStatus() != Status.CANCELED) {
                            System.out.println(ed.getName());
                        }
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
