import java.io.*;

class Main {
  public static void main(String[] args) {
    SRPN sprn = new SRPN();

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    try {
      while (true) {
        String command = reader.readLine();
        if (command == null) {
          System.exit(0);
        }
        sprn.processCommand(command);
      }
    } catch (IOException e) {
      System.err.println(e.getMessage());
      System.exit(1);
    }
  }
}
