import java.util.ArrayList;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Class for an SRPN calculator.
 */

public class SRPN {

  Stack<Integer> numberStack = new Stack<>(); // numbers to be operated on by operations n operationQueue
  Queue<String> operationQueue = new LinkedList<>(); // operations to be performed on numberStack

  Utils utils;
  RandomNumGenerator randomNumGenerator;
  ArrayList<String> arithmeticOperations;
  boolean commentMode;

  // * constructor - executes the following steps when SRPN is initialised
  public SRPN () {
    // 1. initialises random number generator from RandomNumClass
    this.randomNumGenerator = new RandomNumGenerator();

    // 2. initialises utils
    this.utils = new Utils();

    // 3. initialises array of arithmetic operations: -, +, ^, %, *, /
    this.arithmeticOperations = utils.getArithmeticOperations();

    // 4. initialises comment mode
    this.commentMode = this.utils.getCommentMode();

    // 5. welcomes user
    View.welcomeUser();
  }

  public void processCommand(String command) {
    String[] parsedCommands = Parser.parseCommandForSpaces(command);
    distributeCommandsForExecution (parsedCommands);
  }

  private void distributeCommandsForExecution  (String[] parsedCommands) {
    for (String command : parsedCommands) {
      if (command.equals("#")) {
        this.utils.setCommentMode();
      } else {
        distributeOperationsAndOperandsCommands(command);
      }
    }
  }

  private void distributeOperationsAndOperandsCommands (String command) {
    if (!this.commentMode && !command.equals("")) {
      if (command.matches("-?[0-9]+")) {

      } else if (this.arithmeticOperations.contains(command)) {

      } else if (command.equals("=")) {
        View.printNumberStackTop(this.numberStack);
      } else if (command.equals("d")) {
        View.printNumberStack(this.numberStack);
      } else if (command.equals("r")) {

      } else {

      }
    }
  }
}
