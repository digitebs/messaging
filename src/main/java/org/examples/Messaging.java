package org.examples;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import org.examples.error.NoMessageAvailableException;
import org.examples.error.NoSuchUserException;
import org.examples.model.MessageThread;
import org.examples.service.IMessagingService;
import org.examples.service.IUserService;
import org.examples.service.MessagingService;
import org.examples.service.UserService;

public class Messaging {
    // should bind
     IUserService userService = new UserService();
     IMessagingService messagingService = new MessagingService();

    public Messaging(PrintWriter out){
        this.out=out;
    }

    public Messaging(){
        this.out=new PrintWriter(System.out,true);
    }

    public void send(String to, String msg){
        try {
            messagingService.send(userService.getUser(), userService.validate(to), msg);
            out.println("message sent.");
        }catch (NoSuchUserException e){
            out.println(e.getMessage());
        }
    }
    public void read(String i){
        try {
            if (i != null){
                MessageThread m = messagingService.read(userService.getUser(), Integer.parseInt(i));
                out.println("<<EOF\n"+m+"\nEOF"); // added for multi-line support in the network
            }else {
                int c = messagingService.read(userService.getUser());
                String append ="";
                if(c != 0){
                    append +="choose a number from 1 to "+
                    c+" to pick message to read. pick 0 to cancels";
                }
                out.println(c+" new messages. "+ append);
            }
        }catch (NumberFormatException e){
            out.println("invalid argument.");
        }catch (NoMessageAvailableException e){
            out.println(e.getMessage());
        }
    }

    public void reply(String msg){

        try {
            String from = messagingService.reply(userService.getUser(), msg);
            out.println("message sent to "+ from);
        }catch (NoMessageAvailableException e){
            out.println(e.getMessage());
        }
    }

    public void forward(String to){
        try {
            messagingService.forward(userService.getUser(), userService.validate(to));
            out.println("message forwarded to "+to);
        }catch (NoSuchUserException | NoMessageAvailableException e){
            out.println(e.getMessage());
        }
    }

    public void broadcast(String msg){
        List<String> user = userService.getAllUser();
        for(String u:user){
            if(userService.getUser().equals(u));
            messagingService.send(userService.getUser(),u,msg);
        }
    }

    public void login(String user){
        userService.login(user);
        try {
            int s = messagingService.read(userService.getUser());
            String m = "";
            if(s > 0) m+=  userService.getUser() +" logged in,"+ s+" new messages. choose a number 1 to "+ s+
                " to pick the message to read."+
                "pick 0 to cancel";
            out.println(userService.getUser()+" is logged in." + m);
        }catch (Exception e){
            out.println(e.getMessage());
        }
    }

    public void process(String line) {
        int x = line.indexOf("\"");
        String part = line, msg ="";
        if (x != -1){
          part = line.substring(0, x);
          msg = line.substring(x);
       }
        String[] cmd = part.split(" ");

        String args = cmd.length > 1? cmd[1] : null;

        switch (cmd[0]){
            case "login" : login(args); break;
            case "send"  : send(args,msg);break;
            case "read"  : read(args);break;
            case "reply"  : reply(msg);break;
            case "forward"  : forward(args);break;
            case "broadcast"  : broadcast(msg);break;
            case "quit"  : System.exit(0);
            default:        out.println("invalid command.");
        }
    }
    PrintWriter out;

    public static void main(String[] args) {
        Messaging msg = new Messaging();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while(true) {
                String line = br.readLine();
                msg.process(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
