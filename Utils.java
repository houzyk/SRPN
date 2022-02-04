import java.util.ArrayList;

/**
 * Class for utils and helpers used by the SRPN calculator.
 * ? @arithmeticOperations is an array of all SRPN arithmetic operations
 */

public class Utils {
  ArrayList<String> arithmeticOperations = new ArrayList<>();

  // * constructor - executes when SRPN constructor is executed
  public Utils () {
    // fills arithmeticOperations array with all SRPN arithmetic operations
    initArithmeticOperations();
  }

  // getter to obtain array of all arithmetic operations
  public ArrayList<String> getArithmeticOperations() {
    return this.arithmeticOperations;
  }

  // adds each arithmetic operation to the arithmetic operations array
  private void initArithmeticOperations () {
    this.arithmeticOperations.add("+");
    this.arithmeticOperations.add("-");
    this.arithmeticOperations.add("*");
    this.arithmeticOperations.add("/");
    this.arithmeticOperations.add("^");
    this.arithmeticOperations.add("%");
  }
}
