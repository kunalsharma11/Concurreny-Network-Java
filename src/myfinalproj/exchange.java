package myfinalproj;


	import java.io.File;
	import java.util.ArrayList;
	import java.util.Scanner;

	
	public class exchange {

	     private static final String FILE_NAME = "calls.txt";

	    public static void main(String [] args) {
	    	ArrayList<Calling> calls = localCalls();
	    	Threads Thread = new Threads(calls);
	        Thread thread = new Thread(Thread);
	        thread.start();
	    }

	    
	    private static ArrayList<Calling> localCalls() {

	        ArrayList<Calling> calls = new ArrayList<Calling>();

	        try {
	            File file = new File("C:\\Users\\pc\\Desktop\\erlproj\\calls.txt");
	            Scanner fin = new Scanner(file);

	            while(fin.hasNextLine()) {

	                String line = fin.nextLine().trim();
	                if(line.length()-2>1) {
	                	 line = line.substring(1, line.length()-2);
	                }
	               

	                String caller = line.substring(0, line.indexOf(","));
	                String friends = line.substring(line.indexOf("[")+1, line.indexOf("]"));

	                String values [] = friends.split(",");

	                Calling call = new Calling();
	                call.setCaller(caller.trim());

	                for(int i=0; i<values.length; i++) {
	                    call.addFriend(values[i].trim());
	                }

	                calls.add(call);
	            }

	            fin.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return calls;
	    }

	}


