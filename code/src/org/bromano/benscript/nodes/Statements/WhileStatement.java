package org.bromano.benscript.nodes.statements;

import org.bromano.benscript.nodes.expressions.Expression;

public class WhileStatement implements Statement {
    public Expression conditional;
    public Statement statement;
}
