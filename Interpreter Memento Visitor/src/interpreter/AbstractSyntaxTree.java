package interpreter;

import java.util.ArrayList;
import java.util.LinkedList;

public class AbstractSyntaxTree {

    private StatementNode currentNode;
    private StatementNode ROOT;
    private Context context;
    private ArrayList<StatementNode> visitedNodes;

    public AbstractSyntaxTree(Context context) {
        currentNode = null;
        visitedNodes = new ArrayList<>();
        this.context = context;
    }

    void add(Expression expression) { // package private

        if (currentNode == null) {
            ROOT = currentNode = new StatementNode(expression); // saves root node for later traversal
        } else if (currentNode.getLeftBranch() == null) {
            currentNode.setLeftBranch(expression);
        } else if (currentNode.getRightBranch() == null) {
            currentNode.setRightBranch(expression);
        } else {
            currentNode = currentNode.getLeftBranch(); // if both branches are not null, then set left child as current node
            add(expression);
        }
    }

    public StatementNode getROOT() {
        return ROOT;
    }

    public ArrayList<StatementNode> traverseTree(StatementNode currentNode) {
        // using BFS to traverse tree setting currentNode as Root
        // stores all the visited nodes in arraylist so each can be interpreted in sequence
        LinkedList<StatementNode> childNodes = new LinkedList<>();
        visitedNodes.clear();
        childNodes.add(currentNode);

        while (childNodes.size() > 0) {

            currentNode = childNodes.poll();
            visitedNodes.add(currentNode);

            if (currentNode.getLeftBranch() != null)
                childNodes.add(currentNode.getLeftBranch());

            if (currentNode.getRightBranch() != null)
                childNodes.add(currentNode.getRightBranch());

        }
        return visitedNodes; // returns a list of expressions (move/turn/penup etc)
    }

    public void interpretTree() {
        this.traverseTree(getROOT());

        for (StatementNode currentNode : visitedNodes) {
            Expression nextStatement = currentNode.getExpression();
            nextStatement.interpret(context);
        }

    }


}
