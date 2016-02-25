package org.bromano.benscript.parser.parslets;

import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.nodes.expressions.Expression;
import org.bromano.benscript.nodes.expressions.IdentifierExpression;
import org.bromano.benscript.parser.Parser;
import org.bromano.benscript.parser.ParserException;

public class IdentifierParslet implements PrefixParslet {

    public IdentifierParslet() {
    }

    public Expression parse(Parser parser, Lexeme lexeme) throws ParserException {

        IdentifierExpression identifierExpression = new IdentifierExpression();

        identifierExpression.name = lexeme;

        return identifierExpression;
    }
}
