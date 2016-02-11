package org.bromano.benscript.nodes.expressions;

import org.bromano.benscript.lexer.Lexeme;

public class StringLiteralExpression implements Expression {
    public Lexeme string;
    public String quoteType;
}
