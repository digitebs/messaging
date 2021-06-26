package org.examples;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MessagingClient {

  public static void main(String[] args) {
    try (
        var s = new Socket("localhost", 6666);
        var out = new PrintWriter(s.getOutputStream(), true);
        var in = new BufferedReader(
            new InputStreamReader(s.getInputStream()));
        var br = new BufferedReader(new InputStreamReader(System.in))) {

      while (true) {
        out.println(br.readLine());

        var line = in.readLine();
        if (line.trim().startsWith("<<EOF")) {
          // multiline starts here
          while (!(line = in.readLine()).trim().equals("EOF")) {
            System.out.println(line);
          }
        } else
          System.out.println(line);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
