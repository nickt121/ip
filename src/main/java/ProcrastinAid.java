import java.util.ArrayList;
import java.util.Scanner;

public class ProcrastinAid {
    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        hello();
        Scanner userInput = new Scanner(System.in);

        while (true) {
            String inp = getInput(userInput);
            if (inp.equals("bye")) {
                break;
            } else if (inp.equals("list")) {
                printTasks();
            } else if (inp.startsWith("mark")) {
                String[] splitString = inp.split(" ");
                markTaskAsDone(splitString[1]);
            } else if (inp.startsWith("unmark")) {
                String[] splitString = inp.split(" ");
                unmarkTaskAsDone(splitString[1]);
            } else {
                addTask(inp);
            }
        }
        bye();
    }

    public static void hello() {
        System.out.println("Hello! I'm ProcrastinAid");
        System.out.println("What can I do for you?\n");
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public static String getInput(Scanner userInput) {
        System.out.print("> ");
        return userInput.nextLine();
    }

    public static void addTask(String task) {
        System.out.println("Added: " + task);
        Task newTask = new Task(task);
        taskList.add(newTask);
    }

    public static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task tempTask = taskList.get(i);
            System.out.println(String.valueOf(i + 1) + ".[" + tempTask.getStatusIcon() + "] " + tempTask.toString());
        }
    }

    public static void markTaskAsDone(String taskNumber) {
        System.out.println("Nice! I've marked this task as done:");
        int i = Integer.parseInt(taskNumber) - 1;
        Task tempTask = taskList.get(i);
        tempTask.setStatus(true);
        System.out.println("[" + tempTask.getStatusIcon() + "] " + tempTask.toString());
    }

    public static void unmarkTaskAsDone(String taskNumber) {
        System.out.println("OK, I've marked this task as not done yet:");
        int i = Integer.parseInt(taskNumber) - 1;
        Task tempTask = taskList.get(i);
        tempTask.setStatus(false);
        System.out.println("[" + tempTask.getStatusIcon() + "] " + tempTask.toString());
    }
}
