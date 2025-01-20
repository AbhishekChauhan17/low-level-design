// Question: Text Editor Undo/Redo System

// You are tasked with designing a Text Editor Undo/Redo System using the Command Design Pattern. The system should support the following operations:
// Requirements

//     Commands:
//         Insert Text: Adds text to the editor.
//         Delete Text: Deletes the last few characters from the editor.

//     Undo/Redo:
//         The system should allow undoing and redoing commands.

//     History:
//         Keep a history of executed commands for undo/redo functionality.

import java.util.ArrayList;
import java.util.List;

enum Action {
  INSERT,
  DELETE
}

class Receiver {
  String res;
  Receiver() {
    res = "";
  }
  void insert(char c) {
    res = res + c;
  }
  void delete() {
    if (res.length() > 0) {
      res = res.substring(0, res.length() - 1);
    }
  }
  String getString() {
    return res;
  }
}

interface Command {
  void execute(Action action, char c);
}

class InsertCommand implements Command {
  Receiver receiver;
  InsertCommand(Receiver receiver) {
    this.receiver = receiver;
  }
  public void execute(Action action, char c) {
    if (action == Action.INSERT)
      this.receiver.insert(c);
    else
      System.out.println("Cannot execute the deletion here");
  }
}

class DeleteCommand implements Command {
  Receiver receiver;
  DeleteCommand(Receiver receiver) {
    this.receiver = receiver;
  }
  public void execute(Action action, char c) {
    if (action == Action.DELETE)
      this.receiver.delete();
    else
      System.out.println("Cannot execute the insertion here");
  }
}

class Invoker {

  class CommandTracker {
    public Command command;
    public Action action;
    public  char c;
    CommandTracker(Command command, Action action, char c) {
      this.command = command;
      this.action = action;
      this.c = c;
    }
  }

  List<CommandTracker> history = new ArrayList<>();
  List<CommandTracker> redoActions = new ArrayList<>();
  Command command;
  public void setCommand(Command command) {
    this.command = command;
  }
  public void invoke() {
    this.command.execute(Action.DELETE, '\0');
    this.history.add(new CommandTracker(this.command, Action.DELETE, '\0'));

  }
  public void invoke(char c) {
    this.command.execute(Action.INSERT, c);
    this.history.add(new CommandTracker(this.command, Action.INSERT, c));
    redoActions.clear();
  }
  public void undo() {
    if (!history.isEmpty()) {
      CommandTracker lastCommand = history.getLast();
      redoActions.add(lastCommand);
      history.removeLast();
      lastCommand.command.execute(lastCommand.action, lastCommand.c);
    }
  }
  public void redo() {
    if (!redoActions.isEmpty()) {
      CommandTracker lastCommand = redoActions.getLast();
      history.add(lastCommand);
      redoActions.removeLast();
      lastCommand.command.execute(lastCommand.action, lastCommand.c);
    }
  }
}

public class CommandDesignPattern {
  public static void main(String []args) {
    Receiver receiver = new Receiver();
    Command insertCommand = new InsertCommand(receiver);
    Command deleteCommand = new DeleteCommand(receiver);
    Invoker invoker = new Invoker();
    invoker.setCommand(insertCommand);
    invoker.invoke('a');
    invoker.invoke('b');
    invoker.invoke('h');
    invoker.invoke('i');
    invoker.setCommand(deleteCommand);
    invoker.invoke();
    invoker.invoke();
    invoker.setCommand(insertCommand);
    invoker.invoke('h');
    invoker.invoke('i');
    invoker.invoke('s');
    invoker.invoke('h');
    invoker.invoke('e');
    invoker.invoke('s');
    invoker.setCommand(deleteCommand);
    invoker.invoke();
    invoker.setCommand(insertCommand);
    invoker.invoke('k');
    System.out.println(receiver.getString());
  }
}
