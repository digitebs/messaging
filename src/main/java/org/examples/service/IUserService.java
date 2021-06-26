package org.examples.service;

import java.util.List;
import org.examples.error.NoSuchUserException;

public interface IUserService {

   void login(String s);

   String getUser();

   String validate(String s) throws NoSuchUserException;

   List<String> getAllUser();
}
