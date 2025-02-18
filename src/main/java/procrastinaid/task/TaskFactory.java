package procrastinaid.task;

import procrastinaid.exception.ProcrastinAidException;

/**
 * Factory class to create a procrastinaid.task object from a file line.
 */
public class TaskFactory {

    /**
     * Creates a procrastinaid.task object from a file line.
     *
     * @param fileLine The line from the file.
     * @return The procrastinaid.task object created.
     * @throws ProcrastinAidException If the file line is invalid.
     */
    public static Task createTaskFromFile(String fileLine) throws ProcrastinAidException {
        String[] parts = fileLine.split(",");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        String tag;
        try {
            tag = parts[3];
        } catch (ArrayIndexOutOfBoundsException e) {
            tag = "";
        }

        switch (type) {
        case "T":
            return new ToDo(description, isDone, tag);
        case "D":
            return new Deadline(description, isDone, tag, parts[4]);
        case "E":
            return new Event(description, isDone, tag, parts[4], parts[5]);
        default:
            throw new IllegalArgumentException("Unknown procrastinaid.task type: " + type);
        }
    }
}
