public class Event extends Task {
    private String startDate;
    private String endDate;

    public Event(String description, boolean isDone, String startDate, String endDate) {
        super(description, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + this.startDate + " to: " + this.endDate + " )";
    }

    @Override
    public String getIcon() {
        return "[E]";
    }

    @Override
    public String toFileFormat() {
        return String.format("%c,%d,%s,%s,%s", 'E', this.getStatusInt(), super.toString(), this.startDate, this.endDate);
    }
}
