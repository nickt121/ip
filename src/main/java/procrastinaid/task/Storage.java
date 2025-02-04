package procrastinaid.task;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

import procrastinaid.exception.ProcrastinAidException;

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

