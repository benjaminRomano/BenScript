package org.bromano.benscript.nodes.Expression;

import org.bromano.benscript.lexer.Lexeme;

public class UnaryExpression implements Expression {
    public Lexeme operator;
    public Expression expression;
}
