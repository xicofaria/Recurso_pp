package ma02_resources;

import ma02_resources.project.Edition;
import ma02_resources.project.Project;
import ma02_resources.project.Task;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JSONExporter {
    public static void exportEditionData(Edition edition, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            JSONObject editionData = new JSONObject();

            JSONArray projectsArray = new JSONArray();
            for (Project project : edition.getProjects()) {
                JSONObject projectData = new JSONObject();
                projectData.put("name", project.getName());

                JSONArray tasksArray = new JSONArray();
                for (Task task : project.getTasks()) {
                    JSONObject taskData = new JSONObject();
                    taskData.put("title", task.getTitle());
                    taskData.put("description", task.getDescription());
                    taskData.put("start", task.getStart().toString());
                    taskData.put("end", task.getEnd().toString());
                    tasksArray.add(taskData);
                }

                projectData.put("tasks", tasksArray);
                projectsArray.add(projectData);
            }

            editionData.put("edition", edition.getName());
            editionData.put("projects", projectsArray);

            fileWriter.write(editionData.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
