// Question: Chat Application using the Mediator Design Pattern

// You are tasked with designing a Chat Application using the Mediator Design Pattern. The system should allow multiple users to communicate in a chatroom, where the chatroom acts as the mediator to coordinate communication between users.
// Requirements

//     Chatroom Mediator:
//         Implement a Chatroom interface to define methods for sending messages and adding users.
//         Create a ConcreteChatroom class to manage the communication between users.

//     User:
//         Implement a User class that represents a chat participant.
//         Each user should:
//             Have a name.
//             Send messages through the chatroom.
//             Receive messages from the chatroom.

//     Client Code:
//         Simulate a chatroom where multiple users join and send messages to each other.

import java.util.ArrayList;
import java.util.List;

interface Colleague {
  String getName();
  void sendMessage(String msg);
  void getMessage(String senderName, String msg);
}

class ChatMember implements Colleague {
  String name;
  Mediator chatRoom;
  ChatMember(String name, Mediator chatRoom) {
    this.name = name;
    this.chatRoom = chatRoom;
    this.chatRoom.addMember(this);
  }
  public String getName() {
    return this.name;
  }
  public void getMessage(String senderName, String msg) {
    System.out.println("[MSG] " + msg + " [SENT BY] " + senderName);
  }

  public void sendMessage(String msg) {
    this.chatRoom.sendMessage(this, msg);
  }
}

interface Mediator {
  void addMember(Colleague member);
  void sendMessage(Colleague member, String msg);
}

class ChatRoom implements Mediator {
  List<Colleague> chatMembers;
  ChatRoom() {
    chatMembers = new ArrayList<>();
  }
  public void addMember(Colleague member) {
    this.chatMembers.add(member);
  }
  public void sendMessage(Colleague member, String msg) {
    for (Colleague colleague: this.chatMembers) {
      if (colleague.getName() != member.getName()) {
        colleague.getMessage(member.getName(), msg);
      }
    }
  }
}

public class MediatorDesignPattern {
  public static void main(String []args) {
    Mediator chatRoom = new ChatRoom();
    Colleague member1 = new ChatMember("abhishek", chatRoom);
    Colleague member2 = new ChatMember("lakshya", chatRoom);
    Colleague member3 = new ChatMember("ajinkya", chatRoom);
    member1.sendMessage("message from abhishek");
    member3.sendMessage("message from ajinkya");
  }
}
