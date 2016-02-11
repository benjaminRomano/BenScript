package org.bromano.benscript.nodes.expressions;

import org.bromano.benscript.lexer.Lexeme;

public class PropertyAccessorExpression implements Expression {
    public Expression expression;
    public Lexeme property;
}
