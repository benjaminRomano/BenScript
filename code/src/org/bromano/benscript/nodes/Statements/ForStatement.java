package org.bromano.benscript.nodes.Statements;

import org.bromano.benscript.nodes.Expression.Expression;

public class ForStatement implements Statement {
    public VariableDeclarationStatement initializationStatement;
    public Expression conditionalStatement;
    public Expression incrementalExpression;
    public Statement statement;
}
