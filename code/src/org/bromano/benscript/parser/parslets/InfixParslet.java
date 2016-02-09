package org.bromano.benscript.parser.parslets;

import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.nodes.expressions.Expression;
import org.bromano.benscript.parser.Parser;
import org.bromano.benscript.parser.ParserException;

public interface InfixParslet {
    Expression parse(Parser parser, Expression lhs, Lexeme lexeme) throws ParserException;
    int getPrecedence();
}
