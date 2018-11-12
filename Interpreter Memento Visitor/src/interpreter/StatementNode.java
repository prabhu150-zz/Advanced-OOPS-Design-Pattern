package interpreter;


public class StatementNode {

    private Expression expression;
    private StatementNode leftBranch;
    private StatementNode rightBranch;

    StatementNode(Expression expression) {
        this.expression = expression;
    }

    StatementNode getLeftBranch() {
        return leftBranch;
    }

    void setLeftBranch(Expression leftBranch) {
        this.leftBranch = new StatementNode(leftBranch);
    }

    StatementNode getRightBranch() {
        return rightBranch;
    }

    void setRightBranch(Expression rightBranch) {
        this.rightBranch = new StatementNode(rightBranch);
    }

    public Expression getExpression() {
        return expression;
    }

}