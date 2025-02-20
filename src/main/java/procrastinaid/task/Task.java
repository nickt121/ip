package procrastinaid.task;

/**
 * Represents a procrastinaid.task abstract class.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;
    private String tag;

    /**
     * Constructor for the procrastinaid.task class.
     *
     * @param description Description of the procrastinaid.task.
     * @param isDone      Status of the procrastinaid.task.
     */
    public Task(String description, boolean isDone, String tag) {
        this.description = description;
        this.isDone = isDone;
        this.tag = tag;
    }

    /**
     * Returns the status icon of the procrastinaid.task isDone attribute. The icon is either a cross or a blank.
     *
     * @return Icon of the procrastinaid.task isDone status.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done procrastinaid.task with X
    }


    public abstract String getIcon();

    /**
     * Returns the status of the procrastinaid.task as an integer. 1 if done, 0 if not done.
     *
     * @return Status of the procrastinaid.task as an integer.
     */
    public int getStatusInt() {
        return (isDone ? 1 : 0);
    }

    public abstract String toFileFormat();

    /**
     * Sets the status of the procrastinaid.task.
     */
    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Sets the tag of the procrastinaid.task.
     *
     * @param tag The tag of the procrastinaid.task.
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Returns the tag of the procrastinaid.task.
     *
     * @return Tag of the procrastinaid.task.
     */
    public String getTag() {
        return tag;
    }

    /**
     * Returns the description of the procrastinaid.task.
     *
     * @return Description of the procrastinaid.task.
     */
    @Override
    public String toString() {
        return description;
    }
}
