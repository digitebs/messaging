package org.examples.model;

public class Message {
  private String from;
  // read ???
  // timestamp???

  public Message(String from, String contents) {
    this.from = from;
    this.contents = contents;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getContents() {
    return contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }

  private String contents;

  @Override
  public String toString() {
    return "from "+getFrom()+":"+ getContents();
  }
}
