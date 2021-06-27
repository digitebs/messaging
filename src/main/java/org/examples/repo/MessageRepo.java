package org.examples.repo;

import java.util.HashMap;
import org.examples.error.NoMessageAvailableException;
import org.examples.model.MessageThread;
import org.examples.model.Messages;

public class MessageRepo implements IMessageRepo {

  // in memory store
  static HashMap<String, Messages> inbox = new HashMap<>();

  public void sendMessageThread(String from, MessageThread m) {
    inbox.putIfAbsent(from, new Messages());
    inbox.get(from).add(m);
  }

  /*
    i start from 0 index

    when we read the message we remove it from messages.
   */
  public MessageThread readMessageThread(String user, int i) throws NoMessageAvailableException {
    Messages m;
    if ((m = inbox.get(user)) == null) throw new NoMessageAvailableException();
    return m.removeMessageThreadsAt(i);
  }

  /*
      get the last/current on screen message thread
   */
  public MessageThread getMessageThread(String user) throws NoMessageAvailableException {
    Messages m;
    if ((m = inbox.get(user)) == null) throw new NoMessageAvailableException();
    return m.getCurrentMessageThread();
  }

  public int size(String user) {
    Messages m;
    if ((m = inbox.get(user)) == null) return 0;
    return m.size();
  }

  public void clear() {
    inbox = new HashMap<>();
  }
}
