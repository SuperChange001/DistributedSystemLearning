# Assignment: Socket Communication

> The goal of this assignment is to get some practical experiences with developing a distributed application. Basic socket functionality is offered in package **java.net.Socket**. The following classes help to read and write text to and from a socket:

```java
new BufferedReader(new InputStreamReader(socket.getInputStream()));
new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
```
#### Implement a chat client that works as follows:

* Connect on startup to host “debby.vs.uni-due.de” on TCP port 2223
* Read user input from the console and write messages sent by the server to the console (no graphical user interface required)
#### The protocol works as follows:
* All messages are text, terminated with a linebreak with CR LF (“\r\n”)
* On connect, the server asks for a nickname
* After sending the nickname to the server, the client can send chat messages
* The server distributed chat messages to all connected clients

#### Run two clients A, B at the same time, write a chat message in A and observe when it appears at B.

a) Is the chat message of client A blocked until user B writes its own message?

b) Why does the blocking problem occur and how to solve it?

Solve solution: *DistributedSystemLearning/Exercise_02/src/tcpMultiThreadClient.java*