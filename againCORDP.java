// Problem: E-Commerce Order Processing System

// You are tasked with designing an order processing system for an e-commerce platform using the Chain of Responsibility pattern. The system should process orders through multiple stages, including:

//     Stock Check: Ensure that the requested items are available in the inventory.
//     Payment Processing: Validate and process the payment.
//     Shipping: Arrange shipping if the previous stages are successful.

// If any stage fails (e.g., out of stock, payment failure), the order should stop processing, and an appropriate error message should be displayed.

abstract class OrderHandler {

	OrderHandler nextProcess;

	OrderHandler(OrderHandler nextProcess) {
		this.nextProcess = nextProcess;
	}

	public void processStage(Order order) {
		if (this.nextProcess != null) {
			this.nextProcess.processStage(order);
		}
	}

}

class StockHandler extends OrderHandler {

	StockHandler(OrderHandler nextProcess) {
		super(nextProcess);
	}

	public void processStage(Order order) {
		if (order.getRemainingStock() > 0) {
			System.out.println("The product is in the stock. Product availability check completed. Moving on to the payment stage...");
			super.processStage(order);
		}
		else {
			System.out.println("Product Unavailable. Can't process the order");
		}
	}
}

class PaymentHandler extends OrderHandler {

	PaymentHandler(OrderHandler nextProcess) {
		super(nextProcess);
	}

	public void processStage(Order order) {
		if (order.getPaymentStatus()) {
			System.out.println("The payment has been made. Moving on to the shipping stage...");
			super.processStage(order);
		}
		else {
			System.out.println("Unable to process / confirm the payment");
		}
	}
}

class ShippingHandler extends OrderHandler {

	ShippingHandler(OrderHandler nextProcess) {
		super(nextProcess);
	}

	public void processStage(Order order) {
		if (order.getShippingStatus()) {
			System.out.println("The payment has been shipped. Purchase completed");
			super.processStage(order);
		}
		else {
			System.out.println("Unable to ship the product");
		}
	}
}

class Order {

	private int remainingStock = 0;
	private boolean madePayment = false;
	private boolean hasBeenShipped = false;
	OrderHandler startStage;

	public void setRemainingStock(int newStock) {
		this.remainingStock = this.remainingStock + newStock;
	}

	public void setHasMadePayment() {
		this.madePayment = true;
	}

	public void setHasNotMadePayment() {
		this.madePayment = false;
	}

	public void setIsShipping() {
		this.hasBeenShipped = true;
	}

	public void setIsNotShipping() {
		this.hasBeenShipped = false;
	}

	public int getRemainingStock() { return this.remainingStock; }

	public boolean getPaymentStatus() { return this.madePayment; }

	public boolean getShippingStatus() { return this.hasBeenShipped; }

}

public class againCORDP {
	public static void main(String []args) {
		Order order = new Order();
		OrderHandler orderHandler = new StockHandler(new PaymentHandler(new ShippingHandler(null)));
		orderHandler.processStage(order);
		order.setRemainingStock(200);
		orderHandler.processStage(order);
		order.setHasMadePayment();
		orderHandler.processStage(order);
		order.setIsShipping();
		orderHandler.processStage(order);
	}
}
