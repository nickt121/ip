package procrastinaid.task;

/**
 * Represents a to-do task.
 */
public class ToDo extends Task {
    private static final String ICON = "[T]";

    /**
     * Constructor for ToDo.
     *
     * @param description The description of the to-do.
     * @param isDone      The status of the to-do.
     * @param tag         The tag of the to-do.
     */
    public ToDo(String description, boolean isDone, String tag) {
        super(description, isDone, tag);
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
        return String.format("%c,%d,%s,%s", 'T', this.getStatusInt(), super.toString(), this.getTag());
    }
}
