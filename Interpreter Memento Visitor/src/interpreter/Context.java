package interpreter;

import turtle.TurtleDemo;

import java.util.HashMap;
import java.util.Map;

public class Context {

    private Map<String, Double> context;
    private TurtleDemo turtle;

    public Context(TurtleDemo turtle) {
        context = new HashMap<>();
        this.turtle = turtle;
    }

    double getValue(String variableName) {
        if (context.containsKey(variableName))
            return context.get(variableName); // reassignment
        else
            throw new IllegalArgumentException("Invalid Variable");
    }

    public TurtleDemo getTurtle() {
        return turtle;
    }

    void setVariable(String variableName, Double value) {
        if (context.containsKey(variableName))
            context.replace(variableName, value);
        else
            context.put(variableName, value); //storing new values
    }
}
