package org.bromano.benscript.nodes.statements;

import org.bromano.benscript.nodes.expressions.Expression;

public class ForStatement implements Statement {
    public VariableDeclarationStatement initializationStatement;
    public Expression conditionalStatement;
    public Expression incrementalExpression;
    public Statement statement;
}
