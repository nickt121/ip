package procrastinaid.task;

import java.util.ArrayList;

import procrastinaid.exception.ProcrastinAidException;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index The index of the task to be deleted.
     * @return The task that was deleted.
     */
    public Task deleteTask(int index) {
        Task temp = this.tasks.get(index);
        this.tasks.remove(index);
        return temp;
    }

    /**
     * Marks a task as done or undone.
     *
     * @param index The index of the task to be marked.
     * @param isDone The status of the task.
     * @return The task that was marked.
     */
    public Task markTaskAsDone(int index, boolean isDone) {
        Task tempTask = this.tasks.get(index);
        tempTask.setStatus(isDone);
        return tempTask;
    }

    /**
     * Prints all the tasks in the list.
     */
    public void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            Task tempTask = this.tasks.get(i);
            System.out.println(String.valueOf(i + 1) + "." + tempTask.getIcon() + tempTask.getStatusIcon() + " " + tempTask);
        }
    }

    /**
     * Retrieves the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Retrieves all the tasks in the list.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Retrieves a task from the list.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds a new ToDo task to the list.
     *
     * @param userInp The user input to be parsed.
     * @return The task that was added.
     * @throws ProcrastinAidException If the user input is invalid.
     */
    public Task addTodo(String userInp) {
        Task newTask = new ToDo(userInp, false);
        this.addTask(newTask);
        return newTask;
    }

    /**
     * Adds a new Event task to the list.
     *
     * @param userInp The user input to be parsed.
     * @return The task that was added.
     * @throws ProcrastinAidException If the user input is invalid.
     */
    public Task addEvent(String userInp) throws ProcrastinAidException {
        String[] dates = userInp.split(" /from ", 2)[1].split(" /to ", 2);
        Task newTask = new Event(userInp.split(" /from ", 2)[0], false, dates[0], dates[1]);
        this.addTask(newTask);
        return newTask;
    }

    /**
     * Adds a new Deadline task to the list.
     *
     * @param userInp The user input to be parsed.
     * @return The task that was added.
     * @throws ProcrastinAidException If the user input is invalid.
     */
    public Task addDeadline(String userInp) throws ProcrastinAidException {
        String[] args = userInp.split(" /by ", 2);
        Task newTask = new Deadline(args[0], false, args[1]);
        this.addTask(newTask);
        return newTask;
    }

    public void findTasks(String keyword) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            Task tempTask = this.tasks.get(i);
            if (tempTask.toString().contains(keyword)) {
                System.out.println(String.valueOf(i + 1) + "." + tempTask.getIcon() + tempTask.getStatusIcon() + " " + tempTask);
            }
        }
    }
}
