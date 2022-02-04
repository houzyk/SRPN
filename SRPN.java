import java.util.ArrayList;

/**
 * Class for an SRPN calculator.
 */

public class SRPN {

  // * constructor - executes the following steps when SRPN is initialised
  public SRPN () {
    // 1. initialises Views class for dealing with IO
    View view = new View();

    // 2. initialises random number generator from RandomNumClass
    RandomNumGenerator randomNumGenerator = new RandomNumGenerator();

    // 3. initialises utils
    Utils utils = new Utils();

    // 4. initialises array of arithmetic operations: -, +, ^, %, *, /
    ArrayList<String> arithmeticOperations = utils.getArithmeticOperations();

    // 5. initialises comment mode
    boolean commentMode = utils.getCommentMode();

    // 6. welcomes user
    view.welcomeUser();
  }

  public void processCommand(String command) {

  }

}
