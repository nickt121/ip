public abstract class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public abstract String getIcon();

    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return description;
    }
}
