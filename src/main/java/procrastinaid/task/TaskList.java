package procrastinaid.task;

import java.util.ArrayList;

import procrastinaid.exception.ProcrastinAidException;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task deleteTask(int index) {
        Task temp = this.tasks.get(index);
        this.tasks.remove(index);
        return temp;
    }

    public Task markTaskAsDone(int index, boolean isDone) {
        Task temp = this.tasks.get(index);
        temp.setStatus(isDone);
        return temp;
    }

    public void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            Task tempTask = this.tasks.get(i);
            System.out.println(String.valueOf(i + 1) + "." + tempTask.getIcon() + tempTask.getStatusIcon() + " " + tempTask);
        }
    }

    public int getSize() {
        return this.tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public Task addTodo(String userInp) {
        Task newTask = new ToDo(userInp, false);
        this.addTask(newTask);
        return newTask;
    }

    public Task addEvent(String userInp) throws ProcrastinAidException {
        String[] dates = userInp.split(" /from ", 2)[1].split(" /to ", 2);
        Task newTask = new Event(userInp.split(" /from ", 2)[0], false, dates[0], dates[1]);
        this.addTask(newTask);
        return newTask;
    }

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
