import java.io.File;
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
        			
        		} else if(args.length > 0) { // Proceed if argument is provided at runtime. This is for executable file
        			
//        			Extracts path from provided argument
        			String pathString = args[0].substring(args[0].indexOf("/"));
//        			Split the path and store in an array
                	String[] pathArray = pathString.split(":");
                	boolean fileExists = false;
                	
//                	Iterate over all given path to check for executable file
                	for(String path : pathArray) {
                		
//                		Check if any executable file exists in the given directory with the name of command
                		fileExists = new File(path, command).exists();
                		
//                		ONLY FOR DEBUGGING. REMOVE AFTER FIXING THE BUG. STARTS HERE
                		
                		System.out.println("path -----> " + path + "\ncommand -----> " + command + "\nfileExists -----> " + fileExists);
                		
                		File[] listOfFiles = new File(path).listFiles();
                		
                		if(listOfFiles != null) {
                			for (int i = 0; i < listOfFiles.length; i++) {
                				if (listOfFiles[i].isFile()) {
                					System.out.println("File -----> " + listOfFiles[i].getName());
                				} else if (listOfFiles[i].isDirectory()) {
                					System.out.println("Directory -----> " + listOfFiles[i].getName());
                				}
                			}
                		}
                		
//                		ONLY FOR DEBUGGING. REMOVE AFTER FIXING THE BUG. ENDS HERE                		
                		
                		if(fileExists) {
                			System.out.println(command + " is " + path + "/" + command);
                			
//                			Break because only first occurrence needs to be printed
                			break;
                		}
                	}
                	
//                	Executable file does not exist in any given directory
                	if(!fileExists) {
                		System.out.println(command + ": not found");
                	}
        			
        		} else { // Invalid argument is given for the type command
        			System.out.println(command + ": not found");
        		}        		
        	} else {
        		System.out.println(input + ": command not found");
        	}
        }
        
        scanner.close();
    }
}
