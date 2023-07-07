package ma02_resources;

import ma02_resources.project.Edition;
import ma02_resources.project.Project;
import ma02_resources.project.Task;

import java.io.FileWriter;
import java.io.IOException;

public class CSVExporter {
    public static void exportEditionData(Edition edition, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write("Edition Name,Project Name,Task Title,Task Description,Task Start,Task End\n");

            for (Project project : edition.getProjects()) {
                for (Task task : project.getTasks()) {
                    fileWriter.write(String.format("%s,%s,%s,%s,%s,%s\n",
                            edition.getName(),
                            project.getName(),
                            task.getTitle(),
                            task.getDescription(),
                            task.getStart(),
                            task.getEnd()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
