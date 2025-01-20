// 2. Game Development - Character Creation

//     Create a base class CharacterTemplate that defines steps for creating a game character:
//         Choose character type (e.g., warrior, mage).
//         Equip weapons (abstract method).
//         Set attributes like health and attack points.
//     Subclasses (WarriorCharacter and MageCharacter) should implement the weapon-equipping step.

abstract class CharacterDesign {
  public abstract void setName();
  public abstract void chooseAttributes();
  public abstract void chooseClan();
  public final void buildCharacter() {
    setName();
    chooseAttributes();
    chooseClan();
  }
}

class WarriorCharacterDesign extends CharacterDesign {
  public void setName() {
    System.out.println("Choose the warrior name here");
  }

  public void chooseAttributes() {
    System.out.println("Choose the warrior attributes here");
  }

  public void chooseClan() {
    System.out.println("Choose the warrior clan here");
  }
}

class MageCharacterDesign extends CharacterDesign {
  public void setName() {
    System.out.println("Choose the mage name here");
  }

  public void chooseAttributes() {
    System.out.println("Choose the mage attributes here");
  }

  public void chooseClan() {
    System.out.println("Choose the mage clan here");
  }
}

public class TemplateDesignPattern {
  public static void main(String []args) {
    CharacterDesign warrior = new WarriorCharacterDesign();
    warrior.buildCharacter();
    CharacterDesign mage = new MageCharacterDesign();
    mage.buildCharacter();
  }
}
