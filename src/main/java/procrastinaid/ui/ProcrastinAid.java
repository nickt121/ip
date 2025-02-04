package procrastinaid.ui;

import java.util.Scanner;

import procrastinaid.exception.ProcrastinAidException;
import procrastinaid.task.Task;
import procrastinaid.task.TaskList;
import procrastinaid.task.Storage;

public class ProcrastinAid {
    private static Storage storageFile = new Storage("./tasks.txt");
    private static TaskList taskList = storageFile.loadFromFile();

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
                    taskList.printTasks();
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
                default:
                    System.out.println("Oops I don't know what to do with " + arguments);
                    break;
                }
            } catch (ProcrastinAidException e) {
                System.out.println(e.getMessage());
            }
            parser.clearInput();
        }
    }

    public static void addTask(String userInp, TaskType type, Storage storage) throws ProcrastinAidException {
        System.out.println("Got it. I've added this procrastinaid.task:");
        Task newTask = switch (type) {
            case TODO -> taskList.addTodo(userInp);
            case DEADLINE -> taskList.addDeadline(userInp);
            case EVENT -> taskList.addEvent(userInp);
            default -> null;
        };
        storage.saveToFile(taskList);
        Ui.showTask(newTask);
        Ui.showTaskListSize(taskList.getSize());
    }

    public static void markTaskAsDone(String taskNumber, boolean done) {
        int i = Integer.parseInt(taskNumber) - 1;
        try {
            Task tempTask = taskList.markTaskAsDone(i, done);
            Ui.showTask(tempTask);
            if (done) {
                System.out.println("Nice! I've marked this procrastinaid.task as done:");
            } else {
                System.out.println("OK, I've marked this procrastinaid.task as not done yet:");
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.showNotInListMessage();
        }
        storageFile.saveToFile(taskList);
    }

    public static void deleteTask(String taskNumber) {
        int i = Integer.parseInt(taskNumber) - 1;
        try {
            Task tempTask = taskList.deleteTask(i);
            System.out.println("Noted. I've removed this procrastinaid.task:");
            Ui.showTask(tempTask);
            Ui.showTaskListSize(taskList.getSize());
        } catch (IndexOutOfBoundsException e) {
            Ui.showNotInListMessage();
        }
        storageFile.saveToFile(taskList);
    }
}
