package procrastinaid.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import procrastinaid.exception.ProcrastinAidException;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Event(String description, boolean isDone, String startDate, String endDate) throws ProcrastinAidException {
        super(description, isDone);
        try {
            String expectedFormat = "yyyy-MM-dd HH:mm";
            this.startDate = LocalDateTime.parse(startDate, DateTimeFormatter.ofPattern(expectedFormat));
            this.endDate = LocalDateTime.parse(endDate, DateTimeFormatter.ofPattern(expectedFormat));
        } catch (DateTimeParseException e) {
            throw new ProcrastinAidException("Invalid date format. Please use the format yyyy-MM-dd HH:mm");
        }
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
        String formatStartDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(startDate);
        String formatEndDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(endDate);
        return String.format("%c,%d,%s,%s,%s", 'E', this.getStatusInt(), super.toString(), formatStartDate,
                formatEndDate);
    }
}
