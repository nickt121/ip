package procrastinaid.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import procrastinaid.exception.ProcrastinAidException;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private static final String ICON = "[D]";
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    private LocalDateTime by;

    /**
     * Constructor for Deadline.
     *
     * @param description The description of the deadline.
     * @param isDone      The status of the deadline.
     * @param by          The due date of the deadline.
     * @param tag         The tag of the deadline.
     * @throws ProcrastinAidException If the date format is invalid.
     */
    public Deadline(String description, boolean isDone, String tag, String by) throws ProcrastinAidException {
        super(description, isDone, tag);
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern(DATE_FORMAT));
        } catch (DateTimeParseException e) {
            throw new ProcrastinAidException("Invalid date format. Please use the format " + DATE_FORMAT);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }

    @Override
    public String getIcon() {
        return ICON;
    }

    @Override
    public String toFileFormat() {
        return String.format("%c,%d,%s,%s,%s", 'D', this.getStatusInt(), super.toString(), this.getTag(),
                DateTimeFormatter.ofPattern(DATE_FORMAT).format(by));
    }
}
