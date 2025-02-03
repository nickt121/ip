public class Deadline extends Task {

    private String by;

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }

    @Override
    public String getIcon() {
        return "[D]";
    }

    @Override
    public String toFileFormat() {
        return String.format("%c,%d,%s,%s", 'D', this.getStatusInt(), super.toString(), this.by);
    }
}