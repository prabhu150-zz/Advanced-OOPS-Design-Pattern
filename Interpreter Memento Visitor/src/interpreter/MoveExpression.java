package interpreter;

import visitor.Visitable;
import visitor.Visitor;

public class MoveExpression implements Expression, Visitable {

    private CompoundExpression distance; // expression might be constant/variable

    public MoveExpression(CompoundExpression distance) {
        this.distance = distance;
    }

    public CompoundExpression getCompoundExpression() {
        return distance;
    }

    @Override
    public Double interpret(Context context) {
        double newLocation = distance.interpret(context);
        context.getTurtle().move(newLocation);
        return newLocation;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
