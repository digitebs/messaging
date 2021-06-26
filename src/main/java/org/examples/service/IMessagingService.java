package org.examples.service;

import org.examples.error.NoMessageAvailableException;
import org.examples.model.MessageThread;

public interface IMessagingService {

  void send(String from, String to, String message);

  int read(String user);

  MessageThread read(String user, int i) throws NoMessageAvailableException;

  String reply(String user, String message) throws NoMessageAvailableException;

  void forward(String user, String to) throws NoMessageAvailableException;

  void clear();
}
