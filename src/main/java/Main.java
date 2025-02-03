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
        		
//        		ONLY FOR DEBUGGING. REMOVE AFTER FIXING THE BUG. STARTS HERE
        		
        		System.out.println("args length -----> " + args.length);
        		
//        		ONLY FOR DEBUGGING. REMOVE AFTER FIXING THE BUG. ENDS HERE
        		
        		if(builtinCommands.contains(command)) {
        			System.out.println(command + " is a shell builtin");
        			
        		} else {
        			System.out.println(checkExecutableFile(command, args));
        		}        		
        	} else {
        		System.out.println(input + ": command not found");
        	}
        }
        
        scanner.close();
    }
    
    public static String checkExecutableFile(String fileName, String[] args) {
    	boolean fileExists = false;
    	String resultPath = null;
    	
    	if(args.length == 0) {
    		File file = new File(fileName);
    		fileExists = file.exists();
    		resultPath = fileExists ? file.getAbsolutePath() : null;
    		
    	} else {
    		String pathString = args[0].substring(args[0].indexOf("/"));
    		String[] pathArray = pathString.split(":");
    		
    		for(String path : pathArray) {
        		
//        		Check if any executable file exists in the given directory with the name of command
        		fileExists = new File(path, fileName).exists();
        		
//        		ONLY FOR DEBUGGING. REMOVE AFTER FIXING THE BUG. STARTS HERE
        		
        		System.out.println("path -----> " + path + "\ncommand -----> " + fileName + "\nfileExists -----> " + fileExists);
        		
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
        		
//        		ONLY FOR DEBUGGING. REMOVE AFTER FIXING THE BUG. ENDS HERE                		
        		
        		if(fileExists) {
        			resultPath = path;
        			break;
        		}
        	}
    	}
    	
    	return fileExists ? fileName + " is " + resultPath + "/" + fileName : fileName + ": not found";
    }
}
