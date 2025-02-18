package procrastinaid.task;

/**
 * Represents a to-do task.
 */
public class ToDo extends Task {
    private static final String ICON = "[T]";

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getIcon() {
        return ICON;
    }

    @Override
    public String toFileFormat() {
        return String.format("%c,%d,%s", 'T', this.getStatusInt(), super.toString());
    }
}
