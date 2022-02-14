import java.util.Stack;

public abstract class View {
  public static void welcomeUser() {
    System.out.println("You can now start interacting with the SRPN calculator");
  }

  public static void printErrorMessage(String error) {
    System.err.println(error);
  }

  public static void printNumberStackTop(Stack<Integer> numberStack) {
    if (numberStack.empty()) {
      printErrorMessage("Stack empty.");
    } else {
      System.out.println(numberStack.peek());
    }
  }

  public static void printNumberStack(Stack<Integer> numberStack) {
    if (numberStack.empty()) {
      System.out.println(Integer.MIN_VALUE);
    } else {
      for (Integer number : numberStack)
        System.out.println(number.toString());
    }
  }
}
