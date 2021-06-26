package org.examples.error;

public class NoMessageAvailableException extends Exception{
  public NoMessageAvailableException(){
    super("message reading cancelled.");
  }
}
