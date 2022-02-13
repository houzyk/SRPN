import java.util.ArrayList;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Class for an SRPN calculator.
 */

public class SRPN {

  Stack<Integer> numberStack = new Stack<>(); // stores numbers to be operated on by operations n operationQueue
  Queue<String> operationQueue = new LinkedList<>(); // stores operations to be performed on numberStack
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
    // parses the input for spaces and puts each command into an array
    String[] parsedCommands = Parser.parseCommandForSpaces(command);
    // takes the array of parsedCommands and tries to execute each command
    distributeCommandsForExecution (parsedCommands);
  }

  private void distributeCommandsForExecution  (String[] parsedCommands) {
    for (String command : parsedCommands) {
      // doesn't do anything if there's an empty command
      if (!command.equals("")) {
        if (command.equals("#")) {
          this.utils.setCommentMode(); // toggles comment mode on
        } else {
          distributeOperationsAndOperandsCommands(command, parsedCommands);
        }
      }
      calculate(); // carries calculation after numberStack and operationQueue have been filled
    }
  }

  private void distributeOperationsAndOperandsCommands (String command, String[] parsedCommands) {
    // does not execute if comment mode is on
    if (!this.utils.getCommentMode() && !handleCommandFlow(command)) {
      handleComplexCommands(command, parsedCommands); // handles complex commands such as 1+1+1, these are commands that do not have spaces between them
    }
  }

  private void handleComplexCommands (String complexCommand, String[] initialCommands) {
    String[] parsedCommands = Parser.parseComplexSingleLineCommand(complexCommand);
    for (String parsedCommand : parsedCommands) {
      if (!parsedCommand.equals("") && !handleCommandFlow(parsedCommand)) {
        String errorMessage = "Unrecognised operator or operand \"" + parsedCommand + "\".";
        View.printErrorMessage(errorMessage);
      }
      if (initialCommands.length <= 1) calculate();
    }
  }

  // returns true if the command is a legal one and false if it is not recognised such as if 'q' is inserted
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

  // pushes an operand in the number stack if the stack has space, i.e, if the stack if not overflowing
  private void executeOperandCommand (String operand) {
    if (this.utils.checkOverflow(this.numberStack)) {
      this.numberStack.add(Integer.parseInt(operand));
    }
  }

  // adds an arithmetic operation to the operation's queue to be executed at the end of every command line once calculate() is called
  private void executeOperationCommand (String operator) {
    this.operationQueue.add(operator);
  }

  // pushes a random number on the number stack if the stack has space
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
            handleOperationError("Divide by 0.");
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
            handleOperationError("Divide by 0.");
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
    this.operationQueue.clear(); // empties operations queue after iteration
  }

  // checks for saturation and executes stack push depending on saturation
  private void executeAfterSaturationCheck (long testResult, Integer actualResult) {
    if (utils.isPositivelySaturated(testResult)) {
      this.numberStack.push(Integer.MAX_VALUE); // pushes biggest value integer
    } else if (utils.isNegativelySaturated(testResult)) {
      this.numberStack.push(Integer.MIN_VALUE); // pushes lowest integer value
    } else {
      this.numberStack.push(actualResult); // pushes result if not saturated
    }
  }

  // executes when an operation error check is invalid. for example, if a division by zero occurs
  private void handleOperationError (String errorMessage) {
    // pushes the popped numbers from the number stack back into place
    this.numberStack.push(this.secondOperand);
    this.numberStack.push(this.firstOperand);
    View.printErrorMessage(errorMessage); // displays message depending on the operation error
  }
}
