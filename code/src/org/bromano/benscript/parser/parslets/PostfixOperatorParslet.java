package org.bromano.benscript.parser.parslets;

import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.nodes.expressions.Expression;
import org.bromano.benscript.nodes.expressions.PostfixExpression;
import org.bromano.benscript.parser.Parser;
import org.bromano.benscript.parser.ParserException;

public class PostfixOperatorParslet implements InfixParslet {

    private int precedence;

    public PostfixOperatorParslet(int precedence) {
        this.precedence = precedence;
    }

    public Expression parse(Parser parser, Expression lhs, Lexeme lexeme) throws ParserException {

        PostfixExpression postfixExpression = new PostfixExpression();

        postfixExpression.expression = lhs;
        postfixExpression.operator = lexeme;

        return postfixExpression;
    }

    public int getPrecedence() {
        return this.precedence;
    }
}
