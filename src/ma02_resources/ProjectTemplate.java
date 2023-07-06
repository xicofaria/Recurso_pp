package ma02_resources;
import ma02_resources.project.Task;
import ma02_resources.project.TaskImpl;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectTemplate {

    private String jsonFilePath;

    public ProjectTemplate(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
    }

    public JSONObject readJson() {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;

        try {
            Object obj = parser.parse(new FileReader(jsonFilePath));
            jsonObject = (JSONObject) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public List<Task> createTasks() {
        List<Task> tasks = new ArrayList<>();
        JSONObject jsonObject = readJson();
        JSONArray tasksArray = (JSONArray) jsonObject.get("tasks");

        for (Object taskObj : tasksArray) {
            JSONObject taskJson = (JSONObject) taskObj;
            String title = (String) taskJson.get("title");
            String description = (String) taskJson.get("description");
            long startAt = (Long) taskJson.get("start_at");
            long duration = (Long) taskJson.get("duration");

            Task task = new TaskImpl(LocalDate.now().plusDays(startAt), (int) duration, title, description);
            tasks.add(task);
        }

        return tasks;
    }
}
