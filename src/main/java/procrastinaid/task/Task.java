package procrastinaid.task;

public abstract class Task {
    private final String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done procrastinaid.task with X
    }

    public abstract String getIcon();

    public int getStatusInt() {
        return (isDone ? 1 : 0);
    }

    public abstract String toFileFormat();

    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return description;
    }
}
