import java.util.Scanner;

public class ProcrastinAid {
    public static void main(String[] args) {
        hello();
        Scanner userInput = new Scanner(System.in);
        while(true){
            String inp = getInput(userInput);
            if (inp.equals("bye")){break;}
            System.out.println(inp + "\n");
        }
        bye();
    }

    public static void hello(){
        System.out.println("Hello! I'm ProcrastinAid");
        System.out.println("What can I do for you?\n");
    }

    public static void bye(){
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public static String getInput(Scanner userInput){
        System.out.print("> ");
        return userInput.nextLine();
    }
}
