package interpreter;

import visitor.Visitable;
import visitor.Visitor;

public interface Expression extends Visitable {
    Double interpret(Context context);

    void accept(Visitor visitor);
}
