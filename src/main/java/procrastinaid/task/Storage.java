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
    private final String FILEPATH;

    public Storage(String filename) {
        this.FILEPATH = filename;
        if (!new File(this.FILEPATH).exists()) {
            try {
                new File(this.FILEPATH).createNewFile();
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
            FileWriter fw = new FileWriter(this.FILEPATH);
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
            Scanner sc = new Scanner(new File(this.FILEPATH));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Task newTask = TaskFactory.createTaskFromFile(line);
                tasks.addTask(newTask);
            }
            sc.close();
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

