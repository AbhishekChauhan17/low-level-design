//Got it! Here's a new and unique Factory Pattern problem for you to solve:
//
//Problem:
//You are building an e-commerce system where different types of payment methods (like CreditCard, PayPal, ApplePay) are available for customers to choose from.
//
//Requirements:
//Define a PaymentMethod interface with a method processPayment(double amount) that processes a payment for the given amount.
//Implement concrete classes for different payment methods: CreditCard, PayPal, and ApplePay. Each class should have its own implementation of processPayment().
//Create a PaymentFactory class with a method getPaymentMethod(String methodType) that returns the appropriate payment method object.
//The main program should ask the user for a payment method type (e.g., "CreditCard", "PayPal", "ApplePay") and process a payment using the PaymentFactory.
//Try implementing this! Let me know if you need any hints or help along the way.

enum PaymentMethod {
	CREDIT_CARD,
	CASH_ON_DELIVERY,
	POINT_CARD
}

interface Payment {
	public void makePayment();
}

class CreditCardPayment implements Payment {
	public void makePayment() {
		System.out.println("Making the payment using the credit card");
	}
}

class CashOnDeliveryPayment implements Payment {
	public void makePayment() {
		System.out.println("Making the payment using the cash on delivery");
	}
}

class PointCardPayment implements Payment {
	public void makePayment() {
		System.out.println("Making the payment using the point card");
	}
}

interface FactoryInterface {
	public Payment getPaymentMethod(PaymentMethod paymentMethod) throws Exception;
}

class PaymentMethodFactory implements FactoryInterface {
	public Payment getPaymentMethod(PaymentMethod paymentMethod) throws Exception {
		if (paymentMethod == null) throw new Exception("Unsupported payment method");
		return switch (paymentMethod) {
			case CREDIT_CARD -> new CreditCardPayment();
			case CASH_ON_DELIVERY -> new CashOnDeliveryPayment();
			case POINT_CARD -> new PointCardPayment();
			default -> throw new Exception("Unsupported payment method");
		};
		}
	}

public class FactoryPattern {
	public static void main(String []args) {
		try {
			FactoryInterface paymentFactory = new PaymentMethodFactory();
			Payment creditCardPayment = paymentFactory.getPaymentMethod(PaymentMethod.CREDIT_CARD);
			creditCardPayment.makePayment();
			Payment CashOnDeliveryPayment = paymentFactory.getPaymentMethod(PaymentMethod.CASH_ON_DELIVERY);
			CashOnDeliveryPayment.makePayment();
			Payment PointCardPayment = paymentFactory.getPaymentMethod(PaymentMethod.POINT_CARD);
			PointCardPayment.makePayment();
		} catch (Exception e) {
			System.err.println("Unsupported payment method");
		}
	}
}