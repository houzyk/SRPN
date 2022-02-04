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
    // 3. initialises array of arithmetic operations: -, +, ^, %, *, /
    Utils utils = new Utils();
    ArrayList<String> arithmeticOperations = utils.getArithmeticOperations();
    // 4. welcomes user
    view.welcomeUser();
  }

  public void processCommand(String s) {

  }

}
