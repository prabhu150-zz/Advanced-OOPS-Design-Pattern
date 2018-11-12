package interpreter;


import visitor.Visitable;
import visitor.Visitor;

public class PenDownExpression implements Expression, Visitable {

    @Override
    public Double interpret(Context context) {
        context.getTurtle().penDown();
        return 0.0;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
