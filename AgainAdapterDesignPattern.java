// payment platform

import java.util.HashMap;
import java.util.Map;

enum PaymentType {
  STANDARD,
  PAYPAL,
  STRIPE
} 

class PaymentProcessor {
  volatile ThirdPartyPaymentFactory thirdPartyPaymentFactory = null;
  void pay(PaymentType paymentType, int paymentAmount) {
    if (paymentType == PaymentType.STANDARD) {
      System.out.println("Making payment of " + paymentAmount + " dollars through standard payment");
    }
    else {
      if (this.thirdPartyPaymentFactory == null) {
        synchronized(PaymentProcessor.class) {
          if (this.thirdPartyPaymentFactory == null) {
            this.thirdPartyPaymentFactory = new ThirdPartyPaymentFactory();  
          }
        }
      }
      this.thirdPartyPaymentFactory.get(paymentType).pay(paymentAmount);
    }
  }
}

class ThirdPartyPaymentFactory {
  Map<PaymentType, ThirdPartyPayment> paymentMethods = new HashMap<>();
  ThirdPartyPaymentFactory() {
    paymentMethods.put(PaymentType.PAYPAL, new PaypalPayment());
    paymentMethods.put(PaymentType.STRIPE, new StripePayment());
  }
  void put(PaymentType paymentType, ThirdPartyPayment thirdPartyPayment) {
    paymentMethods.put(paymentType, thirdPartyPayment);
  }
  ThirdPartyPayment get(PaymentType paymentType) {
    return paymentMethods.get(paymentType);
  }
}

interface ThirdPartyPayment {
  void pay(int paymentAmount);
}

class PaypalPayment implements ThirdPartyPayment{
  public void pay(int paymentAmount) {
    System.out.println("Making payment of " + paymentAmount + " dollars through paypal"); 
  }
}

class StripePayment implements ThirdPartyPayment{
  public void pay(int paymentAmount) {
    System.out.println("Making payment of " + paymentAmount + " dollars through stripe"); 
  }
}

public class AgainAdapterDesignPattern {
  public static void main(String []args) {
    PaymentProcessor paymentProcessor = new PaymentProcessor();
    paymentProcessor.pay(PaymentType.STANDARD, 200);
    paymentProcessor.pay(PaymentType.PAYPAL, 200);
    paymentProcessor.pay(PaymentType.STRIPE, 200);
  }
}