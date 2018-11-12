package Command;

import PriorityQueue.PriorityQueue;

public class AddCommand implements Command{

    private PriorityQueue currentQueue;
    private Processor commandProcessor;
    private Object parameters;

    public AddCommand(PriorityQueue PQueue,Processor commandProcessor,Object parameters)
    {
        currentQueue = PQueue;
        this.commandProcessor = commandProcessor;
        this.parameters = parameters;
    }

    @Override
    public void execute() {
        Object element = parameters;
        currentQueue.add(element);
        commandProcessor.push(this);
    }

    @Override
    public void undo() {
        currentQueue.removeElement(parameters);
    }


}
