package org.examples.repo;

import org.examples.error.NoMessageAvailableException;
import org.examples.model.MessageThread;

public interface IMessageRepo {
  void sendMessageThread(String from, MessageThread m);

  MessageThread getMessageThread(String user) throws NoMessageAvailableException;

  int size(String user);

   void clear();
}
