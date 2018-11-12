package interpreter;


import visitor.Visitable;
import visitor.Visitor;

public class EndExpression implements Expression, Visitable {
    @Override
    public Double interpret(Context context) {
        return 0.0;
    } // return nothing

    @Override
    public void accept(Visitor visitor) {
        // do nothing
    }
}
