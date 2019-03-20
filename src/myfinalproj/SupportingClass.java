package myfinalproj;

import java.time.*;

public class SupportingClass implements Runnable {

    private String name;
    private long start;
    private boolean keepAlive;

    
    public SupportingClass(String name) {
        this.name = name;
        this.start = System.currentTimeMillis();
        this.keepAlive = true;
    }

    public void run() {
        while(keepAlive) {

            long currentStamp = System.currentTimeMillis();
            long diff = (currentStamp - start) / 1000;

            if(diff >= 1) {
                keepAlive = false;
            }
        }
    }

   
    public boolean getIsAlive() {
        return this.keepAlive;
    }

    public String getName() {
        return this.name;
    }

   
    public String sendMessage(String friend) {
        return friend + " received intro message from " + name + " [" + System.currentTimeMillis() +"]";
    }

    public void updateTime(long time) {
        this.start = time;
    }

  
    public String replyMessage(String friend) {
        return friend + " received reply message from "+ name +" ["+System.currentTimeMillis()+"]";
    }
}

