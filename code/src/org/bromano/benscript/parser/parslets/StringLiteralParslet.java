package org.bromano.benscript.parser.parslets;

import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.nodes.expressions.Expression;
import org.bromano.benscript.nodes.expressions.StringLiteralExpression;
import org.bromano.benscript.nodes.expressions.UnaryExpression;
import org.bromano.benscript.parser.Parser;
import org.bromano.benscript.parser.ParserException;

public class StringLiteralParslet implements PrefixParslet {

    public Expression parse(Parser parser, Lexeme lexeme) throws ParserException {

        StringLiteralExpression stringLiteralExpression = new StringLiteralExpression();

        stringLiteralExpression.string = lexeme;

        return stringLiteralExpression;
    }
}
