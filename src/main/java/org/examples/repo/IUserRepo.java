package org.examples.repo;

import java.util.HashSet;

public interface IUserRepo {
  void login(String s);
  HashSet<String> getSessions();
}
