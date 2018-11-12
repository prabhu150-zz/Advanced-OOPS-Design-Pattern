package visitor;

import interpreter.*;
import memento.Caretaker;
import memento.Originator;

public class DistanceVisitor implements Visitor {

    private Originator originator;
    private Caretaker caretaker;
    private double totalDistance;
    private Context context;


    DistanceVisitor(Context context) {
        this.context = context;
        originator = new Originator();
        caretaker = new Caretaker();
        totalDistance = 0;
    }

    @Override
    public void visit(MoveExpression move) {
        move.getCompoundExpression().accept(this); // this leads us to visit the constant/variable visit subroutine (MOVE X/ MOVE 10)
    }

    @Override
    public void visit(ConstantExpression constant) {
        totalDistance += constant.interpret(context);
    }

    @Override
    public void visit(VariableExpression variable) {
        totalDistance += variable.interpret(context);
    }

    @Override
    public void visit(TurnExpression turn) {
        // no action
    }

    @Override
    public void visit(EqualsExpression equals) {
        // no action
    }

    @Override
    public void visit(PenUpExpression penUp) {
        // no action
    }

    @Override
    public void visit(PenDownExpression penDown) {
        // no action
    }

    @Override
    public void visit(RepeatExpression repeat) {

        int iterations = repeat.getIterations();

        while (iterations-- > 0)
            for (Expression currentExpression : repeat.getStatements())
                currentExpression.accept(this);

    }

    double getTotalDistance() {
        return totalDistance;
    }
}
