// Problem: ATM Withdrawal System

// You are tasked with implementing an ATM withdrawal system using the Chain of Responsibility pattern. The system should dispense currency notes of specific denominations: ₹2000, ₹500, and ₹100.

// The ATM should:

//     Dispense the largest denomination first, followed by smaller denominations.
//     Pass any remaining amount to the next handler in the chain.
//     If the amount cannot be dispensed exactly (not a multiple of ₹100), it should reject the request.

// using the chain of responsibility design pattern here

abstract class CurrencyDispenser {

    CurrencyDispenser nextCurrency;
    int currDenomination;

    CurrencyDispenser(int currDenomination, CurrencyDispenser nextCurrency) {
        this.nextCurrency = nextCurrency;
        this.currDenomination = currDenomination;
    }

    public void withdraw(int remAmount) {
        if (remAmount >= this.currDenomination) {
            System.out.println(
                "Withdrawing " +
                remAmount / this.currDenomination +
                " notes of " +
                this.currDenomination
            );
            remAmount = remAmount % this.currDenomination;
        }
        if (remAmount >= 0) {
            if (this.nextCurrency != null) {
                this.nextCurrency.withdraw(remAmount);
            } else {
                if (remAmount > 0) System.out.println(
                    "Cannot withdraw " + remAmount + " amount"
                );
            }
        }
    }
}

class TwoThousandCurrency extends CurrencyDispenser {

    TwoThousandCurrency(CurrencyDispenser nextCurrency) {
        super(2000, nextCurrency);
    }
}

class FiveHundredCurrency extends CurrencyDispenser {

    FiveHundredCurrency(CurrencyDispenser nextCurrency) {
        super(500, nextCurrency);
    }
}

class OneHundredCurrency extends CurrencyDispenser {

    OneHundredCurrency(CurrencyDispenser nextCurrency) {
        super(100, nextCurrency);
    }
}

public class chainOfResponsibilityDesignPattern {

    public static void main(String[] args) {
        CurrencyDispenser currencyDispenser = new TwoThousandCurrency(
            new FiveHundredCurrency(new OneHundredCurrency(null))
        );
        currencyDispenser.withdraw(5900);
        currencyDispenser.withdraw(1300);
        currencyDispenser.withdraw(423);
    }
}
