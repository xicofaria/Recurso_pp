package ma02_resources.project;

import ma02_resources.participants.Participant;
import ma02_resources.project.exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProjectImpl implements Project {
    private String name;
    private String description;
    private List<Participant> participants;
    private List<Task> tasks;

    public ProjectImpl(String name, String description) {
        this.name = name;
        this.description = description;
        this.participants = new ArrayList<>();
        this.tasks = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getNumberOfParticipants() {
        return participants.size();
    }

    @Override
    public int getNumberOfStudents() {
        // TODO: Implement this method according to your requirements
        return 0;
    }

    @Override
    public int getNumberOfPartners() {
        // TODO: Implement this method according to your requirements
        return 0;
    }

    @Override
    public int getNumberOfFacilitators() {
        // TODO: Implement this method according to your requirements
        return 0;
    }

    @Override
    public int getNumberOfTasks() {
        return tasks.size();
    }

    @Override
    public int getMaximumNumberOfTasks() {
        // TODO: Implement this method according to your requirements
        return 0;
    }

    @Override
    public long getMaximumNumberOfParticipants() {
        // TODO: Implement this method according to your requirements
        return 0;
    }

    @Override
    public int getMaximumNumberOfStudents() {
        // TODO: Implement this method according to your requirements
        return 0;
    }

    @Override
    public int getMaximumNumberOfPartners() {
        // TODO: Implement this method according to your requirements
        return 0;
    }

    @Override
    public int getMaximumNumberOfFacilitators() {
        // TODO: Implement this method according to your requirements
        return 0;
    }

    @Override
    public void addParticipant(Participant participant) throws IllegalNumberOfParticipantType, ParticipantAlreadyInProject {
        // TODO: Implement this method according to your requirements
    }

    @Override
    public Participant removeParticipant(String email) {
        // TODO: Implement this method according to your requirements
        return null;
    }

    @Override
    public Participant getParticipant(String email) {
        // TODO: Implement this method according to your requirements
        return null;
    }

    @Override
    public String[] getTags() {
        // TODO: Implement this method according to your requirements
        return new String[0];
    }

    @Override
    public boolean hasTag(String tag) {
        // TODO: Implement this method according to your requirements
        return false;
    }

    @Override
    public void addTask(Task task) throws IllegalNumberOfTasks, TaskAlreadyInProject {
        // TODO: Implement this method according to your requirements
    }

    @Override
    public Task getTask(String title) {
        // TODO: Implement this method according to your requirements
        return null;
    }

    @Override
    public Task[] getTasks() {
        // TODO: Implement this method according to your requirements
        return new Task[0];
    }

    @Override
    public boolean isCompleted() {
        // TODO: Implement this method according to your requirements
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProjectImpl project = (ProjectImpl) obj;
        return name.equals(project.name) &&
                description.equals(project.description) &&
                participants.equals(project.participants) &&
                tasks.equals(project.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, participants, tasks);
    }
}
