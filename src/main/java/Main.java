import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        
        Set<String> commands = new HashSet<String>();
        commands.add("type");
        commands.add("exit");
        commands.add("echo");
        
        while(true) {
        	System.out.print("$ ");
        	
        	String input = scanner.nextLine();
            
        	if(input.equals("exit 0")) {
        		break;
        		
        	} else if(input.startsWith("echo ")) {
        		System.out.println(input.replace("echo ", ""));
        		
        	} else if(input.startsWith("type ")) {
        		String command = input.substring(5);
        		String message = command + (commands.contains(command) ? " is a shell builtin" : ": not found");
        		
        		System.out.println(message);
        		
        	} else {
        		System.out.println(input + ": command not found");
        	}
        }
        
        scanner.close();
    }
}
