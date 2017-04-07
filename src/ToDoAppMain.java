import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ToDoAppMain {

  private final static String FILE_NAME = "nodata.csv";

  public static void main(String[] args) {

    if (args.length == 0 || args[0].equals("help")) {
      System.out.println("\nJAVA Todo application\n" +
              "=======================\n" +
              "\n" +
              "Command line arguments:\n" +
              " -l   Lists all the tasks\n" +
              " -a   Adds a new task\n" +
              " -r   Removes a task\n" +
              " -c   Completes a task");
    } else if (args.length == 1 && !(args[0].equals("-l") || args[0].equals("-a") || args[0].equals("-r") || args[0].equals("-c") || args[0].equals("help"))) {
      System.out.println("Unsupported argument");
    } else if (args[0].equals("-l") && readLinesFromFile().size() > 0) {
      processData(readLinesFromFile());
    } else if (args[0].equals("-l")) {
      System.out.println("No todos for today! :)");
    } else if (args[0].equals("-a") && args.length == 2 ) {
      writeToFile(addToList(args[1]));
      processData(readLinesFromFile());
    } else if (args[0].equals("-a") && args.length != 2 ) {
      System.out.println("Unable to add: no task provided");
    } else if (args[0].equals("-r") && args.length != 2 ) {
      System.out.println("Unable to remove: no index provided");
    } else if (args[0].equals("-r") && Integer.parseInt(args[1]) > readLinesFromFile().size()) {
      System.out.println("Unable to remove: index is out of bound");
    } else if (args[0].equals("-r") && args.length == 2 ) {
      writeToFile(removeTask(Integer.parseInt(args[1])));
      processData(readLinesFromFile());
    } else if (args[0].equals("-c")) {
      checkTask(Integer.parseInt(args[1]));
      processData(readLinesFromFile());
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
    newTaskList.add( "[ ] " + newTask);
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

  private static ArrayList<String>  removeTask(int taskNumber) {
    ArrayList <String> newTaskList = new ArrayList<>();
    newTaskList.addAll(readLinesFromFile());
    newTaskList.remove(taskNumber - 1);
    return newTaskList;
  }

  private static void checkTask (int n) {
    ArrayList <String> checkTaskList = new ArrayList<>();
    checkTaskList.addAll(readLinesFromFile());
    String sub = "";
    sub = checkTaskList.get(n-1).substring(4,checkTaskList.get(n-1).length());
    sub = "[x] " + sub ;
    checkTaskList.set(n-1, sub);
    writeToFile(checkTaskList);
  }

  private static void processData (List<String> rawLines) {
    for(int i = 0; i < rawLines.size(); i++) {
      System.out.println((i +1) + " - " + rawLines.get(i));
    }
  }

}
