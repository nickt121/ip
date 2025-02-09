package procrastinaid.ui;

import java.util.Scanner;

import procrastinaid.exception.ProcrastinAidException;
import procrastinaid.task.Storage;
import procrastinaid.task.Task;
import procrastinaid.task.TaskList;

/**
 * Represents the main class of ProcrastinAid chatbot.
 */
public class ProcrastinAid {
    private final Storage storageFile;
    private final TaskList tasks;
    private final Parser parser;
    private final Scanner userInput;

    /**
     * Constructor for ProcrastinAid.
     */
    public ProcrastinAid() {
        storageFile = new Storage("./tasks.txt");
        tasks = storageFile.loadFromFile();
        parser = new Parser();
        userInput = new Scanner(System.in);
    }

    public String getResponse(String input) {
        parser.clearInput();
        parser.getUserInput(input);
        System.out.println("Input: " + input);
        String command = parser.getCommand();
        String arguments = parser.getRawArgs();
        System.out.println("Command: " + command);
        System.out.println("Arguments: " + arguments);
        try {
            switch (command) {
            case "bye":
                System.exit(0);
                return ""; // unreachable
            case "list":
                return tasks.printTasks();
            case "mark":
                return markTaskAsDone(arguments, true);
            case "unmark":
                return markTaskAsDone(arguments, false);
            case "todo":
                return addTask(arguments, TaskType.TODO, storageFile);
            case "deadline":
                return addTask(arguments, TaskType.DEADLINE, storageFile);
            case "event":
                return addTask(arguments, TaskType.EVENT, storageFile);
            case "delete":
                return deleteTask(arguments);
            case "find":
                return tasks.findTasks(arguments);
            default:
                return Ui.showUnknownCommandMessage(input);
            }
        } catch (ProcrastinAidException e) {
            return e.getMessage();
        }
    }

    /**
     * Adds a task to the list.
     *
     * @param userInp The description of the task.
     * @param type    The type of the task.
     * @param storage The storage object.
     * @return The message to be displayed.
     */
    public String addTask(String userInp, TaskType type, Storage storage) throws ProcrastinAidException {
        String returnString = "Got it. I've added this procrastinaid.task:\n";
        Task newTask = switch (type) {
            case TODO -> tasks.addTodo(userInp);
            case DEADLINE -> tasks.addDeadline(userInp);
            case EVENT -> tasks.addEvent(userInp);
            default -> null;
        };
        storage.saveToFile(tasks);
        returnString += Ui.showTask(newTask) + "\n";
        returnString += Ui.showTaskListSize(tasks.getSize());

        return returnString;
    }

    /**
     * Marks a task as done or not done.
     *
     * @param taskNumber The index of the task to be marked.
     * @param done       Whether the task is done or not.
     * @return The message to be displayed.
     */
    public String markTaskAsDone(String taskNumber, boolean done) {
        String returnString = "";
        int i = Integer.parseInt(taskNumber) - 1;
        try {
            Task tempTask = tasks.markTaskAsDone(i, done);
            returnString += Ui.showTask(tempTask) + "\n";
            if (done) {
                returnString += "Nice! I've marked this procrastinaid.task as done:";
            } else {
                returnString += "OK, I've marked this procrastinaid.task as not done yet:";
            }
        } catch (IndexOutOfBoundsException e) {
            return Ui.showNotInListMessage();
        }
        storageFile.saveToFile(tasks);
        return returnString;
    }

    /**
     * Deletes a task from the list.
     *
     * @param taskNumber The index of the task to be deleted.
     * @return The message to be displayed.
     */
    public String deleteTask(String taskNumber) {
        String returnString = "";
        int i = Integer.parseInt(taskNumber) - 1;
        try {
            Task tempTask = tasks.deleteTask(i);
            returnString += "Noted. I've removed this procrastinaid.task:\n";
            returnString += Ui.showTask(tempTask) + "\n";
            returnString += Ui.showTaskListSize(tasks.getSize());
        } catch (IndexOutOfBoundsException e) {
            return Ui.showNotInListMessage();
        }
        storageFile.saveToFile(tasks);
        return returnString;
    }
}
