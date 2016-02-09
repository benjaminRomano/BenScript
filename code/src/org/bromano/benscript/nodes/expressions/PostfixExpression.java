package org.bromano.benscript.nodes.expressions;

import org.bromano.benscript.lexer.Lexeme;

public class PostfixExpression implements Expression {
    public Expression expression;
    public Lexeme operator;
}
