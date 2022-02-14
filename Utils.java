import java.util.ArrayList;
import java.util.Stack;

public class Utils {
  private ArrayList<String> arithmeticOperations = new ArrayList<>();
  private boolean commentMode = false;

  public Utils() {
    initArithmeticOperationsArray();
  }

  public ArrayList<String> getArithmeticOperations() {
    return this.arithmeticOperations;
  }

  public boolean getCommentMode() {
    return this.commentMode;
  }

  public void setCommentMode() {
    this.commentMode = !this.commentMode;
  }

  private void initArithmeticOperationsArray() {
    this.arithmeticOperations.add("+");
    this.arithmeticOperations.add("-");
    this.arithmeticOperations.add("*");
    this.arithmeticOperations.add("/");
    this.arithmeticOperations.add("^");
    this.arithmeticOperations.add("%");
  }

  public boolean isPositivelySaturated(long calculation) {
    return calculation > Integer.MAX_VALUE;
  }

  public boolean isNegativelySaturated(long calculation) {
    return calculation < Integer.MIN_VALUE;
  }

  public boolean checkUnderFlow(Stack<Integer> numberStack) {
    if (numberStack.size() <= 1) {
      View.printErrorMessage("Stack underflow.");
      return false;
    }
    return true;
  }

  public boolean checkOverflow(Stack<Integer> numberStack) {
    if (numberStack.size() >= 23) {
      View.printErrorMessage("Stack overflow.");
      return false;
    }
    return true;
  }
}
