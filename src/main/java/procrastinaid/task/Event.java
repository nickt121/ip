package procrastinaid.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import procrastinaid.exception.ProcrastinAidException;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private static final String ICON = "[E]";
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * Constructor for Event.
     *
     * @param description The description of the event.
     * @param isDone      The status of the event.
     * @param startDate   The start date of the event.
     * @param endDate     The end date of the event.
     * @throws ProcrastinAidException If the date format is invalid.
     */
    public Event(String description, boolean isDone, String startDate, String endDate) throws ProcrastinAidException {
        super(description, isDone);
        try {
            this.startDate = LocalDateTime.parse(startDate, DateTimeFormatter.ofPattern(DATE_FORMAT));
            this.endDate = LocalDateTime.parse(endDate, DateTimeFormatter.ofPattern(DATE_FORMAT));
        } catch (DateTimeParseException e) {
            throw new ProcrastinAidException("Invalid date format. Please use the format " + DATE_FORMAT);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + this.startDate + " to: " + this.endDate + " )";
    }

    @Override
    public String getIcon() {
        return ICON;
    }

    @Override
    public String toFileFormat() {
        String formatStartDate = DateTimeFormatter.ofPattern(DATE_FORMAT).format(startDate);
        String formatEndDate = DateTimeFormatter.ofPattern(DATE_FORMAT).format(endDate);
        return String.format("%c,%d,%s,%s,%s", 'E', this.getStatusInt(), super.toString(), formatStartDate,
                formatEndDate);
    }
}
