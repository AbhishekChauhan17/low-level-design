// 1. Shopping Cart Discount System

// Create a shopping cart where each item type (e.g., Book, Electronics, Clothes) can accept a visitor to calculate discounts:

//     Book: 10% discount.
//     Electronics: 20% discount.
//     Clothes: 15% discount.

// Implement a visitor (DiscountVisitor) that calculates the discount for each type of item.

enum Importance {
  LOW, MEDIUM, HIGH
}

interface Item {
  void accept(Visitor visitor);
  int getPrice();
}

class Book implements Item {
  private final int pages;
  private final Importance authorImportance;
  private int price;

  public Book(int pages, Importance authorImportance) {
      this.pages = pages;
      this.authorImportance = authorImportance;
  }

  public int getPages() {
      return pages;
  }

  public Importance getAuthorImportance() {
      return authorImportance;
  }

  @Override
  public void accept(Visitor visitor) {
      visitor.visit(this);
  }

  @Override
  public int getPrice() {
      return price;
  }

  public void setPrice(int price) {
      this.price = price;
  }
}

class Electronic implements Item {
  private final int warrantyLengthInDays;
  private final Importance brandImportance;
  private int price;

  public Electronic(int warrantyLengthInDays, Importance brandImportance) {
      this.warrantyLengthInDays = warrantyLengthInDays;
      this.brandImportance = brandImportance;
  }

  public int getWarrantyLengthInDays() {
      return warrantyLengthInDays;
  }

  public Importance getBrandImportance() {
      return brandImportance;
  }

  @Override
  public void accept(Visitor visitor) {
      visitor.visit(this);
  }

  @Override
  public int getPrice() {
      return price;
  }

  public void setPrice(int price) {
      this.price = price;
  }
}

class Clothes implements Item {
  private final int size;
  private final Importance brandImportance;
  private int price;

  public Clothes(int size, Importance brandImportance) {
      this.size = size;
      this.brandImportance = brandImportance;
  }

  public int getSize() {
      return size;
  }

  public Importance getBrandImportance() {
      return brandImportance;
  }

  @Override
  public void accept(Visitor visitor) {
      visitor.visit(this);
  }

  @Override
  public int getPrice() {
      return price;
  }

  public void setPrice(int price) {
      this.price = price;
  }
}

interface Visitor {
  void visit(Book book);
  void visit(Electronic electronic);
  void visit(Clothes clothes);
}

class PriceCalculationVisitor implements Visitor {
  private int getImportanceValue(Importance importance) {
      return switch (importance) {
          case LOW -> 1;
          case MEDIUM -> 2;
          case HIGH -> 3;
      };
  }

  @Override
  public void visit(Book book) {
      int importanceValue = getImportanceValue(book.getAuthorImportance());
      book.setPrice(importanceValue * 100 + book.getPages() * 10);
  }

  @Override
  public void visit(Electronic electronic) {
      int importanceValue = getImportanceValue(electronic.getBrandImportance());
      electronic.setPrice(importanceValue * 100 + electronic.getWarrantyLengthInDays() * 10);
  }

  @Override
  public void visit(Clothes clothes) {
      int importanceValue = getImportanceValue(clothes.getBrandImportance());
      clothes.setPrice(importanceValue * 100 + clothes.getSize() * 10);
  }
}

class InventoryCheckingVisitor implements Visitor {
  @Override
  public void visit(Book book) {
      System.out.println("Book is in stock.");
  }

  @Override
  public void visit(Electronic electronic) {
      System.out.println("Electronic item is in stock.");
  }

  @Override
  public void visit(Clothes clothes) {
      System.out.println("Clothes are in stock.");
  }
}

public class VisitorDesignPattern {
  public static void main(String[] args) {
      Item book = new Book(200, Importance.MEDIUM);
      Item clothes = new Clothes(5, Importance.LOW);
      Item electronic = new Electronic(365, Importance.HIGH);

      Visitor priceVisitor = new PriceCalculationVisitor();
      Visitor inventoryVisitor = new InventoryCheckingVisitor();

      book.accept(priceVisitor);
      clothes.accept(priceVisitor);
      electronic.accept(priceVisitor);

      System.out.println("Book price: $" + book.getPrice());
      System.out.println("Clothes price: $" + clothes.getPrice());
      System.out.println("Electronic price: $" + electronic.getPrice());

      book.accept(inventoryVisitor);
      clothes.accept(inventoryVisitor);
      electronic.accept(inventoryVisitor);
  }
}
