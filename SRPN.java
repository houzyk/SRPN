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

  // * constructor - executes the following steps when SRPN is initialised
  public SRPN () {
    // 1. initialises random number generator from RandomNumClass
    RandomNumGenerator randomNumGenerator = new RandomNumGenerator();

    // 2. initialises utils
    Utils utils = new Utils();

    // 3. initialises array of arithmetic operations: -, +, ^, %, *, /
    ArrayList<String> arithmeticOperations = utils.getArithmeticOperations();

    // 4. initialises comment mode
    boolean commentMode = utils.getCommentMode();

    // 5. welcomes user
    View.welcomeUser();
  }

  public void processCommand(String command) {

  }

}
