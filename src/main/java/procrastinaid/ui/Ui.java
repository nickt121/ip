package procrastinaid.ui;

import procrastinaid.task.Task;

public class Ui {
    public static void hello() {
        System.out.println("Hello! I'm procrastinaid.ui.ProcrastinAid");
        System.out.println("What can I do for you?\n");
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public static void showTaskListSize(int size) {
        System.out.println("Now you have " + size + " tasks in the list.\n");
    }

    public static void showTask(Task task) {
        System.out.println(task.getIcon() + task.getStatusIcon() + " " + task);
    }

    public static void showNotInListMessage() {
        System.out.println("Sorry, that procrastinaid.task is not in the list");
    }

    public static void showUnknownCommandMessage(String arguments) {
        System.out.println("Oops I don't know what to do with " + arguments);
    }
}
