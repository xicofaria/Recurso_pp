package ma02_resources;

import ma02_resources.participants.*;
import ma02_resources.project.*;
import ma02_resources.project.exceptions.IllegalNumberOfTasks;
import ma02_resources.project.exceptions.TaskAlreadyInProject;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;


public class InitialTestingValues {
    // Create Contacts
    Contact contact1 = new ContactImpl("Street 1", "City", "State", "12345", "Country", "123456789");
    Contact contact2 = new ContactImpl("Street of New York", "New York City", "New York", "10001", "USA", "555-1234");

    // Create Institutions
    Instituition institution1 = new InstituitionImpl("University of Example", "university@example.com", InstituitionType.UNIVERSITY, contact1, "https://www.example.edu", "A university for examples.");
    Instituition institution2 = new InstituitionImpl("ABC Company", "company@example.com", InstituitionType.COMPANY, contact2, "https://www.abccompany.com", "A company that specializes in examples.");

    // Create Students
    Student student1 = new StudentImpl("Ana Santos", "ana.santos@example.com", contact1, institution1, 12345);
    Student student2 = new StudentImpl("Pedro Gomes", "pedro.gomes@example.com", contact2, institution2, 67890);

    // Create Edition
    Edition edition1 = new EditionImpl("Edição 1", LocalDate.of(2022, 1, 1), "project_template.json", Status.ACTIVE);

    // Create Projects
    Project project1 = new ProjectImpl("Projeto A", "Descrição do Projeto A");
    Project project2 = new ProjectImpl("Projeto B", "Descrição do Projeto B");

    // Create Tasks
    Task task1 = new TaskImpl(LocalDate.now(), 7, "Tarefa 1", "Descrição da Tarefa 1");
    Task task2 = new TaskImpl(LocalDate.now().plusDays(7), 14, "Tarefa 2", "Descrição da Tarefa 2");

    public InitialTestingValues() throws TaskAlreadyInProject, IllegalNumberOfTasks {
        // Add tasks to projects
        project1.addTask(task1);
        project2.addTask(task2);

// Add projects to edition
        try {
            edition1.addProject("Projeto A", "Descrição do Projeto A", new String[]{"tag1", "tag2"});
            edition1.addProject("Projeto B", "Descrição do Projeto B", new String[]{"tag3", "tag4"});
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    public Edition getEdition() {
        return edition1;
    }
    public Edition getEdition1() {
        return edition1;
    }
    public Participant getStudent1() {
        return student1;
    }

    public Participant getStudent2() {
        return student2;
    }

}