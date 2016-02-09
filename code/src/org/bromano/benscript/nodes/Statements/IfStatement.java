package org.bromano.benscript.nodes.Statements;

import org.bromano.benscript.nodes.Expression.Expression;

import java.util.List;

public class IfStatement implements Statement {
    public Expression conditional;
    public Statement statement;
    public Statement elseStatement;
}
