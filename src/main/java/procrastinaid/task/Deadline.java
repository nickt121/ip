package procrastinaid.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import procrastinaid.exception.ProcrastinAidException;

public class Deadline extends Task {

    private LocalDateTime by;

    public Deadline(String description, boolean isDone, String by) throws ProcrastinAidException {
        super(description, isDone);
        try {
            String expectedFormat = "yyyy-MM-dd HH:mm";
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern(expectedFormat));
        } catch (DateTimeParseException e) {
            throw new ProcrastinAidException("Invalid date format. Please use the format yyyy-MM-dd HH:mm");
        }
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
        return String.format("%c,%d,%s,%s", 'D', this.getStatusInt(), super.toString(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(by));
    }
}