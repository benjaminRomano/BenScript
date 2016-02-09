package org.bromano.benscript.nodes.statements;

import org.bromano.benscript.nodes.expressions.Expression;

public class IfStatement implements Statement {
    public Expression conditional;
    public Statement statement;
    public Statement elseStatement;
}
