// 2. Game State Save/Restore

//     Create a simple game with a Player class that has attributes like health, position, and score.
//     Use the Memento pattern to allow the game to save and restore the player's state at checkpoints.

import java.util.ArrayList;
import java.util.List;

class PlayerState {
  String playername;
  int xpos;
  int ypos;
  int health;
  PlayerState(String playername, int xpos, int ypos, int health) {
    this.playername = playername;
    this.xpos = xpos;
    this.ypos = ypos;
    this.health = health;
  }
  void setPosition(int xpos, int ypos) {
    this.xpos = xpos;
    this.ypos = ypos;
  }
  void setHealth(int health) {
    this.health = health;
  } 
  Memento saveState() {
    return new Memento(this);
  }
  void restoreState(Memento memento) {
    this.playername = memento.playername;
    this.xpos = memento.xpos;
    this.ypos = memento.ypos;
    this.health = memento.health;
  }
  void printPlayerInfo() {
    System.out.println("[PLAYER NAME]: " + this.playername + " [X]: " + this.xpos + " [Y]: " + this.ypos + " [HEALTH]: " + this.health); 
  }
}

class Memento {
  final String playername;
  final int xpos;
  final int ypos;
  final int health;
  Memento(PlayerState playerState) {
    this.playername = playerState.playername;
    this.xpos = playerState.xpos;
    this.ypos = playerState.ypos;
    this.health = playerState.health;
  }
}

class Database {
  List<Memento> undoList, redoList;
  Database() {
    undoList = new ArrayList<>();
    redoList = new ArrayList<>();
  }

  public void saveState(Memento memento) {
    undoList.add(memento);
    redoList.clear();
  }

  public Memento undo() {
    if (!undoList.isEmpty()) {
      Memento lastState = undoList.getLast();
      undoList.remove(undoList.size() - 1);
      redoList.add(lastState);
      return lastState;
    }
    return null;
  }

  public Memento redo() {
    if (!redoList.isEmpty()) {
      Memento lastState = redoList.getLast();
      redoList.remove(redoList.size() - 1);
      undoList.add(lastState);
      return lastState;
    }
    return null;
  }
}

public class MementoDesignPattern {
  public static void main(String []args) {
    PlayerState player = new PlayerState("player1", 12, 12, 100);
    Database db = new Database();
    db.saveState(new Memento(player));
    player.printPlayerInfo();
    player.setPosition(20, 20);
    db.saveState(new Memento(player));
    player.printPlayerInfo();
    player.setHealth(50);
    db.saveState(new Memento(player));
    player.printPlayerInfo();
    player.restoreState(db.undo());
    player.printPlayerInfo();
    player.restoreState(db.undo());
    player.printPlayerInfo();
    // player.restoreState(db.redo());
    // player.printPlayerInfo();
    player.restoreState(db.undo());
    player.printPlayerInfo();
  }
}
