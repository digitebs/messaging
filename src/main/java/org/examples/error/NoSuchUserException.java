package org.examples.error;

public class NoSuchUserException extends Exception{
  public NoSuchUserException(){
    super("No Such User Exception");
  }
}
