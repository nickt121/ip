import java.util.ArrayList;
import java.util.Scanner;

public class ProcrastinAid {
    public static Storage storageFile = new Storage("./tasks.txt");
    public static ArrayList<Task> taskList = storageFile.loadFromFile();

    public static void main(String[] args) {
        hello();
        Scanner userInput = new Scanner(System.in);
        boolean shouldExit = false;

        while (!shouldExit) {
            String inp = getInput(userInput);
            String command = inp.split(" ", 2)[0];
            try {
                switch (command) {
                case "bye":
                    shouldExit = true;
                    bye();
                    break;
                case "list":
                    printTasks();
                    break;
                case "mark":
                    String[] splitString = parseInput(inp);
                    markTaskAsDone(splitString[1], true);
                    break;
                case "unmark":
                    String[] splitString2 = parseInput(inp);
                    markTaskAsDone(splitString2[1], false);
                    break;
                case "todo":
                    String[] parsedInput = parseInput(inp);
                    addTask(parsedInput[1], TaskType.TODO, storageFile);
                    break;
                case "deadline":
                    addTask(inp.split(" ", 2)[1], TaskType.DEADLINE, storageFile);
                    break;
                case "event":
                    addTask(inp.split(" ", 2)[1], TaskType.EVENT, storageFile);
                    break;
                case "delete":
                    deleteTask(inp.split(" ", 2)[1]);
                    break;
                default:
                    System.out.println("Oops I don't know what to do with " + inp);
                    break;
                }
            } catch (ProcrastinAidException e) {
                System.out.println(e.getMessage());
            }
        }
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

    public static void addTask(String userInp, TaskType type, Storage storage) throws ProcrastinAidException {
        System.out.println("Got it. I've added this task:");
        Task newTask = switch (type) {
            case TODO -> addTodo(userInp);
            case DEADLINE -> addDeadline(userInp);
            case EVENT -> addEvent(userInp);
            default -> null;
        };
        storage.saveToFile(taskList);
        System.out.println(newTask.getIcon() + newTask.getStatusIcon() + " " + newTask);
        System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
    }

    public static Task addTodo(String userInp) {
        Task newTask = new ToDo(userInp, false);
        taskList.add(newTask);
        return newTask;
    }

    public static Task addEvent(String userInp) throws ProcrastinAidException {
        String[] dates = userInp.split(" /from ", 2)[1].split(" /to ", 2);
        Task newTask = new Event(userInp.split(" /from ", 2)[0], false, dates[0], dates[1]);
        taskList.add(newTask);
        return newTask;
    }

    public static Task addDeadline(String userInp) throws ProcrastinAidException {
        String[] args = userInp.split(" /by ", 2);
        Task newTask = new Deadline(args[0], false, args[1]);
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

    public static void markTaskAsDone(String taskNumber, boolean done) {
        int i = Integer.parseInt(taskNumber) - 1;
        try {
            Task tempTask = taskList.get(i);
            tempTask.setStatus(done);
            System.out.println(tempTask.getIcon() + tempTask.getStatusIcon() + " " + tempTask);
            if (done) {
                System.out.println("Nice! I've marked this task as done:");
            } else {
                System.out.println("OK, I've marked this task as not done yet:");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry, that task is not in the list");
        }
    }

    public static void deleteTask(String taskNumber) {
        int i = Integer.parseInt(taskNumber) - 1;
        try {
            Task tempTask = taskList.get(i);
            taskList.remove(i);
            System.out.println("Noted. I've removed this task:");
            System.out.println(tempTask.getIcon() + tempTask.getStatusIcon() + " " + tempTask);
            System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry, that task is not in the list");
        }
        storageFile.saveToFile(taskList);
    }

    public static String[] parseInput(String input) throws ProcrastinAidException {
        String[] inputs = input.split(" ", 2);
        if (inputs.length == 2) {
            return inputs;
        } else {
            throw new ProcrastinAidException("Not enough arguments for this command");
        }
    }
}
