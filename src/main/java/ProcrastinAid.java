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
            } else if (inp.startsWith("todo")) {
                addTask(inp.split(" ", 2)[1], 1);
            } else if (inp.startsWith("deadline")) {
                addTask(inp.split(" ", 2)[1], 2);
            } else if (inp.startsWith("event")) {
                addTask(inp.split(" ", 2)[1], 3);
            } else {
                ;
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

    public static void addTask(String userInp, int type){
        System.out.println("Got it. I've added this task:");
        Task newTask = switch (type) {
            case 1 -> addTodo(userInp);
            case 2 -> addDeadline(userInp);
            case 3 -> addEvent(userInp);
            default -> null;
        };
        System.out.println(newTask.getIcon() + newTask.getStatusIcon() + " " + newTask);
        System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
    }

    public static Task addTodo(String userInp){
        Task newTask = new ToDo(userInp);
        System.out.println(newTask.getIcon() + newTask.getStatusIcon() + " " + newTask);
        taskList.add(newTask);
        return newTask;
    }

    public static Task addEvent(String userInp){
        String[] dates = userInp.split(" /from ", 2)[1].split(" /to ", 2);
        Task newTask = new Event(userInp.split(" /from ", 2)[0], dates[0], dates[1]);
        taskList.add(newTask);
        return newTask;
    }

    public static Task addDeadline(String userInp){
        String[] args = userInp.split(" /by ", 2);
        Task newTask = new Deadline(args[0], args[1]);
        taskList.add(newTask);
        return newTask;
    }

    public static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task tempTask = taskList.get(i);
            System.out.println(String.valueOf(i + 1) + "." + tempTask.getIcon() + tempTask.getStatusIcon() + " " + tempTask);
        }
    }

    public static void markTaskAsDone(String taskNumber) {
        System.out.println("Nice! I've marked this task as done:");
        int i = Integer.parseInt(taskNumber) - 1;
        Task tempTask = taskList.get(i);
        tempTask.setStatus(true);
        System.out.println(tempTask.getIcon() + tempTask.getStatusIcon() + " " + tempTask);
    }

    public static void unmarkTaskAsDone(String taskNumber) {
        System.out.println("OK, I've marked this task as not done yet:");
        int i = Integer.parseInt(taskNumber) - 1;
        Task tempTask = taskList.get(i);
        tempTask.setStatus(false);
        System.out.println(tempTask.getIcon() + tempTask.getStatusIcon() + " " + tempTask);
    }
}
