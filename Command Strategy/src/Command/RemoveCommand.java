package Command;

import PriorityQueue.PriorityQueue;

public class RemoveCommand implements Command {

    private PriorityQueue currentQueue;
    private Processor commandProcessor;
    private Object parameters;

    public RemoveCommand(PriorityQueue PQueue,Processor commandProcessor)
    {
        currentQueue = PQueue;
        this.commandProcessor = commandProcessor;
    }


    @Override
    public void execute() {
        parameters = currentQueue.poll();
        commandProcessor.push(this);
    }

    @Override
    public void undo() {
        currentQueue.add(parameters);
    }

}
