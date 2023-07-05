package ma02_resources.project;

import ma02_resources.participants.Participant;
import ma02_resources.participants.Student;
import ma02_resources.participants.Partner;
import ma02_resources.participants.Facilitator;
import ma02_resources.project.exceptions.*;

import java.util.*;

public class ProjectImpl implements Project {
    private String name;
    private String description;
    private List<Participant> participants;
    private List<Task> tasks;
    private Set<String> tags;


    public ProjectImpl(String name, String description) {
        this.name = name;
        this.description = description;
        this.participants = new ArrayList<>();
        this.tasks = new ArrayList<>();
        this.tags = new HashSet<>();

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
        return (int) participants.stream().filter(p -> p instanceof Student).count();
    }

    @Override
    public int getNumberOfPartners() {
        return (int) participants.stream().filter(p -> p instanceof Partner).count();
    }

    @Override
    public int getNumberOfFacilitators() {
        return (int) participants.stream().filter(p -> p instanceof Facilitator).count();
    }

    @Override
    public int getNumberOfTasks() {
        return tasks.size();
    }

    @Override
    public int getMaximumNumberOfTasks() {
        return tasks.size();
    }

    @Override
    public long getMaximumNumberOfParticipants() {
        return participants.size();
    }

    @Override
    public int getMaximumNumberOfStudents() {
        return getNumberOfStudents();
    }

    @Override
    public int getMaximumNumberOfPartners() {
        return getNumberOfPartners();
    }

    @Override
    public int getMaximumNumberOfFacilitators() {
        return getNumberOfFacilitators();
    }

    @Override
    public void addParticipant(Participant participant) throws ParticipantAlreadyInProject {
        if (participants.contains(participant)) {
            throw new ParticipantAlreadyInProject("Participant already in project");
        }
        participants.add(participant);
    }



    @Override
    public Participant removeParticipant(String email) {
        Participant participantToRemove = getParticipant(email);
        if (participantToRemove != null) {
            participants.remove(participantToRemove);
        }
        return participantToRemove;
    }

    @Override
    public Participant getParticipant(String email) {
        return participants.stream().filter(p -> p.getEmail().equals(email)).findFirst().orElse(null);
    }

    @Override
    public String[] getTags() {
        return tags.toArray(new String[0]);
    }

    @Override
    public boolean hasTag(String tag) {
        return tags.contains(tag);
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void removeTag(String tag) {
        tags.remove(tag);
    }

    @Override
    public void addTask(Task task) throws IllegalNumberOfTasks, TaskAlreadyInProject {
        if (tasks.contains(task)) {
            throw new TaskAlreadyInProject("Task already in project");
        }
        tasks.add(task);
    }

    @Override
    public Task getTask(String title) {
        return tasks.stream().filter(t -> t.getTitle().equals(title)).findFirst().orElse(null);
    }

    @Override
    public Task[] getTasks() {
        return tasks.toArray(new Task[0]);
    }

    @Override
    public boolean isCompleted() {
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
                tasks.equals(project.tasks) &&
                tags.equals(project.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, participants, tasks, tags);
    }
}
