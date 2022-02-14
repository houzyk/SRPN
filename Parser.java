public abstract class Parser {
  public static String[] parseCommandForSpaces(String command) {
    return command.split("\\s+");
  }

  public static String[] parseComplexSingleLineCommand(String complexCommand) {
    String parsedCommands = complexCommand.replaceAll(" +", " ")
        .replaceAll("([\\p{P}\\p{S}a-z0-9])(?=[\\p{P}\\p{S}a-z])", "$1 ")
        .replaceAll("([^- 0-9])(?=[0-9])", "$1 ");
    return parseCommandForSpaces(parsedCommands.trim());
  }
}
