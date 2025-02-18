package procrastinaid.task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import procrastinaid.exception.ProcrastinAidException;

/**
 * Storage class to handle saving and loading of tasks to and from a file.
 */
public class Storage {
    private final String filepath;

    /**
     * Constructor for Storage class.
     *
     * @param filename File path to save tasks to.
     */
    public Storage(String filename) {
        assert filename != null : "File path should not be null";
        this.filepath = filename;
        if (!new File(this.filepath).exists()) {
            try {
                new File(this.filepath).createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Saves the tasks in the TaskList to a file.
     *
     * @param tasks TaskList to save to file.
     */
    public void saveToFile(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(this.filepath);
            for (Task task : tasks.getTasks()) {
                String writeString = task.toFileFormat();
                fw.write(writeString);
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks from a file and returns a TaskList.
     *
     * @return TaskList of tasks loaded from file.
     */
    public TaskList loadFromFile() {
        TaskList tasks = new TaskList();
        try {
            Scanner scanner = new Scanner(new File(this.filepath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task newTask = TaskFactory.createTaskFromFile(line);
                tasks.addTask(newTask);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
            return new TaskList(); // return an empty list if there is an error
        } catch (ProcrastinAidException e) {
            e.printStackTrace();
            return new TaskList(); // return an empty list if there is an error
        }
        return tasks;
    }
}

