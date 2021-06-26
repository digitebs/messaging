package org.examples.model;

import java.util.LinkedList;
import java.util.List;

public class MessageThread {

  public static int count;

  public int local;
  private LinkedList<Message> messages = new LinkedList<>();

  public MessageThread() {
    count++; // increment
    local = count;
  }

  public MessageThread(String user, String msg) {
    super();
    messages.add(new Message(user, msg));
  }

  public String getPreviousFrom() {
    return messages.get(size() - 2).getFrom();
  }

  public String getFrom() {
    return messages.getLast().getFrom();
  }
  public String getContents() {
    return messages.getLast().getContents();
  }

  public int size() {
    return messages.size();
  }

  public void appendContents(String message) {
    messages.getLast().setContents(
        messages.getLast().getContents() + "," + message);
  }

  public Message getFirst() {
    return messages.getFirst();
  }

  public void addMessage(String user, String msg) {
    messages.add(new Message(user, msg));
  }

  public String toString() {
    String res = "message thread #" + local + ":";
    for (Message m : messages) {
      res += "\n" + m;
    }
    return res;
  }
}
