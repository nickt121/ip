public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return description;
    }
}
