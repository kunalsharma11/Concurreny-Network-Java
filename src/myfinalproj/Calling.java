package myfinalproj;


import java.util.ArrayList;

public class Calling {

    private String caller;
    private ArrayList<String> friends;

    
    public Calling() {
        this.caller = "";
        this.friends = new ArrayList<String>();
    }

    
    public void setCaller(String caller) {
        this.caller = caller;
    }

   
    public String getCaller() {
        return this.caller;
    }

  
    public void addFriend(String friend) {
        this.friends.add(friend);
    }

    
    public ArrayList<String> getFriends() {
        return this.friends;
    }

    
    public String toString() {

        String output = caller + ": [";
        output += friends.get(0);

        for(int i=1; i<friends.size(); i++) {
            output += ", " + friends.get(i);
        }

        output += "]";

        return output;

    }
}
