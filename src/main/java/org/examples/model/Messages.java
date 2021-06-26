package org.examples.model;

import java.util.LinkedList;
import org.examples.error.NoMessageAvailableException;

public class Messages {
  private LinkedList<MessageThread> messageThreads = new LinkedList<>();
  private MessageThread last;

  public void add(MessageThread mt) {
    messageThreads.add(mt);
  }

  public int size() {
    return messageThreads.size();
  }
  
  public MessageThread getCurrentMessageThread() {
    return last;
  }

  public MessageThread removeMessageThreadsAt(int i) throws NoMessageAvailableException {
    if (i < 0 || i >= size())
      throw new NoMessageAvailableException();

    return last = messageThreads.remove(i);
  }

}
