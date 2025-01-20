// creating an arthimetic expression evaluator

interface ArthimeticExpression {
  public int evaluate();
}

class Number implements ArthimeticExpression {
  int num;
  Number(int num) {
    this.num = num;
  }
  public int evaluate() {
    return this.num;
  }
}

class AddExpression implements ArthimeticExpression {

  ArthimeticExpression exp1;
  ArthimeticExpression exp2;

  AddExpression(ArthimeticExpression exp1, ArthimeticExpression exp2) {
    this.exp1 = exp1;
    this.exp2 = exp2;
  }

  public int evaluate() {
    return exp1.evaluate() + exp2.evaluate();
  }
}

class MultiplyExpression implements ArthimeticExpression {

  ArthimeticExpression exp1;
  ArthimeticExpression exp2;

  MultiplyExpression(ArthimeticExpression exp1, ArthimeticExpression exp2) {
    this.exp1 = exp1;
    this.exp2 = exp2;
  }

  public int evaluate() {
    return exp1.evaluate() * exp2.evaluate();
  }
}

public class AgainCompositeDesignPattern {
  public static void main(String []args) {
    ArthimeticExpression result = new AddExpression(new MultiplyExpression(new Number(12), new Number(3)), new AddExpression(new Number(12), new Number(3)));
    System.out.println(result.evaluate());
  }
}