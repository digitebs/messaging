package service;

import org.examples.error.NoMessageAvailableException;
import org.examples.model.MessageThread;
import org.examples.service.MessagingService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MessagingServiceTest {

  MessagingService msgService = new MessagingService();


  @AfterEach
  public void tearDown() {
    msgService.clear();// clear memory
  }

  @Test
  public void testSend() {
    msgService.send("a", "b", "send");
  }


  @Test
  public void testRead() {
    msgService.send("a", "b", "read");
    int m = msgService.read("b");
    Assertions.assertEquals(m, 1);

  }

  @Test
  public void testReadAt() {
    msgService.send("a", "b", "readat");
    msgService.send("a", "b", "readat2");

    try {
      MessageThread m = msgService.read("b", 1);
      Assertions.assertEquals(m.getFirst().getContents(), "readat");
    } catch (NoMessageAvailableException nme) {
      Assertions.fail();
    }

  }

  @Test
  public void testNoReadAt() {
    try {
      MessageThread m = msgService.read("b", 1);
      Assertions.fail();
    } catch (NoMessageAvailableException nme) {
      Assertions.assertNotNull(nme);
    }

  }


  @Test
  public void testToRead() {
    msgService.send("a", "b", "toread");
    try {
      MessageThread m = msgService.read("c", 1);
      Assertions.fail();
    } catch (NoMessageAvailableException nme) {
      Assertions.assertNotNull(nme);
    }

  }

  @Test
  public void testReply() {
    msgService.send("a", "b", "replya");
    try {
      MessageThread m = msgService.read("b", 1);
      msgService.reply("b", "replyb");
      msgService.reply("b", "replyb");

      Assertions.assertEquals("replyb,replyb", m.getContents());
      m = msgService.read("a", 1);
      Assertions.assertEquals("b", m.getFrom());
    } catch (NoMessageAvailableException nme) {
      Assertions.fail();
    }

  }

  @Test
  public void testForward() {
    msgService.send("a", "b", "forward");
    try {
      MessageThread m = msgService.read("b", 1);
      msgService.forward("b", "c");
      MessageThread m2 = msgService.read("c", 1);
      Assertions.assertEquals("\n\t\tmessage thread #" + m.local + ":\n\t\tfrom a:forward",
          m2.getContents());
    } catch (NoMessageAvailableException nme) {
      Assertions.fail();
    }

  }

}
