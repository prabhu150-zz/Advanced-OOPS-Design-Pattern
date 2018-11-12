package interpreter;


import visitor.Visitor;

public class VariableExpression implements CompoundExpression {

    private String variableName;

    VariableExpression(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public Double interpret(Context context) {
        return context.getValue(variableName);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
