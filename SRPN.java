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
  Integer firstOperand; // stores first operand
  Integer secondOperand; // stores second operand


  // instance of utils class. initialises utils
  Utils utils = new Utils();

  // instance of random num generator class. initialises random number generator from RandomNumClass
  RandomNumGenerator randomNumGenerator = new RandomNumGenerator();

  // initialises array of arithmetic operations:-,+,^,%,*,/. array of arithmetic operations
  ArrayList<String> arithmeticOperations = utils.getArithmeticOperations();

  // * constructor - executes the following steps when SRPN is initialised
  public SRPN () {
    View.welcomeUser(); // welcomes user
  }

  public void processCommand(String command) {
    String[] parsedCommands = Parser.parseCommand(command);
    distributeCommandsForExecution (parsedCommands);
  }

  private void distributeCommandsForExecution  (String[] parsedCommands) {
    for (String command : parsedCommands) {
      if (!command.equals("")) {
        if (command.equals("#")) {
          this.utils.setCommentMode();
        } else {
          distributeOperationsAndOperandsCommands(command);
        }
      }
    }
    calculate();
  }

  private void distributeOperationsAndOperandsCommands (String command) {
    if (!this.utils.getCommentMode()) {
      if (command.matches("-?[0-9]+")) {
        executeOperandCommand(command);
      } else if (this.arithmeticOperations.contains(command)) {
        executeOperationCommand(command);
      } else if (command.equals("=")) {
        View.printNumberStackTop(this.numberStack);
      } else if (command.equals("d")) {
        View.printNumberStack(this.numberStack);
      } else if (command.equals("r")) {
        executeRandomCommand();
      } else {
        View.printErrorMessage("Unrecognised operator or operand " + '"' + command + '"' + ".");
      }
    }
  }

  private void executeOperandCommand (String operand) {
    if (this.utils.checkOverflow(this.numberStack)) {
      this.numberStack.add(Integer.parseInt(operand));
    }
  }

  private void executeOperationCommand (String operator) {
    this.operationQueue.add(operator);
  }

  private void executeRandomCommand () {
    if (this.utils.checkOverflow(this.numberStack)) {
      this.numberStack.add(this.randomNumGenerator.generateRandomNumber());
    }
  }

  private void calculate () {
    for (String operation : this.operationQueue) {
      this.operationQueue.remove();
      if (this.utils.checkUnderFlow(this.numberStack)) {

        this.firstOperand = this.numberStack.pop();
        long firstOperandCheck = Long.parseLong(this.firstOperand.toString());

        this.secondOperand = this.numberStack.pop();
        long secondOperandCheck = Long.parseLong(this.secondOperand.toString());

        switch (operation) {
          case "+":
            Integer additionResult = this.secondOperand + this.firstOperand;
            long additionResultCheck = secondOperandCheck + firstOperandCheck;
            executeAfterSaturationCheck(additionResultCheck, additionResult);
            return;

          case "*":
            Integer multiplicationResult = this.secondOperand * this.firstOperand;
            long multiplicationResultCheck = secondOperandCheck * firstOperandCheck;
            executeAfterSaturationCheck(multiplicationResultCheck, multiplicationResult);
            return;

          case "-":
            Integer subtractionResult = this.secondOperand - this.firstOperand;
            long subtractionResultCheck = secondOperandCheck - firstOperandCheck;
            executeAfterSaturationCheck(subtractionResultCheck, subtractionResult);
            return;

          case "/":
            if (this.firstOperand == 0) {
              handleOperationError("Divide by zero.");
            } else {
              Integer divisionResult = this.secondOperand / this.firstOperand;
              long divisionResultCheck = secondOperandCheck / firstOperandCheck;
              executeAfterSaturationCheck(divisionResultCheck, divisionResult);
            }
            return;

          case "^":
            if (this.firstOperand < 0) {
              handleOperationError("Negative power.");
            } else {
              Integer powerResult = (int) Math.pow(this.secondOperand, this.firstOperand);
              long powerResultCheck = (long) Math.pow(secondOperandCheck, firstOperandCheck);
              executeAfterSaturationCheck(powerResultCheck, powerResult);
            }
            return;

          case "%":
            if (this.secondOperand == 0) {
              handleOperationError("Divide by zero.");
            } else if (this.firstOperand == 0) {
              throw new RuntimeException();
            } else {
              Integer moduloResult = this.secondOperand % this.firstOperand;
              long moduloResultCheck = secondOperandCheck % firstOperandCheck;
              executeAfterSaturationCheck(moduloResultCheck, moduloResult);
            }
            return;

          default:
            return;
        }
      }
      }
  }

  private void executeAfterSaturationCheck (long check, Integer result) {
    if (utils.isPositivelySaturated(check)) {
      this.numberStack.push(Integer.MAX_VALUE);
    } else if (utils.isNegativelySaturated(check)) {
      this.numberStack.push(Integer.MIN_VALUE);
    } else {
      this.numberStack.push(result);
    }
  }

  private void handleOperationError (String errorMessage) {
    this.numberStack.push(this.secondOperand);
    this.numberStack.push(this.firstOperand);
    View.printErrorMessage(errorMessage);
  }
}
