package org.bromano.benscript.nodes.Expression;

import org.bromano.benscript.lexer.Lexeme;

public class BinaryExpression implements Expression {
    public Expression left;
    public Lexeme operator;
    public Expression right;
}
