package ma02_resources.project;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EditionImpl implements Edition {
    private String name;
    private LocalDate start;
    private String projectTemplate;
    private Status status;
    private List<Project> projects;

    public EditionImpl(String name, LocalDate start, String projectTemplate, Status status) {
        this.name = name;
        this.start = start;
        this.projectTemplate = projectTemplate;
        this.status = status;
        this.projects = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public LocalDate getStart() {
        return start;
    }

    @Override
    public String getProjectTemplate() {
        return projectTemplate;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public void addProject(String name, String description, String[] tags) throws IOException, ParseException {
        Project project = new ProjectImpl(name, description);
        this.projects.add(project);
    }

    @Override
    public void removeProject(String name) {
        projects.removeIf(project -> project.getName().equals(name));
    }

    @Override
    public Project getProject(String name) {
        for (Project project : projects) {
            if (project.getName().equals(name)) {
                return project;
            }
        }
        return null;
    }

    @Override
    public Project[] getProjects() {
        return projects.toArray(new Project[0]);
    }

    @Override
    public Project[] getProjectsByTag(String tag) {
        // This method will not work as expected because we don't have tags in Project
        return new Project[0];
    }

    @Override
    public Project[] getProjectsOf(String name) {
        // TODO: Implement this method according to your requirements
        return new Project[0];
    }

    @Override
    public int getNumberOfProjects() {
        return projects.size();
    }

    @Override
    public LocalDate getEnd() {
        // TODO: Implement this method according to your requirements
        return null;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EditionImpl edition = (EditionImpl) obj;
        return name.equals(edition.name) &&
                start.equals(edition.start) &&
                projectTemplate.equals(edition.projectTemplate) &&
                status == edition.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, start, projectTemplate, status);
    }
}
