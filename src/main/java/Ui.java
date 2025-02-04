public class Ui {
    public static void hello() {
        System.out.println("Hello! I'm ProcrastinAid");
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
        System.out.println("Sorry, that task is not in the list");
    }
}
