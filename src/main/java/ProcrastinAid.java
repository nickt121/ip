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
            System.out.println(String.valueOf(i) + ".[" + tempTask.getStatusIcon() + "] "  + tempTask.getDescription());
        }
    }
}
