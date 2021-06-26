package org.examples.repo;

import java.util.HashSet;

public class UserRepo implements IUserRepo{
  // in memory store
  static HashSet<String> sessions = new HashSet<>();

  public void login(String s){
    sessions.add(s); //login create id
  }

  public HashSet<String> getSessions() {
    return sessions;
  }
}