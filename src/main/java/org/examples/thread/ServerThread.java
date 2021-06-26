package org.examples.thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.examples.Messaging;

public class ServerThread extends Thread {

  Socket s;

  public ServerThread(Socket s) {
    this.s = s;
  }

  public void run() {

    try (
        var out = new PrintWriter(s.getOutputStream(), true);
        var br = new BufferedReader(new InputStreamReader(s.getInputStream()))) {

      var msg = new Messaging(out);
      String line;

      while ((line = br.readLine()) != null) {
        msg.process(line);
        System.out.print("echo:" + line);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
