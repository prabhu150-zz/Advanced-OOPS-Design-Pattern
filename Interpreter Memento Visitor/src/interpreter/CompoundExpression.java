package interpreter;

import visitor.Visitable;

public interface CompoundExpression extends Expression, Visitable {
// created to accomodate both constant and variable expressions uniformly
// it extends expression so we can use interpreter on both constants and variables
}
