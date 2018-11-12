package interpreter;

import visitor.Visitable;
import visitor.Visitor;

public class TurnExpression implements Expression, Visitable {

    private CompoundExpression degrees;

    TurnExpression(CompoundExpression degrees) {
        this.degrees = degrees;
    }

    @Override
    public Double interpret(Context context) {
        double newOrientation = degrees.interpret(context);
        context.getTurtle().turn(newOrientation);
        return newOrientation;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
