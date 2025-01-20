// Question: Pizza Builder

// You are tasked with designing a Pizza Builder for a pizza delivery application. The system should allow customers to customize their pizzas with different ingredients and configurations. Use the Builder Design Pattern to implement the solution.
// Requirements

//     Pizza Customization:
//         The pizza should have the following customizable attributes:
//             Size: Small, Medium, Large
//             Crust Type: Thin, Thick, Cheese-Stuffed
//             Toppings: Multiple toppings such as Pepperoni, Mushrooms, Olives, etc.
//             Sauce: Regular, Spicy, No Sauce

//     Builder Design Pattern:
//         Implement a PizzaBuilder class to handle the construction of the pizza object.
//         Make the Pizza class immutable once created.

//     Client:
//         Use the PizzaBuilder to create different types of pizzas, such as:
//             A Vegetarian Pizza
//             A Meat Lover’s Pizza
//             A Custom Pizza with no sauce and extra toppings.

import java.util.ArrayList;
import java.util.List;

enum Size {
    SMALL, MEDIUM, LARGE
}

enum CrustType {
    THIN, THICK, CHEESE_STUFFED
}

enum Sauce {
    REGULAR, SPICY, NONE
}

class Pizza {
  private final Size size;
  private final CrustType crustType;
  private final List<String> toppings;
  private final Sauce sauce;

  // Private constructor to enforce use of the builder
  private Pizza(PizzaBuilder builder) {
      this.size = builder.size;
      this.crustType = builder.crustType;
      this.toppings = builder.toppings;
      this.sauce = builder.sauce;
  }

  @Override
  public String toString() {
      return "Pizza [size=" + size + ", crustType=" + crustType + ", toppings=" + toppings + ", sauce=" + sauce + "]";
  }

  // Builder Class
  public static class PizzaBuilder {
      private Size size;
      private CrustType crustType;
      private List<String> toppings = new ArrayList<>();
      private Sauce sauce;

      public PizzaBuilder setSize(Size size) {
          this.size = size;
          return this;
      }

      public PizzaBuilder setCrustType(CrustType crustType) {
          this.crustType = crustType;
          return this;
      }

      public PizzaBuilder addTopping(String topping) {
          this.toppings.add(topping);
          return this;
      }

      public PizzaBuilder setSauce(Sauce sauce) {
          this.sauce = sauce;
          return this;
      }

      public Pizza build() {
          if (size == null || crustType == null || sauce == null) {
              throw new IllegalStateException("Size, crust type, and sauce must be set!");
          }
          return new Pizza(this);
      }
  }
}

public class BuilderDesignPattern {
  public static void main(String[] args) {
      // Build a vegetarian pizza
      Pizza vegetarianPizza = new Pizza.PizzaBuilder()
              .setSize(Size.MEDIUM)
              .setCrustType(CrustType.THIN)
              .addTopping("Mushrooms")
              .addTopping("Olives")
              .setSauce(Sauce.REGULAR)
              .build();

      // Build a meat lover's pizza
      Pizza meatLoversPizza = new Pizza.PizzaBuilder()
              .setSize(Size.LARGE)
              .setCrustType(CrustType.CHEESE_STUFFED)
              .addTopping("Pepperoni")
              .addTopping("Bacon")
              .setSauce(Sauce.SPICY)
              .build();

      // Build a custom pizza with no sauce
      Pizza customPizza = new Pizza.PizzaBuilder()
              .setSize(Size.SMALL)
              .setCrustType(CrustType.THICK)
              .setSauce(Sauce.NONE)
              .build();

      // Print the pizzas
      System.out.println(vegetarianPizza);
      System.out.println(meatLoversPizza);
      System.out.println(customPizza);
  }
}

