package procrastinaid.ui;

import java.util.Scanner;

import procrastinaid.exception.ProcrastinAidException;

public class Parser {
    private static Scanner userInput = new Scanner(System.in);
    private String currentInput;
    private String command;
    private String rawArgs;

    public Parser() {
        this.currentInput = "";
        this.command = "";
        this.rawArgs = "";
    }

    public void clearInput() {
        this.currentInput = "";
        this.command = "";
        this.rawArgs = "";
    }

    public void getStdIn() {
        System.out.print("> ");
        this.currentInput = userInput.nextLine();
    }

    public void setCurrentInput(String input) {
        this.currentInput = input;
    }

    public void getUserInput() {
        getStdIn();
        try {
            parseInputBySpace();
        } catch (ProcrastinAidException e) {
            System.out.println(e.getMessage());
            this.command = "";
        }
    }

    public void parseInputBySpace() throws ProcrastinAidException {
        String[] inputs = this.currentInput.split(" ", 2);
        if (inputs.length == 2) {
            this.command = inputs[0];
            this.rawArgs = inputs[1];
        } else if (inputs.length == 1) {
            this.command = inputs[0];
            if (this.command.equals("list") || this.command.equals("bye")) {
                this.rawArgs = "";
            } else {
                throw new ProcrastinAidException("Not enough arguments for this command");
            }
        } else {
            throw new ProcrastinAidException("Not enough arguments for this command");
        }
    }

    public String getCommand() {
        return this.command;
    }

    public String getRawArgs() {
        return this.rawArgs;
    }
}
