import org.examples.service.MessagingService;
import org.examples.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MessagingTest {
  UserService userService = new UserService();
  MessagingService messagingService = new MessagingService();


  @AfterEach
  public void tearDown() {
    messagingService.clear();// clear memory
  }

  @Test
  public void TestBroadCast(){
    userService.login("a");
    userService.login("b");
    userService.login("c");

    for (String s:
    userService.getAllUser()) {
      messagingService.send(userService.getUser(),s,"hello");
    }

    try {
      Assertions.assertEquals(messagingService.read("a"),1);
      Assertions.assertEquals(messagingService.read("b"),1);
      Assertions.assertEquals(messagingService.read("c"),1);
    }catch (Exception e){
      Assertions.fail();
    }
  }
}
