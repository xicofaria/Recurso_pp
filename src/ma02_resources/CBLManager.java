package ma02_resources;

import ma02_resources.participants.*;
import ma02_resources.participants.StudentEvaluation;
import ma02_resources.project.*;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CBLManager {
    private List<Edition> editions;
    private Edition activeEdition;

    public CBLManager() {
        this.editions = new ArrayList<>();
    }

    public void addEdition(Edition edition) {
        editions.add(edition);
    }

    public void removeEdition(String editionName) {
        editions.removeIf(edition -> edition.getName().equals(editionName));
    }

    public Edition getEdition(String editionName) {
        return editions.stream().filter(edition -> edition.getName().equals(editionName)).findFirst().orElse(null);
    }
    public List<Edition> getEditions() {
        return editions;
    }

    public void setActiveEdition(Edition edition) {
        if (activeEdition != null) {
            activeEdition.setStatus(Status.INACTIVE);
        }
        edition.setStatus(Status.ACTIVE);
        activeEdition = edition;
    }

    public Edition getActiveEdition() {
        return activeEdition;
    }

    public List<Edition> getEditionsWithMissingSubmissions() {
        List<Edition> editionsWithMissingSubmissions = new ArrayList<>();
        for (Edition edition : editions) {
            for (Project project : edition.getProjects()) {
                for (Task task : project.getTasks()) {
                    if (task.getNumberOfSubmissions() < task.getMaximumNumberOfSubmissions()) {
                        editionsWithMissingSubmissions.add(edition);
                        break;
                    }
                }
            }
        }
        return editionsWithMissingSubmissions;
    }


    public void addProjectToEdition(String editionName, String projectName, String projectDescription) {
        Edition edition = getEdition(editionName);
        if (edition != null) {
            try {
                edition.addProject(projectName, projectDescription, new String[0]); // Adicione tags, se necessário
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeProjectFromEdition(String editionName, String projectName) {
        Edition edition = getEdition(editionName);
        if (edition != null) {
            edition.removeProject(projectName);
        }
    }

    public List<Project> getProjectsWithMissingSubmissions(Edition edition) {
        List<Project> projectsWithMissingSubmissions = new ArrayList<>();
        for (Project project : edition.getProjects()) {
            for (Task task : project.getTasks()) {
                if (task.getNumberOfSubmissions() < task.getMaximumNumberOfSubmissions()) {
                    projectsWithMissingSubmissions.add(project);
                    break;
                }
            }
        }
        return projectsWithMissingSubmissions;
    }

    public List<Project> getProjectsWithMissingSubmissionsFromActiveEdition() {
        if (activeEdition != null) {
            return getProjectsWithMissingSubmissions(activeEdition);
        } else {
            return new ArrayList<>();
        }
    }// retorna os projetos com submissões em falta da edição ativa


    public int getNumberOfProjects(Edition edition) {
        return edition.getNumberOfProjects();
    }


    // Implemente os métodos para gerar relatórios e listagens mencionados no enunciado
    public void generateReport() {
        // Implemente a lógica para gerar relatórios conforme necessário
    }

    public void assignSelfEvaluation(Student student, double grade) {
        StudentEvaluation evaluation = new StudentEvaluation(student);
        evaluation.setSelfEvaluation(grade);
        student.addEvaluation(evaluation);
    }

    public void assignPeerEvaluation(Student student, double grade) {
        StudentEvaluation evaluation = new StudentEvaluation(student);
        evaluation.setPeerEvaluation(grade);
        student.addEvaluation(evaluation);
    }

    public double generateFinalEvaluation(Student student) {
        return student.getFinalEvaluation();
    }

    public void exportDataCSV() {
        // Exemplo: Exportar os dados da edição ativa para um arquivo CSV
        if (activeEdition != null) {
            CSVExporter.exportEditionData(activeEdition, "active_edition_data.csv");
        }
    }

    public void exportDataJSON() {
        // Exemplo: Exportar os dados da edição ativa para um arquivo JSON
        if (activeEdition != null) {
            JSONExporter.exportEditionData(activeEdition, "active_edition_data.json");
        }
    }

    public int getNumberOfEditions() {
        return editions.size();
    }



    public String getEditionProgress(Edition edition) {
        StringBuilder progress = new StringBuilder();
        progress.append("Edition: ").append(edition.getName()).append("\n");

        for (Project project : edition.getProjects()) {
            progress.append("Project: ").append(project.getName()).append("\n");
            progress.append(getProjectProgress(project));
        }

        return progress.toString();
    }

    public String getProjectProgress(Project project) {
        StringBuilder progress = new StringBuilder();

        for (Task task : project.getTasks()) {
            progress.append("Task: ").append(task.getTitle()).append("\n");
            progress.append("Submissions: ").append(task.getNumberOfSubmissions()).append(" / ").append(task.getMaximumNumberOfSubmissions()).append("\n");
        }

        return progress.toString();
    }

    public Participant getParticipant(String email) {
        for (Edition edition : editions) {
            for (Project project : edition.getProjects()) {
                Participant participant = project.getParticipant(email);
                if (participant != null) {
                    return participant;
                }
            }
        }
        return null;
    }

    /**
     * Adiciona uma submissão a um projeto específico. Verifica se o estudante que está fazendo a submissão é um participante do projeto.
     *
     * @param project        O projeto ao qual a submissão será adicionada.
     * @param student        O estudante que está fazendo a submissão.
     * @param submissionText O texto da submissão.
     */
    public void addSubmissionToProject(Project project, Student student, String submissionText) {
        // Verifique se o estudante é um participante do projeto
        if (project.getParticipant(student.getEmail()) != null) {
            // Crie uma nova submissão
            Submission submission = new SubmissionImpl(LocalDateTime.now(), student, submissionText);
            // Encontre a tarefa para a qual a submissão está sendo feita
            for (Task task : project.getTasks()) {
                // Adicione a submissão à tarefa
                task.addSubmission(submission);
            }
        } else {
            throw new IllegalArgumentException("Student is not a participant of the project");
        }
    }

    /**
     * Adiciona uma submissão a um projeto na edição ativa. Verifica se o estudante que está fazendo a submissão é um participante do projeto.
     *
     * @param projectName O nome do projeto ao qual a submissão será adicionada.
     * @param taskTitle   O título da tarefa à qual a submissão será adicionada.
     * @param submission  A submissão que será adicionada.
     */
    public void addSubmissionToActiveEdition(String projectName, String taskTitle, Submission submission) {
        // Obtenha o projeto pelo nome
        Project project = activeEdition.getProject(projectName);
        if (project != null) {
            // Obtenha a tarefa pelo título
            Task task = project.getTask(taskTitle);
            if (task != null) {
                // Verifique se o estudante é um participante do projeto
                if (project.getParticipant(submission.getStudent().getEmail()) != null) {
                    // Adicione a submissão à tarefa
                    task.addSubmission(submission);
                } else {
                    throw new IllegalArgumentException("Student is not a participant of the project");
                }
            }
        }
    }


    // Exemplo de listagem 1: Lista de estudantes participantes em uma edição
// Este método retorna uma lista de estudantes que estão participando de uma edição específica.
// Ele percorre todos os projetos na edição e, para cada projeto, percorre todos os participantes.
// Se o participante for um estudante, ele é adicionado à lista de estudantes.
    public Participant[] getStudentsInEdition(Edition edition) {
        List<Participant> students = new ArrayList<>();
        for (Project project : edition.getProjects()) {
            for (Participant participant : project.getParticipants()) {
                if (participant instanceof Student) {
                    students.add(participant);
                }
            }
        }
        return students.toArray(new Participant[0]);
    }


    // Exemplo de listagem 2: Projetos com mais de N submissões em uma edição
// Este método retorna uma lista de projetos que têm mais de 'n' submissões em uma edição específica.
// Ele percorre todos os projetos na edição e, para cada projeto, soma o número de submissões de todas as tarefas.
// Se o total de submissões for maior que 'n', o projeto é adicionado à lista de projetos.
    public Project[] getProjectsWithMoreThanNSubmissions(Edition edition, int n) {
        List<Project> projects = new ArrayList<>();
        for (Project project : edition.getProjects()) {
            int totalSubmissions = 0;
            for (Task task : project.getTasks()) {
                totalSubmissions += task.getNumberOfSubmissions();
            }
            if (totalSubmissions > n) {
                projects.add(project);
            }
        }
        return projects.toArray(new Project[0]);
    }


    // Exemplo de listagem 3: Projetos concluídos em uma edição
// Este método retorna uma lista de projetos que estão concluídos em uma edição específica.
// Ele percorre todos os projetos na edição e, para cada projeto, verifica se todas as tarefas foram concluídas.
// Se todas as tarefas de um projeto foram concluídas, o projeto é considerado concluído e é adicionado à lista de projetos concluídos.
    public Project[] getCompletedProjectsInEdition(Edition edition) {
        List<Project> completedProjects = new ArrayList<>();
        for (Project project : edition.getProjects()) {
            boolean allTasksCompleted = true;
            for (Task task : project.getTasks()) {
                if (task.getNumberOfSubmissions() < task.getMaximumNumberOfSubmissions()) {
                    allTasksCompleted = false;
                    break;
                }
            }
            if (allTasksCompleted) {
                completedProjects.add(project);
            }
        }
        return completedProjects.toArray(new Project[0]);
    }
}

