import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

  private static void processData (List<String> rawLines) {
    for(int i = 0; i < rawLines.size(); i++) {
      System.out.println((i +1) + " - " + rawLines.get(i));
    }
  }

}
