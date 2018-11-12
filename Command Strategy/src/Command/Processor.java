package Command;

import java.util.Stack;

public class Processor {

    private Stack<Command> commandProcessor;

    public Processor() {
        commandProcessor = new Stack<>();
    }

    public void push(Command currentCommand)
    {
        commandProcessor.add(currentCommand);
    }


    public Command pop()
    {
        return commandProcessor.pop();
    }

    public int size()
    {
        return commandProcessor.size();
    }

    public boolean empty()
    {
        return (this.size() == 0);
    }
}
