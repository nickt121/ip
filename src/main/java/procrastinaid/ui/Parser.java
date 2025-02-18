package procrastinaid.ui;

import java.util.Scanner;

import procrastinaid.exception.ProcrastinAidException;

/**
 * Represents a parser that parses user input. A parser object is created to parse user input and extract the command.
 */
public class Parser {
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String INPUT_PROMPT = "> ";
    private static final String NOT_ENOUGH_ARGUMENTS_MESSAGE = "Not enough arguments for this command";

    private static Scanner userInput = new Scanner(System.in);
    private String currentInput;
    private String command;
    private String rawArgs;


    /**
     * Constructor for Parser.
     */
    public Parser() {
        this.currentInput = "";
        this.command = "";
        this.rawArgs = "";
    }

    /**
     * Clears the current input. Should be called before getting new input.
     */
    public void clearInput() {
        this.currentInput = "";
        this.command = "";
        this.rawArgs = "";
    }

    /**
     * Gets the standard input from the user. The input is stored in the currentInput attribute.
     */
    public void getStdIn() {
        System.out.print(INPUT_PROMPT);
        this.currentInput = userInput.nextLine();
    }

    public void setCurrentInput(String input) {
        assert input != null : "Input should not be null";
        this.currentInput = input;
    }

    /**
     * Parses the user input by space. The first word is stored in the command attribute and the rest of the input is
     * stored in the rawArgs attribute.
     *
     * @throws ProcrastinAidException If the input does not contain enough arguments.
     */
    public void getUserInput() {
        getStdIn();
        try {
            parseInputBySpace();
        } catch (ProcrastinAidException e) {
            System.out.println(e.getMessage());
            this.command = "";
        }
    }

    /**
     * Parses the user input by space. The first word is stored in the command attribute and the rest of the input is
     * stored in the rawArgs attribute.
     *
     * @throws ProcrastinAidException If the input does not contain enough arguments.
     */
    public void getUserInput(String userInput) {
        this.currentInput = userInput;
        try {
            parseInputBySpace();
        } catch (ProcrastinAidException e) {
            System.out.println(e.getMessage());
            this.command = "";
        }
    }

    /**
     * Parses the user input by space. The first word is stored in the command attribute and the rest of the input is
     * stored in the rawArgs attribute.
     *
     * @throws ProcrastinAidException If the input does not contain enough arguments.
     */
    public void parseInputBySpace() throws ProcrastinAidException {
        String[] inputs = this.currentInput.split(" ", 2);
        if (inputs.length == 2) {
            this.command = inputs[0];
            this.rawArgs = inputs[1];
        } else if (inputs.length == 1) {
            this.command = inputs[0];
            if (this.command.equals(LIST_COMMAND) || this.command.equals(BYE_COMMAND)) {
                this.rawArgs = "";
            } else {
                throw new ProcrastinAidException(NOT_ENOUGH_ARGUMENTS_MESSAGE);
            }
        } else {
            throw new ProcrastinAidException(NOT_ENOUGH_ARGUMENTS_MESSAGE);
        }
    }

    /**
     * Returns the command attribute.
     *
     * @return String The command attribute.
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * Returns the rawArgs attribute.
     *
     * @return String The rawArgs attribute.
     */
    public String getRawArgs() {
        return this.rawArgs;
    }
}
