package interpreter;


import visitor.Visitable;
import visitor.Visitor;

public class PenUpExpression implements Expression, Visitable {

    @Override
    public Double interpret(Context context) {
        context.getTurtle().penUp();
        return 0.0;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
