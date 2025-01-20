// design a vending machine

// idle state -> has oney state
// has money state -> item selected state, return money state
// item selected state -> disptach item state
// dispatch state -> return money state
// return money state -> idle state

interface MachineState {
  void insertMoney(int money);
  void selectItem();
  void dispatchItem();
  void returnMoney();
}

class StartTransactionState implements MachineState {

  VendingMachine vendingMachine;
  StartTransactionState(VendingMachine vendingMachine) {
    this.vendingMachine = vendingMachine;
  }

  @Override
  public void insertMoney(int money) {
    // TODO Auto-generated method stub
    System.out.println("Insert money to start the transaction.");
    this.vendingMachine.insertCash(money);
    this.vendingMachine.setMoneyInsertedTransactionState();
  }

  @Override
  public void selectItem() {
    // TODO Auto-generated method stub
    System.out.println("Unavailable Operation");
  }

  @Override
  public void dispatchItem() {
    // TODO Auto-generated method stub
    System.out.println("Unavailable Operation");
  }

  @Override
  public void returnMoney() {
    // TODO Auto-generated method stub
    System.out.println("Unavailable Operation");
  }

}

class MoneyInsertedTransactionState implements MachineState {

  VendingMachine vendingMachine;
  MoneyInsertedTransactionState(VendingMachine vendingMachine) {
    this.vendingMachine = vendingMachine;
  }

  @Override
  public void insertMoney(int money) {
    // TODO Auto-generated method stub
    System.out.println("Unavailable Operation");
  }

  @Override
  public void selectItem() {
    // TODO Auto-generated method stub
    System.out.println("Select the item you want to buy");
    if (this.vendingMachine.getProductPrice() > this.vendingMachine.getMoneyCash()) {
      System.out.println("The cash inserted is less that the price of the product, no purchase can be made");
      this.vendingMachine.setReturnMoneyState();
      return;
    }
    this.vendingMachine.setItemSelectedState();
  }

  @Override
  public void dispatchItem() {
    // TODO Auto-generated method stub
    System.out.println("Unavailable Operation");
  }

  @Override
  public void returnMoney() {
    // TODO Auto-generated method stub
    System.out.println("Unavailable Operation");
  }

}

class ItemSelectedState implements MachineState {

  VendingMachine vendingMachine;
  ItemSelectedState(VendingMachine vendingMachine) {
    this.vendingMachine = vendingMachine;
  }

  @Override
  public void insertMoney(int money) {
    // TODO Auto-generated method stub
    System.out.println("Unavailable Operation");
  }

  @Override
  public void selectItem() {
    // TODO Auto-generated method stub
    System.out.println("Unavailable Operation");
  }

  @Override
  public void dispatchItem() {
    // TODO Auto-generated method stub
    System.out.println("Dispatching the selected item");
    if (this.vendingMachine.getRemainingProduct() <= 0) {
      System.out.println("The product is out of stock.");
      this.vendingMachine.setReturnMoneyState();
      return;
    }
    this.vendingMachine.deductProductPrice();
    this.vendingMachine.setReturnMoneyState();
  }

  @Override
  public void returnMoney() {
    // TODO Auto-generated method stub
    System.out.println("Unavailable Operation");
  }

}

class ReturnMoneyState implements MachineState {

  VendingMachine vendingMachine;
  ReturnMoneyState(VendingMachine vendingMachine) {
    this.vendingMachine = vendingMachine;
  }

  @Override
  public void insertMoney(int money) {
    // TODO Auto-generated method stub
    System.out.println("Unavailable Operation");
  }

  @Override
  public void selectItem() {
    // TODO Auto-generated method stub
    System.out.println("Unavailable Operation");
  }

  @Override
  public void dispatchItem() {
    // TODO Auto-generated method stub
    System.out.println("Unavailable Operation");
  }

  @Override
  public void returnMoney() {
    // TODO Auto-generated method stub
    System.out.println("Returning the extra money");
    this.vendingMachine.returnExtraMoney();
    this.vendingMachine.setStartTransactionState();
  }

}

class VendingMachine {

  int remainingProducts = 2;
  int cashInserted = 0;
  int productPrice = 100;

  MachineState currentMachineState;
  MachineState startTransactionState;
  MachineState moneyInsertedTransactionState;
  MachineState itemSelectedState;
  MachineState returnMoneyState;

  VendingMachine() {
    this.startTransactionState = new StartTransactionState(this);
    this.moneyInsertedTransactionState = new MoneyInsertedTransactionState(this);
    this.itemSelectedState = new ItemSelectedState(this);
    this.returnMoneyState = new ReturnMoneyState(this);
    this.currentMachineState = startTransactionState;
  }

  public void setStartTransactionState() {
    currentMachineState = startTransactionState;  
  }

  public void setMoneyInsertedTransactionState() {
    currentMachineState = moneyInsertedTransactionState;
  }

  public void setItemSelectedState() {
    currentMachineState = itemSelectedState;
  }

  public void setReturnMoneyState() {
    currentMachineState = returnMoneyState;
  }

  public int getRemainingProduct() {
    return remainingProducts;
  }

  public void setRemainingProduct(int newProducts) {
    this.remainingProducts = this.remainingProducts + newProducts;
  }

  public int getProductPrice() {
    return this.productPrice;
  }

  public void insertCash(int cash) {
    this.cashInserted = this.cashInserted + cash;
  }

  public void deductProductPrice() {
    this.remainingProducts = this.remainingProducts - 1;
    this.cashInserted = this.cashInserted - getProductPrice();
  }

  public int getMoneyCash() {
    return this.cashInserted;
  }

  public int returnExtraMoney() {
    int toReturn = this.cashInserted;
    this.cashInserted = 0;
    return toReturn;
  }

  public void insertMoney(int cash) {
    this.currentMachineState.insertMoney(cash);
  }

  public void selectItem() {
    this.currentMachineState.selectItem();
  }

  public void dispatchProduct() {
    this.currentMachineState.dispatchItem();
  }

  public void returnMoney() {
    this.currentMachineState.returnMoney();
  }

}

public class StateDesignPattern {
  public static void main(String []args) {

    VendingMachine vendingMachine = new VendingMachine();
    vendingMachine.insertMoney(150);
    vendingMachine.selectItem();
    vendingMachine.dispatchProduct();
    vendingMachine.returnMoney();
    vendingMachine.insertMoney(60);
    vendingMachine.selectItem();
    vendingMachine.dispatchProduct();
    vendingMachine.returnMoney();
    vendingMachine.insertMoney(150);
    vendingMachine.selectItem();
    vendingMachine.dispatchProduct();
    vendingMachine.returnMoney();
    vendingMachine.insertMoney(150);
    vendingMachine.selectItem();
    vendingMachine.dispatchProduct();
    vendingMachine.returnMoney();

  }
}