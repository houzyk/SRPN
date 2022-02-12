/**
 * Class for parsing user input into executable arithmetic operations in SRPN class
 */

public abstract class Parser {
  // takes user input divided by spaces and divides each input separated by a space
  public static String[] parseCommandForSpaces(String command) {
    return command.split("\\s+");
  }

  // A complex command is one that contains may operations within one string. It may look like 1+1+1. So, we take any complex command and separates them so that they can be handled by SRPN. So, 1+1+1 because 1 + 1 + 1 + 1 (notice the spaces).
  public static String parseComplexSingleLineCommand(String complexCommand) {
    return complexCommand.replaceAll(" +", " ")
        .replaceAll("([\\p{P}\\p{S}a-z0-9])(?=[\\p{P}\\p{S}a-z])", "$1 ")
        .replaceAll("([^- 0-9])(?=[0-9])", "$1 ")
        .trim();
  }
}
