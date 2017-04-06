import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ToDoAppMain {

  private final static String FILE_NAME = "data.csv";

  public static void main(String[] args) {

    if (args.length == 0) {
      System.out.println("JAVA Todo application\n" +
              "=======================\n" +
              "\n" +
              "Command line arguments:\n" +
              " -l   Lists all the tasks\n" +
              " -a   Adds a new task\n" +
              " -r   Removes an task\n" +
              " -c   Completes an task");
    } else if (args[0].equals("-l") && readLinesFromFile().size() > 0) {
        processData(readLinesFromFile());
    } else if (args[0].equals("-l")) {
        System.out.println("No todos for today! :)");
    } else if (args[0].equals("-a") && args.length == 2 ) {
        writeToFile(addToList(args[1]));
    } else if (args[0].equals("-a") && args.length == 1 ) {
        System.out.println("Unable to add: no task provided");
    } else if (args[0].equals("-r") ) {
        removeTask(Integer.parseInt(args[1]));
    }

  }

  private static List<String> readLinesFromFile() {
    Path path = Paths.get(FILE_NAME);
    List<String> rawLines;
    try {
      rawLines = Files.readAllLines(path);
    } catch (IOException e) {
      e.printStackTrace();
      rawLines = new ArrayList<>();
    }

    return rawLines;
  }

  private static ArrayList<String> addToList(String newTask) {
    ArrayList <String> newTaskList = new ArrayList<>();
    newTaskList.addAll(readLinesFromFile());
    newTaskList.add(newTask);
    return  newTaskList;
  }

  private static void writeToFile(ArrayList<String> newList) {
    Path path = Paths.get(FILE_NAME);
    try {
      Files.write(path, newList);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void  removeTask(int taskNumber) {
    readLinesFromFile().remove(taskNumber - 1);
  }


  private static void processData (List<String> rawLines) {
    for(int i = 0; i < rawLines.size(); i++) {
      System.out.println((i +1) + " - " + rawLines.get(i));
    }
  }

}
