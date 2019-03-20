package myfinalproj;

import java.util.ArrayList;

public class Threads implements Runnable {

  
    private ArrayList<Calling> calls;
    private ArrayList<SupportingClass> friends;
    private boolean keepAlive;
    private long start;



    
    public void run() {

        displayCalls();

        while(keepAlive) {

            if(!calls.isEmpty()) {
                callProcess(calls.get(0));
                calls.remove(0);
            }

      
            for(SupportingClass friend: friends) {
                if(!friend.getIsAlive()) {
                    System.out.println("Process "+ friend.getName() +" has received no calls for 1 second, ending...");
                    friends.remove(friend);
                    break;
                }
            }

            long end = System.currentTimeMillis();
            long diff = end - start;
            if(diff >= 1500) {
                System.out.println("Master has received no replies for 1.5 seconds, ending...");
                keepAlive = false;
            }
        }
    }

    
    public Threads(ArrayList<Calling> calls) {
        this.calls = calls;
        this.friends = new ArrayList<SupportingClass>();
        this.setupFriendThreads();
        keepAlive = true;
        start = System.currentTimeMillis();
    }
    private void callProcess(Calling call) {
        ArrayList<String> list = call.getFriends();

        // Send messages
        int idx = getIndex(call.getCaller());

        if (idx >= 0) {
            for (String f : list) {
                String result = friends.get(idx).sendMessage(f);
                System.out.println(result);
                int index = getIndex(f);
                friends.get(index).updateTime(System.currentTimeMillis());
                start = System.currentTimeMillis();
            }

            // Send replies
            for (String f : list) {
                idx = getIndex(f);
                if (idx >= 0) {
                    String result = friends.get(idx).replyMessage(call.getCaller());
                    System.out.println(result);
                    friends.get(idx).updateTime(System.currentTimeMillis());
                    start = System.currentTimeMillis();
                }
            }
        }
    }

    
    private int getIndex(String friend) {
        for(int i=0; i<friends.size(); i++) {
            if(friends.get(i).getName().equalsIgnoreCase(friend))
            {
                return i;
            }
        }

        return -1;
    }

    
    private void displayCalls() {
        System.out.println("** Calls to be made **");
        for(Calling call: calls) {
            System.out.println(call);
        }
    }

    private void setupFriendThreads() {

     
        for(Calling call: calls) {
        	SupportingClass friend = new SupportingClass(call.getCaller());
            this.friends.add(friend);

            Thread thread = new Thread(friend);
            thread.start();
        }
    }
}

