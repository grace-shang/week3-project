package interface_adapter.display_task;

import java.util.ArrayList;

public class DisplayTaskState {

    private ArrayList<String> tasks = new ArrayList<>();

    private ArrayList<ArrayList<Object>> taskInfo = new ArrayList<>();

    /**
     * Constructs the display task state
     * @param copy the state of the display use case
     */
    public DisplayTaskState(DisplayTaskState copy) {
        this.tasks = copy.tasks;
        this.taskInfo = copy.taskInfo;
    }

    /**
     * Constructor for the display task state with no given state
     */
    public DisplayTaskState() {
    }

    /**
     * Getter for the list of task names
     * @return list of task names
     */
    public ArrayList<String> getTasks() {return tasks;}

    /**
     * Setter for the task names
     * @param tasks A list of task names
     */
    public void setTasks(ArrayList<String> tasks) {this.tasks = tasks;}

    /**
     * Getter for the task information
     * @return a list that contains lists of objects with task information
     */
    public ArrayList<ArrayList<Object>> getTaskInfo() {
        return taskInfo;
    }

    /**
     * Setter for the task information
     * @param taskInfo a list that contains lists of objects with task information
     */
    public void setTaskInfo(ArrayList<ArrayList<Object>> taskInfo) {
        this.taskInfo = taskInfo;
    }

}
