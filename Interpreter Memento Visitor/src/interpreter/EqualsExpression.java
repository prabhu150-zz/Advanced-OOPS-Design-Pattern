package interpreter;


import visitor.Visitor;

public class EqualsExpression implements Expression {

    private String variableName;
    private ConstantExpression value;

    EqualsExpression(String variableName, ConstantExpression value) {
        this.variableName = variableName;
        this.value = value;
    }

    @Override
    public Double interpret(Context context) {
        double variableValue = value.interpret(context);
        context.setVariable(variableName, variableValue); // storing assignments within context to be used later
        return variableValue;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

