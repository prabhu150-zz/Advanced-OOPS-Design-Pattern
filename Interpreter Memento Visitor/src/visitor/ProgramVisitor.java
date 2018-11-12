package visitor;

import interpreter.*;
import memento.Caretaker;
import memento.Memento;
import memento.Originator;
import turtle.TurtleDemo;

import java.util.Vector;

class ProgramVisitor implements Visitor {

    private Context context;
    private Originator originator;
    private Caretaker caretaker;
    private Vector<Memento> mementos;

    ProgramVisitor(Context context) {
        this.context = context;
        originator = new Originator();
        caretaker = new Caretaker();
        mementos = new Vector<>();
    }

    Vector<Memento> getMementos() {
        return mementos;
    }

    Memento saveState(TurtleDemo turtle) {
        originator.set(new TurtleDemo(turtle));
        Memento currentState = originator.storeInMemento();
        caretaker.addMemento(currentState);
        mementos.add(currentState);
        return currentState;
    }

    @Override
    public void visit(MoveExpression move) {
        move.interpret(context);
        saveState(context.getTurtle());
    }

    @Override
    public void visit(TurnExpression turn) {
        turn.interpret(context);
        saveState(context.getTurtle());
    }

    @Override
    public void visit(EqualsExpression equals) {
        equals.interpret(context); // this step not a state-changer, so not saved
    }

    @Override
    public void visit(PenUpExpression penUp) {
        penUp.interpret(context);
        saveState(context.getTurtle());
    }

    @Override
    public void visit(PenDownExpression penDown) {
        penDown.interpret(context);
        saveState(context.getTurtle());
    }

    @Override
    public void visit(RepeatExpression repeat) {
        int iterations = repeat.getIterations();

        while (iterations-- > 0) {
            for (Expression statement : repeat.getStatements())
                statement.accept(this); // this step ensures the commands save its own state
        }

    }

    @Override
    public void visit(ConstantExpression constant) {
        constant.interpret(context); // this step not a state-changer, so not saved
    }

    @Override
    public void visit(VariableExpression variable) {
        variable.interpret(context); // this step not a state-changer, so not saved
    }
}