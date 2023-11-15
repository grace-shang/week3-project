package data_access;


import api.Todo;
import entity.TaskFactory;
import entity.TaskI;
import use_case.complete_task.CompleteTaskDataAccessInterface;
import use_case.create_task.CreateTaskDataAccessInterface;
import use_case.delete_task.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileTaskDataAccessObject implements CreateTaskDataAccessInterface, CompleteTaskDataAccessInterface{
    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, TaskI> tasks = new HashMap<>();

    private TaskFactory taskFactory;
    private Todo todo;

    public FileTaskDataAccessObject(Todo todo, String file, TaskFactory taskFactory) throws IOException{
        this.taskFactory = taskFactory;

        csvFile = new File(file);
        headers.put("task_name", 0);
        headers.put("completion", 1);
        this.todo = todo;

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                assert header.equals("task_name,completion");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String nameTask = String.valueOf(col[headers.get("task_name")]);
                    String completionTask = String.valueOf(col[headers.get("completion")]);
                    TaskI task = taskFactory.create(nameTask, completionTask);
                    accounts.put(username, user);
                }
            }
        }


    }

    /**
     * @param task the data to save
     */
    @Override
    public void save(TaskI task) {
        tasks.put(task.getName(), task);
        this.save();
    }

    public void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (TaskI task : tasks.values()) {
                String line = String.format("%s,%s,%s",
                        task.getName(), task.getComplete());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addTask(TaskI taskI){
//        String projectName = taskI.getProjectName();
        String taskName = taskI.getName();
        todo.addTask("projectName", taskName);
    }


    @Override
    public void complete(TaskI task) throws IOException {

    }
}
