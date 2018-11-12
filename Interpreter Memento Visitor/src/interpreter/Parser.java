package interpreter;


public class Parser {

    private String[] allStatements;
    private AbstractSyntaxTree syntaxTree;
    private Context context;
    private int currentLine;

    public Parser(Context context, AbstractSyntaxTree syntaxTree, String allStatements) {
        this.context = context;
        this.syntaxTree = syntaxTree;
        this.allStatements = allStatements.split("\\r?\\n"); // seperate each command within the array using \n delimiter
        currentLine = 0;
    }

    private boolean isVariable(String token) {
        return token.contains("#") || token.contains("$");
    }

    private CompoundExpression checkRightOperand(String token) { // checks if right operand is a constant or a variable expression and returns appropriate expression
        if (isVariable(token))
            return new VariableExpression(token);
        else
            return new ConstantExpression(Double.parseDouble(token));
    }


    Expression parseExpression(String allStatements[], String currentStatement[]) {
        CompoundExpression data;
        int statementSize = currentStatement.length;

        switch (currentStatement[0]) {
            case "penUp":

                return new PenUpExpression();

            case "penDown":
                return new PenDownExpression();

            case "move":
                data = checkRightOperand(currentStatement[1]);
                return new MoveExpression(data);

            case "turn":
                data = checkRightOperand(currentStatement[1]);
                return new TurnExpression(data);

            case "repeat": {
                int iterations = checkRightOperand(currentStatement[1]).interpret(context).intValue();
                RepeatExpression repeat = new RepeatExpression(iterations, context);

                while (true) {
                    String currentCommand = allStatements[++currentLine]; // update currentline to avoid running again
                    currentStatement = currentCommand.split("\\s+");

                    if (currentStatement[0].equals("end"))
                        break;

                    Expression currentExpression = parseExpression(allStatements, currentStatement);
                    repeat.setStatements(currentExpression);
                }

                return repeat;
            }

            case "end":
                return new EndExpression();

            default:
                if (statementSize > 2) {
                    if (currentStatement[1].equals("=")) // assignment operation
                    {
                        String variableName = currentStatement[0];

                        return new EqualsExpression(variableName, new ConstantExpression(Double.parseDouble(currentStatement[2])));
                    }
                }
        }

        throw new IllegalArgumentException("Illegal Statement!");
    }


    public void execute() {
        while (currentLine < allStatements.length) {
            String lineStatement = allStatements[currentLine];  // all statements present in string delimited by \n
            String[] currentStatement = lineStatement.split("\\s+"); // all statements split word by word
            Expression currentExpression = parseExpression(allStatements, currentStatement); // evaluate each expression
            syntaxTree.add(currentExpression); // add to data structure
            currentLine++;
        }
    }
}
