// Question: Payment Processing System

// You are tasked with designing a Payment Processing System using the Bridge Design Pattern. The system should support multiple payment gateways (like PayPal and Stripe) and different payment types (like credit card and debit card). The system should allow the payment gateways and payment types to evolve independently.
// Requirements

//     Abstraction:
//         Implement a Payment class that acts as the abstraction.
//         It should delegate payment processing to the respective PaymentGateway.

//     Payment Gateways (Implementations):
//         Create two payment gateway implementations:
//             PayPalGateway
//             StripeGateway

//     Payment Types:
//         The Payment abstraction should support two types of payments:
//             CreditCardPayment
//             DebitCardPayment

//     Client Code:
//         Allow the client to process payments for any combination of payment gateways and payment types.

interface PaymentType {
  void makePayment();
}

class CreditCardPayment implements PaymentType {
  public void makePayment() {
    System.out.println("Making payment through the Credit Card");
  }
}

class DebitCardPayment implements PaymentType {
  public void makePayment() {
    System.out.println("Making payment through the Debit Card");
  }
}

abstract class PaymentProcessor {
  PaymentType paymentType;
  PaymentProcessor(PaymentType paymentType) {
    this.paymentType = paymentType;
  }
  void makePayment() {
    this.paymentType.makePayment();
  }
  abstract void printPaymentMerchant();
}

class PaypalPayment extends PaymentProcessor {
  PaypalPayment(PaymentType paymentType) {
    super(paymentType);
  }
  void printPaymentMerchant() {
    System.out.println("Paypal Payment Merchant");
  }
}

class StripePayment extends PaymentProcessor {
  StripePayment(PaymentType paymentType) {
    super(paymentType);
  }
  void printPaymentMerchant() {
    System.out.println("Stripe Payment Merchant");
  }
}

public class BridgeDesignPattern {
  public static void main(String []args) {
    PaymentType creditCardPayment = new CreditCardPayment();
    PaymentProcessor stripePayment = new StripePayment(creditCardPayment);
    stripePayment.printPaymentMerchant();
    stripePayment.makePayment();
  }
}
