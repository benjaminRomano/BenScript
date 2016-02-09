package org.bromano.benscript.nodes.Statements;

import org.bromano.benscript.nodes.Expression.Expression;

import java.util.List;

public class WhileStatement implements Statement {
    public Expression conditional;
    public Statement statement;
}
