package org.bromano.benscript.parser.parslets;

import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.nodes.Expression.Expression;
import org.bromano.benscript.parser.Parser;

public interface PrefixParslet {
    Expression parse(Parser parser, Lexeme lexeme);
}
