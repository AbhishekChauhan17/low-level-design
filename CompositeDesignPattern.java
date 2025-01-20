// Question: Restaurant Menu System

// You are tasked with designing a Restaurant Menu System using the Composite Design Pattern. The menu consists of Menu Items (e.g., individual dishes) and Menus (e.g., categories like "Drinks," "Main Course," "Desserts"). A menu can contain both menu items and sub-menus.

import java.util.ArrayList;
import java.util.List;

interface MenuComponent {
  public void printComponent();
}

class MenuItem implements MenuComponent{
  String name;
  MenuItem(String name) {
    this.name = name;
  }
  public void printComponent() {
    System.out.println("The item is: " + this.name);
  }
}

class Menu implements MenuComponent{
  String name;
  List<MenuComponent> menuStuff = new ArrayList<>();

  Menu(String name) {
    this.name = name;
  }

  public void addStuff(MenuComponent menuStuff) { this.menuStuff.add(menuStuff); }

  public void removeStuff(MenuComponent menuStuff) { this.menuStuff.remove(menuStuff); }

  public void printComponent() {
    System.out.println("The menu name is: " + this.name);
    for (MenuComponent curr: menuStuff) {
      curr.printComponent();
    }
  }
}

public class CompositeDesignPattern {
  public static void main(String []args) {
    MenuItem drinks = new MenuItem("drinks");
    MenuItem sushi = new MenuItem("sushi");
    MenuItem tempura = new MenuItem("tempura");
    Menu dinnerMenu = new Menu("dinner menu");
    dinnerMenu.addStuff(drinks);
    dinnerMenu.addStuff(sushi);
    Menu breakfastMenu = new Menu("breakfast menu");
    breakfastMenu.addStuff(drinks);
    breakfastMenu.addStuff(new MenuItem("karage"));
    tempura.printComponent();
    dinnerMenu.printComponent();
    breakfastMenu.printComponent();
  }
}