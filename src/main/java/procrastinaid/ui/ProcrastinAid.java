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
        this.storageFile = new Storage("./tasks.txt");
        this.tasks = storageFile.loadFromFile();
        this.parser = new Parser();
        this.userInput = new Scanner(System.in);
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
            String response;
            switch (command) {
            case "bye":
                System.exit(0);
                response = ""; // unreachable
                break;
            case "list":
                response = tasks.printTasks();
                break;
            case "mark":
                response = markTaskAsDone(arguments, true);
                break;
            case "unmark":
                response = markTaskAsDone(arguments, false);
                break;
            case "todo":
                response = addTask(arguments, TaskType.TODO, storageFile);
                break;
            case "deadline":
                response = addTask(arguments, TaskType.DEADLINE, storageFile);
                break;
            case "event":
                response = addTask(arguments, TaskType.EVENT, storageFile);
                break;
            case "delete":
                response = deleteTask(arguments);
                break;
            case "find":
                response = tasks.findTasks(arguments);
                break;
            case "tag":
                response = setTag(arguments);
                break;
            default:
                response = Ui.showUnknownCommandMessage(input);
            }
            assert response != null : "Response should not be null";
            return response;
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

        assert newTask != null : "New task should not be null";
        storage.saveToFile(tasks);
        returnString += Ui.showTask(newTask) + "\n";
        returnString += Ui.showTaskListSize(tasks.getSize());

        return returnString;
    }

    /**
     * Translates the task number from user input to the index in the list.
     *
     * @param taskNumber The task number from user input.
     * @return The index of the task in the list.
     */
    public int translateTaskNumber(String taskNumber) {
        return Integer.parseInt(taskNumber) - 1;
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
        int i = translateTaskNumber(taskNumber);
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
        int i = translateTaskNumber(taskNumber);
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

    /**
     * Sets the tag of a task.
     *
     * @param taskNumber The index of the task to be tagged.
     * @param tag        The tag to be set.
     * @return The message to be displayed.
     */
    public String setTag(String userInput) {
        String[] splitInput = userInput.split(" ", 2);
        String taskNumber = splitInput[0];
        String tag = splitInput[1];
        int i = translateTaskNumber(taskNumber);
        try {
            Task tempTask = tasks.setTag(i, tag);
            storageFile.saveToFile(tasks);
            return "Got it. I've added the tag to this procrastinaid.task:\n" + Ui.showTaskTag(tempTask);
        } catch (IndexOutOfBoundsException e) {
            return Ui.showNotInListMessage();
        }
    }
}
