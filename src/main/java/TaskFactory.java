public class TaskFactory {
    public static Task createTask(String fileLine) throws ProcrastinAidException {
        String[] parts = fileLine.split(",");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
        case "T":
            return new ToDo(description, isDone);
        case "D":
            return new Deadline(description, isDone, parts[3]);
        case "E":
            return new Event(description, isDone, parts[3], parts[4]);
        default:
            throw new IllegalArgumentException("Unknown task type: " + type);
        }
    }
}