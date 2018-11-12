package interpreter;


import visitor.Visitable;
import visitor.Visitor;

import java.util.Vector;

public class RepeatExpression implements Expression, Visitable {

    private int iterations;
    private Vector<Expression> statements;
    private Context context;

    public RepeatExpression(int iterations, Context context) {
        this.iterations = iterations;
        this.context = context;
        statements = new Vector<>();
    }

    public Vector<Expression> getStatements() {
        return statements;
    }

    public void setStatements(Expression statement) {
        statements.add(statement);
    }

    public int getIterations() {
        return iterations;
    }

    @Override
    public Double interpret(Context context) {

        int iterations = this.iterations; // for multiple blocks, changing global variable causes problems
        double value = 0.0;

        while (iterations-- > 0)
            for (Expression nextStatement : statements) {
                value += nextStatement.interpret(context);
            }
        return value;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
