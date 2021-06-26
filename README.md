Simple messaging applications

Messages -> MessageThread -> Message

##### Features
* multi session terminal
* message threads

##### Prerequisite
java 11

##### Building
```
mvn clean package
```
##### Running

**standalone**
```
java -cp target/messaging-1.0-SNAPSHOT.jar org.examples.Messaging
```

**server**

Start one server instance
```
java -cp target/messaging-1.0-SNAPSHOT.jar org.examples.MessagingServer
```

**client**

Open multiple terminal
```
java -cp target/messaging-1.0-SNAPSHOT.jar org.examples.MessagingClient
```