package interpreter;

import visitor.Visitor;

public class ConstantExpression implements CompoundExpression {
    private double constant; // used to store constants

    ConstantExpression(double constant) {
        this.constant = constant;
    }

    @Override
    public Double interpret(Context context) {
        return constant;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
