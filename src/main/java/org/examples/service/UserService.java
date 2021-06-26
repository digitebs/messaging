package org.examples.service;

import java.util.ArrayList;
import java.util.List;
import org.examples.error.NoSuchUserException;
import org.examples.repo.UserRepo;

public class UserService implements IUserService{
  private UserRepo userRepo = new UserRepo();

  private String user;
  public void login(String u){
    userRepo.login(u);
    user = u;
  }

  public String getUser() {
    return user;
  }

  public String validate(String s) throws NoSuchUserException{
    if(userRepo.getSessions().contains(s))
      return s;

    throw new NoSuchUserException();
  }

  public List<String> getAllUser(){
      return new ArrayList<>(userRepo.getSessions());
  }
}
