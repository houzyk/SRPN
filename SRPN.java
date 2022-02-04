import java.util.ArrayList;

/**
 * Class for an SRPN calculator.
 */

public class SRPN {

  // * constructor - executes when SRPN is initialised
  public SRPN () {
    // 1. initialises Views class for dealing with IO
    View view = new View();

    // 2. initialises random number generator from RandomNumClass
    RandomNumGenerator randomNumGenerator = new RandomNumGenerator();

    Utils utils = new Utils();
    // 3. initialises array of arithmetic operations: -, +, ^, %, *, /
    ArrayList<String> arithmeticOperations = utils.getArithmeticOperations();

    // 4. initialises comment mode
    boolean commentMode = utils.getCommentMode();

    // 5. welcomes user
    view.welcomeUser();
  }

  public void processCommand(String s) {

  }

}
