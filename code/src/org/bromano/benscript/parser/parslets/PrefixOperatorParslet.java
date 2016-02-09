package org.bromano.benscript.parser.parslets;

import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.nodes.expressions.Expression;
import org.bromano.benscript.nodes.expressions.UnaryExpression;
import org.bromano.benscript.parser.Parser;
import org.bromano.benscript.parser.ParserException;

public class PrefixOperatorParslet implements PrefixParslet {

    private int precedence;

    public PrefixOperatorParslet(int precedence) {
        this.precedence = precedence;
    }

    public Expression parse(Parser parser, Lexeme lexeme) throws ParserException {

        UnaryExpression unaryExpression = new UnaryExpression();

        unaryExpression.operator = lexeme;
        unaryExpression.expression = parser.parseExpression(this.precedence);

        return unaryExpression;
    }
}
