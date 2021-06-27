package org.examples.service;

import org.examples.error.NoMessageAvailableException;
import org.examples.model.MessageThread;
import org.examples.repo.MessageRepo;

public class MessagingService implements IMessagingService {

  MessageRepo repo = new MessageRepo();

  // we send a message thread
  public void send(String from, String to, String message) {
    MessageThread mt = new MessageThread(from, message);
    repo.sendMessageThread(to, mt);
  }

  // read the size of threads
  public int read(String user) {
    return repo.size(user);
  }

  public MessageThread read(String user, int i) throws NoMessageAvailableException {
    return repo.readMessageThread(user, i - 1); //subtract by 1
  }

  public String reply(String user, String message) throws NoMessageAvailableException {
    MessageThread messageThread = repo.getMessageThread(user);
    // if the thread from is not same with user
    // we create new conversation
    // else we append the contents
    if (!messageThread.getFrom().equals(user)) {
      messageThread.addMessage(user, message);
      repo.sendMessageThread(messageThread.getPreviousFrom(), messageThread);
    } else {
      messageThread.appendContents(message);
    }

    if (user.equals(messageThread.getFrom())) {
      return messageThread.getPreviousFrom();
    } else {
      return messageThread.getFrom();
    }
  }

  public void forward(String user, String to) throws NoMessageAvailableException {
    MessageThread mt = repo.getMessageThread(user);

    // we create another thread where from is the current user
    // at the same time tab the contents
    MessageThread mt1 = new MessageThread(user, ("\n" + mt.toString()).
        replaceAll("\n", "\n\t\t"));
    repo.sendMessageThread(to, mt1); // creates a copy of the thread
  }

  public void clear() {
    repo.clear();
  }
}
