package org.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.logging.Logger;
import org.examples.model.MessageThread;
import org.examples.service.MessagingService;
import org.examples.service.UserService;
import org.examples.thread.ServerThread;

public class MessagingServer {

  Logger logger = Logger.getLogger(MessagingServer.class.getName());

  public void start(int port) {
    logger.info("Starting server at port " + port);
    try {
      var ss = new ServerSocket(port);
      while (true) {
        var s = ss.accept();
        var st = new ServerThread(s);
        st.start();
      }
    } catch (IOException ioe) {
      logger.severe("Unable to connect to server." + ioe.getMessage());
    }
  }

  public static void main(String[] args) {
    // write your code here
    var msg = new MessagingServer();
    msg.start(6666);


  }
}
