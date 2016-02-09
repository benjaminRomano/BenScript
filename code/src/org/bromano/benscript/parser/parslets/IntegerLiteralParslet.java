package org.bromano.benscript.parser.parslets;

import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.nodes.expressions.BooleanLiteralExpression;
import org.bromano.benscript.nodes.expressions.Expression;
import org.bromano.benscript.nodes.expressions.IntegerLiteralExpression;
import org.bromano.benscript.parser.Parser;
import org.bromano.benscript.parser.ParserException;

public class IntegerLiteralParslet implements PrefixParslet {

    public Expression parse(Parser parser, Lexeme lexeme) throws ParserException {

        IntegerLiteralExpression integerLiteralExpression = new IntegerLiteralExpression();

        integerLiteralExpression.integer = lexeme;

        return integerLiteralExpression;
    }
}
