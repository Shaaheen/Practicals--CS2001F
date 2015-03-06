import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
/**
 * Command line program interface.
 * 
 * @author Stephan Jamieson
 * @version 4/3/2015
 */
public class TreeUI {

    private AVLTree target;

    private Map<String, Command> commands;
    
    
    public TreeUI() {
        commands = new HashMap<String, Command>();
        //commands.put("new", new New());
        //commands.put("insert", new Insert());
        commands.put("contains", new Contains());
        //commands.put("print", new Print());
        //commands.put("write", new Write());
        commands.put("help", new Help());
        commands.put("quit", new Quit());
        target = new AVLTree();
    }
    
    public void run() {
        Scanner input = new Scanner(System.in);
        commands.get("help").execute("");
        
        while (true) {
            System.out.print(">");
            Scanner line = new Scanner(input.nextLine());
        
            String keyword = (line.hasNext() ? line.next().trim().toLowerCase() : "");
            String argument = (line.hasNext() ? line.next() : "");
                
            if (commands.containsKey(keyword)) {
                commands.get(keyword).execute(argument);
            }
            else {
                System.out.println("Sorry, that command is not recognised. Type 'help' for assistance.");
            }
        }
    }
    

    public static void main(String[] args) {
        (new TreeUI()).run();
    }
    
    private abstract class Command {
        public abstract String help();

        public abstract void execute(String argument) throws IllegalArgumentException;
   
    }
    
    private class Contains extends Command {
        public String help() { return "contains <key value>"; }

        public void execute(String argument) throws IllegalArgumentException {
            try {
                String response = target.contains(Integer.parseInt(argument)) ? "Yes" : "No";
                System.out.println(response);
            }
            catch (NumberFormatException numFormE) {
                throw new IllegalArgumentException("Insert "+argument+" : argument not an integer.");
            }    
        }
    }
            
    private class Help extends Command {
        public String help() { return "help"; }
        
        public void execute(String argument) throws IllegalArgumentException {
            
            Iterator<String> keywordIter = commands.keySet().iterator();
           
            if (keywordIter.hasNext()) {
                System.out.print("Commands: "+commands.get(keywordIter.next()).help());
                while (keywordIter.hasNext()) {
                    System.out.print(", "+commands.get(keywordIter.next()).help());
                }
                System.out.println(".");
            }
            else {
                System.out.println("No commands installed.");
            }
        }
    }

    private class Quit extends Command {
        public String help() { return "quit"; }
       
        public void execute(String argument) throws IllegalArgumentException {
            System.exit(0);
        }
    }
 

}
