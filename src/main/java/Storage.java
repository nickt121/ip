import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class Storage {
    private final String DATAFILE;

    public Storage(String filename) {
        this.DATAFILE = filename;
        if (!new File(this.DATAFILE).exists()) {
            try {
                new File(this.DATAFILE).createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveToFile(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(this.DATAFILE);
            for (Task task : tasks) {
                String writeString = task.toFileFormat();
                fw.write(writeString);
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> loadFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(this.DATAFILE));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Task newTask = TaskFactory.createTask(line);
                tasks.add(newTask);
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}

