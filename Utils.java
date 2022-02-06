import java.util.ArrayList;

/**
 * Class for utils and helpers used by the SRPN calculator.
 * ? @arithmeticOperations is an array of all SRPN arithmetic operations
 * ? @commentMode is stores whether a comment is present, true upon '#' command
 */

public class Utils {
  private ArrayList<String> arithmeticOperations = new ArrayList<>();
  private boolean commentMode = false; // initially, the calculator is NOT in comment mode

  // * constructor - executes when SRPN constructor is executed
  public Utils () {
    // fills arithmeticOperations array with all SRPN arithmetic operations
    initArithmeticOperationsArray();
  }

  // getter to obtain array of all arithmetic operations
  public ArrayList<String> getArithmeticOperations() {
    return this.arithmeticOperations;
  }

  // getter to obtain commentmode
  public boolean getCommentMode () {
    return this.commentMode;
  }

  // setter for comment mode. toggles comemnt mode on and off whenever a '#' is present
  public void setCommentMode () { this.commentMode = !this.commentMode; }

  // adds each arithmetic operation to the arithmetic operations array
  private void initArithmeticOperationsArray () {
    this.arithmeticOperations.add("+");
    this.arithmeticOperations.add("-");
    this.arithmeticOperations.add("*");
    this.arithmeticOperations.add("/");
    this.arithmeticOperations.add("^");
    this.arithmeticOperations.add("%");
  }
}
