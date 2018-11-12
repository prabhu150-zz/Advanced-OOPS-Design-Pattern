package visitor;

import interpreter.*;

public interface Visitor {
    // double dispatch to determine which method to call
    void visit(MoveExpression move);

    void visit(TurnExpression turn);

    void visit(EqualsExpression equals);

    void visit(PenUpExpression penUp);

    void visit(PenDownExpression penDown);

    void visit(RepeatExpression repeat);

    void visit(ConstantExpression constant);

    void visit(VariableExpression variable);
}
