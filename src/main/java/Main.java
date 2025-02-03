import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        
//        Builtin Commands
        Set<String> builtinCommands = new HashSet<String>();
        builtinCommands.add("type");
        builtinCommands.add("exit");
        builtinCommands.add("echo");
        
        while(true) {
        	System.out.print("$ ");
        	
        	String input = scanner.nextLine();
            
        	if(input.equals("exit 0")) {
        		break;
        		
        	} else if(input.startsWith("echo ")) {
        		System.out.println(input.replace("echo ", ""));
        		
        	} else if(input.startsWith("type ")) {
        		String command = input.substring(5);
        		
        		if(builtinCommands.contains(command)) {
        			System.out.println(command + " is a shell builtin");
        			
        		} else {
        			System.out.println(checkExecutableFile(command));
        		}        		
        	} else {
        		System.out.println(input + ": command not found");
        	}
        }
        
        scanner.close();
    }
    
    public static String checkExecutableFile(String fileName) {
    	for(String path : System.getenv("PATH").split(":")) {
    		Path fullPath = Path.of(path, fileName);
    		
    		if(Files.isRegularFile(fullPath)) {
    			return fileName + " is " + fullPath.toString();
    		}
    	}
    	
    	return fileName + ": not found";
    }
}
