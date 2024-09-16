package interface_adapter.create_task;

public class CreateTaskState{
    private String task = "";

    private String taskError = null;

    /**
     * constructs the create task state with no given stat
     */
    public CreateTaskState(){}

    /**
     * @return gets the task in the state and returns it
     */
    public String getTask(){return task;}

    /**
     * @param task a setter for the state than updates the task in the state.
     */
    public void setTask(String task){this.task = task;}

    /**
     * @param taskError setter to update the state if there is an error in the creation of the state.
     */
    public void setTaskError(String taskError) {
        this.taskError = taskError;
    }

    /**
     * @return a getter for the state that returns the task error in the state.
     */
    public String getTaskError(){return taskError;}

    /**
     * @return returns the string version of the state that includes the task
     */
    public String toString() {
        return "CreateTaskState{" +
                "task=" + task + '\'' +
                '}';
    }


}
