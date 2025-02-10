package procrastinaid.ui;

import java.util.Scanner;

import procrastinaid.exception.ProcrastinAidException;
import procrastinaid.task.Storage;
import procrastinaid.task.Task;
import procrastinaid.task.TaskList;

public class ProcrastinAid {
    private static Storage storageFile = new Storage("./tasks.txt");
    private static TaskList tasks = storageFile.loadFromFile();

    public static void main(String[] args) {
        Ui.hello();
        Parser parser = new Parser();
        Scanner userInput = new Scanner(System.in);
        boolean shouldExit = false;

        while (!shouldExit) {
            parser.getUserInput();
            String command = parser.getCommand();
            String arguments = parser.getRawArgs();
            try {
                switch (command) {
                case "bye":
                    shouldExit = true;
                    Ui.bye();
                    break;
                case "list":
                    tasks.printTasks();
                    break;
                case "mark":
                    markTaskAsDone(arguments, true);
                    break;
                case "unmark":
                    markTaskAsDone(arguments, false);
                    break;
                case "todo":
                    addTask(arguments, TaskType.TODO, storageFile);
                    break;
                case "deadline":
                    addTask(arguments, TaskType.DEADLINE, storageFile);
                    break;
                case "event":
                    addTask(arguments, TaskType.EVENT, storageFile);
                    break;
                case "delete":
                    deleteTask(arguments);
                    break;
                case "find":
                    tasks.findTasks(arguments);
                    break;
                default:
                    Ui.showUnknownCommandMessage(arguments);
                    break;
                }
            } catch (ProcrastinAidException e) {
                System.out.println(e.getMessage());
            }
            parser.clearInput();
        }
    }

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    public static void addTask(String userInp, TaskType type, Storage storage) throws ProcrastinAidException {
        System.out.println("Got it. I've added this procrastinaid.task:");
        Task newTask = switch (type) {
            case TODO -> tasks.addTodo(userInp);
            case DEADLINE -> tasks.addDeadline(userInp);
            case EVENT -> tasks.addEvent(userInp);
            default -> null;
        };
        storage.saveToFile(tasks);
        Ui.showTask(newTask);
        Ui.showTaskListSize(tasks.getSize());
    }

    public static void markTaskAsDone(String taskNumber, boolean done) {
        int i = Integer.parseInt(taskNumber) - 1;
        try {
            Task tempTask = tasks.markTaskAsDone(i, done);
            Ui.showTask(tempTask);
            if (done) {
                System.out.println("Nice! I've marked this procrastinaid.task as done:");
            } else {
                System.out.println("OK, I've marked this procrastinaid.task as not done yet:");
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.showNotInListMessage();
        }
        storageFile.saveToFile(tasks);
    }

    public static void deleteTask(String taskNumber) {
        int i = Integer.parseInt(taskNumber) - 1;
        try {
            Task tempTask = tasks.deleteTask(i);
            System.out.println("Noted. I've removed this procrastinaid.task:");
            Ui.showTask(tempTask);
            Ui.showTaskListSize(tasks.getSize());
        } catch (IndexOutOfBoundsException e) {
            Ui.showNotInListMessage();
        }
        storageFile.saveToFile(tasks);
    }
}
