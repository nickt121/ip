package procrastinaid.ui;

import procrastinaid.task.Task;

/**
 * Represents the user interface of ProcrastinAid.
 */
public class Ui {
    /**
     * Prints the welcome message.
     */
    public static void hello() {
        System.out.println("Hello! I'm procrastinaid.ui.ProcrastinAid");
        System.out.println("What can I do for you?\n");
    }

    public static String bye() {
        return "Bye. Hope to see you again soon!\n";
    }

    public static String showTaskListSize(int size) {
        return "Now you have " + size + " tasks in the list.\n";
    }

    public static String showTask(Task task) {
        return task.getIcon() + task.getStatusIcon() + " " + task;
    }

    public static String showNotInListMessage() {
        return "Sorry, that procrastinaid.task is not in the list";
    }

    public static String showUnknownCommandMessage(String arguments) {
        return "Oops I don't know what to do with " + arguments;
    }
}
