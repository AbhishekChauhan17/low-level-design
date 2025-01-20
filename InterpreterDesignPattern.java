// 6. Arithmetic Expression with Variables

//     Design an interpreter that can evaluate expressions with variables, such as:
//         "a + b - c", where a = 5, b = 3, c = 2.
//     Allow users to define variables and their values.

import java.util.HashMap;
import java.util.Map;

class Context {
  Map<Character, Integer> map;
  Context() {
    map = new HashMap<>();
  }
  void put(char key, int value) {
    map.put(key, value);
  }
  int get(char key) {
    return map.get(key);
  }
}

interface Expression {
  int interpret(Context context);
}

class TerminalExpression implements Expression {
  char key;
  TerminalExpression(char key) {
    this.key = key;
  }
  public int interpret(Context context) {
    return context.get(this.key);
  }
}

class NonTerminalMultiplyExpression implements Expression {
  Expression leftExpression;
  Expression rightExpression;
  NonTerminalMultiplyExpression(Expression left, Expression right) {
    this.leftExpression = left;
    this.rightExpression = right;
  }
  public int interpret(Context context) {
    return this.leftExpression.interpret(context) * this.rightExpression.interpret(context);
  }
}

class NonTerminalAdditionExpression implements Expression {
  Expression leftExpression;
  Expression rightExpression;
  NonTerminalAdditionExpression(Expression left, Expression right) {
    this.leftExpression = left;
    this.rightExpression = right;
  }
  public int interpret(Context context) {
    return this.leftExpression.interpret(context) + this.rightExpression.interpret(context);
  }
}

public class InterpreterDesignPattern {
  public static void main(String []args) {
    Context context = new Context();
    context.put('a', 10);
    context.put('b', 100);
    int res = new NonTerminalAdditionExpression(new NonTerminalMultiplyExpression(new TerminalExpression('a'), new TerminalExpression('b')), new TerminalExpression('a')).interpret(context);
    System.out.println(res);
  }
}
