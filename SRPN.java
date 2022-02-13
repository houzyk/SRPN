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
    String[] parsedCommands = Parser.parseCommandForSpaces(command);
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
    // calculate();
  }

  private void distributeOperationsAndOperandsCommands (String command) {
    if (!this.utils.getCommentMode() && !handleCommandFlow(command)) {
      handleComplexCommands(command);
    } else {
      calculate();
    }
  }

  private void handleComplexCommands (String complexCommand) {
    String[] parsedCommands = Parser.parseComplexSingleLineCommand(complexCommand);
    for (String parsedCommand : parsedCommands) {
      if (!parsedCommand.equals("") && !handleCommandFlow(parsedCommand)) {
        View.printErrorMessage("Unrecognised operator or operand \"" + parsedCommand + "\".");
      } else {
        calculate();
      }
    }
  }

  private boolean handleCommandFlow (String command) {
    if (command.matches("-?[0-9]+")) {
      executeOperandCommand(command);
      return true;
    } else if (this.arithmeticOperations.contains(command)) {
      executeOperationCommand(command);
      return true;
    } else if (command.equals("=")) {
      View.printNumberStackTop(this.numberStack);
      return true;
    } else if (command.equals("d")) {
      View.printNumberStack(this.numberStack);
      return true;
    } else if (command.equals("r")) {
      executeRandomCommand();
      return true;
    }
    return false;
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
      if (this.utils.checkUnderFlow(this.numberStack)) {

        this.firstOperand = this.numberStack.pop();
        this.secondOperand = this.numberStack.pop();

        long firstOperandTest = Long.parseLong(this.firstOperand.toString());
        long secondOperTest = Long.parseLong(this.secondOperand.toString());

        if (operation.equals("+")) {
          Integer additionResult = this.secondOperand + this.firstOperand;
          long additionTestResult = secondOperTest + firstOperandTest;
          executeAfterSaturationCheck(additionTestResult, additionResult);
        } else if (operation.equals("*")) {
          Integer multiplicationResult = this.secondOperand * this.firstOperand;
          long multiplicationTestResult = secondOperTest * firstOperandTest;
          executeAfterSaturationCheck(multiplicationTestResult, multiplicationResult);
        } else if (operation.equals("-")) {
          Integer subtractionResult = this.secondOperand - this.firstOperand;
          long subtractionTestResult = secondOperTest - firstOperandTest;
          executeAfterSaturationCheck(subtractionTestResult, subtractionResult);
        } else if (operation.equals("/")) {
          if (this.firstOperand == 0) {
            handleOperationError("Divide by zero.");
          } else {
            Integer divisionResult = this.secondOperand / this.firstOperand;
            long divisionTestResult = secondOperTest / firstOperandTest;
            executeAfterSaturationCheck(divisionTestResult, divisionResult);
          }
        } else if (operation.equals("^")) {
          if (this.firstOperand < 0) {
            handleOperationError("Negative power.");
          } else {
            Integer powerResult = (int) Math.pow(this.secondOperand, this.firstOperand);
            long powerTestResult = (long) Math.pow(secondOperTest, firstOperandTest);
            executeAfterSaturationCheck(powerTestResult, powerResult);
          }
        } else if (operation.equals("%")) {
          if (this.secondOperand == 0) {
            handleOperationError("Divide by zero.");
          } else if (this.firstOperand == 0) {
            throw new RuntimeException();
          } else {
            Integer moduloResult = this.secondOperand % this.firstOperand;
            long moduloTestResult = secondOperTest % firstOperandTest;
            executeAfterSaturationCheck(moduloTestResult, moduloResult);
          }
        }
      }
    }
    this.operationQueue.clear();
  }

  private void executeAfterSaturationCheck (long testResult, Integer actualResult) {
    if (utils.isPositivelySaturated(testResult)) {
      this.numberStack.push(Integer.MAX_VALUE);
    } else if (utils.isNegativelySaturated(testResult)) {
      this.numberStack.push(Integer.MIN_VALUE);
    } else {
      this.numberStack.push(actualResult);
    }
  }

  private void handleOperationError (String errorMessage) {
    this.numberStack.push(this.secondOperand);
    this.numberStack.push(this.firstOperand);
    View.printErrorMessage(errorMessage);
  }
}
