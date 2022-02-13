import java.util.Stack;

/**
 * Class for dealing with outputting messages to the user's
 * console. The class has two types of methods - one deals with user output
 */

public abstract class View {
  // welcomes user when SRPN is started
  public static void welcomeUser () {
    System.out.println("You can now start interacting with the SRPN calculator");
  }

  // prints any error message
  // error is the error message to be printed- stack overflow/underflow, divide by zero, ..
  public static void printErrorMessage (String error) { System.err.println(error); }

  // prints the top of the SRPN number stack, is triggered with the '=' command
  // numberStack is the SRPN number stack
  public static void printNumberStackTop (Stack<Integer> numberStack) {
    // checks if stack is empty before printing stack's top value
    if (numberStack.empty()) {
      printErrorMessage("Stack empty.");
    } else {
      System.out.println(numberStack.peek());
    }
  }

  // prints the whole SRPN number stack, is triggered with the 'd' command
  public static void printNumberStack(Stack<Integer> numberStack) {
    // checks if stack is empty before printing stack
    if (numberStack.empty()) {
      System.out.println(Integer.MIN_VALUE);
    } else {
      // loops over the stack's numbers and print each number
      for (Integer number : numberStack) System.out.println(number.toString());
    }
  }
}
