/**
 * Class for parsing user input into executable arithmetic operations in SRPN class
 */

public abstract class Parser {
  // takes user input divided by spaces and divides each input separated by a space
  public static String[] parseCommandForSpaces(String command) {
    return command.split("\\s+"); // regex for representing spaces
  }

  // A complex command is one that contains may operations within one string. It may look like 1+1+1. So, we take any complex command and separates them so that they can be handled by SRPN. So, 1+1+1 because 1 + 1 + 1 + 1 (notice the spaces).
  public static String[] parseComplexSingleLineCommand(String complexCommand) {
    String parsedCommands = complexCommand.replaceAll(" +", " ") // checks for +1 space and removes the extra spaces, it replaces the extra space by a single space
        .replaceAll("([\\p{P}\\p{S}a-z0-9])(?=[\\p{P}\\p{S}a-z])", "$1 ") // seperates non numeric chatacters such as +, - and groups them by a set of space
        .replaceAll("([^- 0-9])(?=[0-9])", "$1 "); // separates numbers (inc negative numbers) and groups them by a set of space
    return parseCommandForSpaces(parsedCommands.trim()); // removes spaces at the front and end of the parsed command
  }
}
