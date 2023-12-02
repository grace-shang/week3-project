package create_task;

import app.Main;
import data_access.FileTaskDataAccessObject;
import entity.AllTaskFactory;
import entity.Task;
import entity.TaskFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import api.Todo;
import api.ToDoList;
import create_task.TestUtils;
import org.junit.Test;
import view.TaskView;

import static org.junit.Assert.*;

public class CreateTaskViewTest {
    static String message = "";

    /**
     * ensures there are at least 2 tasks in the CSV file for testing purposes
     */
    public void addTwoTasks() {
        TaskFactory tf = new AllTaskFactory();
        FileTaskDataAccessObject ftdao;
        Todo todo = new ToDoList();

        try {
            ftdao = new FileTaskDataAccessObject("./tasks.csv", tf, todo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ftdao.save(tf.create("task1", false));
        ftdao.save(tf.create("task2", false));
    }


    public static JButton getCreateTaskButton(Container container) {
        return getChildNamed(container, "Create Task Button", JButton.class);
    }

    public static JTextField getCreateTaskInputField(Container container) {
        return getChildNamed(container, "Text Box", JTextField.class);
    }

    private static <T extends Component> T getChildNamed(Container container, String name, Class<T> clazz) {
        Component[] components = container.getComponents();

        for (Component component : components) {
            if (component instanceof Container) {
                T result = getChildNamed((Container) component, name, clazz);
                if (result != null) {
                    return result;
                }
            }

            if (component.getName() != null && component.getName().equals(name) && clazz.isInstance(component)) {
                return clazz.cast(component);
            }
        }

        return null;
    }

    public static JButton getCreateTaskButton() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }
        assertNotNull(app);
        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();
        JPanel jp = (JPanel) cp;
        JPanel taskViewPanel = (JPanel) jp.getComponent(2);
        TaskView taskView = (TaskView) taskViewPanel.getComponent(0);
        JPanel buttonsPanel = (JPanel) taskView.getComponent(2);
        return (JButton) buttonsPanel.getComponent(0);
    }


    @org.junit.Test
    public void testCompleteButtonPresent(){
        Main.main(null);
        JButton button = getCreateTaskButton();
        assert(button.getText().equals("Create Task"));
    }


    @org.junit.Test
    public void testCreateSavesTaskIntoFile() throws IOException {
        addTwoTasks();
        Main.main(null);
        ArrayList<String> names = getNames();
        System.out.println("Names In File: " + names);
        assertTrue("Task1 is not found in the file", names.contains("task1"));
        assertTrue("Task2 is not found in the file", names.contains("task2"));

    }

    @org.junit.Test
    public void testCreateDisplaysNewTask(){

    }


    @org.junit.Test
    public void testLinesInCSVAfterCreate(){addTwoTasks();
        addTwoTasks();
        Main.main(null);

        try {
            int lines = countLines();
            System.out.println("lines in csv file = " + lines);
            assert(lines == 3);
        } catch (IOException e){
            throw new RuntimeException(e);
        }}


    private static ArrayList<String> getNames() throws IOException {
        ArrayList<String> listOfNames = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("tasks.csv"))) {
            String line;
            // Skip the header line
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 1) {
                    // Trim to remove any leading/trailing whitespaces
                    listOfNames.add(values[0].trim());
                }
            }
            return listOfNames;
        }
    }
    private static int countLines() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("tasks.csv"));
        int lineCount = 0;
        while (reader.readLine() != null) {
            lineCount++;
        }
        return lineCount;
    }

}
