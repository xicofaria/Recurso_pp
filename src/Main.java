package ma02_resources;

import ma02_resources.participants.*;
import ma02_resources.project.*;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Crie uma instância da classe CBLManager
        CBLManager cblManager = new CBLManager();

        // Crie uma instância da classe ProjectTemplate com o caminho do arquivo JSON
        ProjectTemplate projectTemplate = new ProjectTemplate("path/to/your/json/file");

        // Leia o arquivo JSON e crie uma lista de tarefas
        List<Task> tasks = projectTemplate.createTasks();

        // Crie uma nova edição e adicione-a ao CBLManager
        Edition edition = new EditionImpl("Edition Name", LocalDate.now(), "Project Template", Status.ACTIVE);
        cblManager.addEdition(edition);

        // Adicione projetos, tarefas, submissões e participantes usando os métodos da classe CBLManager
        // Exemplo: adicionar um projeto à edição ativa
        cblManager.addProjectToEdition(edition.getName(), "Project Name", "Project Description");

        // Gere relatórios e listagens usando os métodos da classe CBLManager
        // Exemplo: gerar um relatório
        cblManager.generateReport();
    }
}
