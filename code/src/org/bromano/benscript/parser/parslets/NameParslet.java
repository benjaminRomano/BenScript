package org.bromano.benscript.parser.parslets;

import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.nodes.expressions.Expression;
import org.bromano.benscript.nodes.expressions.NameExpression;
import org.bromano.benscript.nodes.expressions.UnaryExpression;
import org.bromano.benscript.parser.Parser;
import org.bromano.benscript.parser.ParserException;

public class NameParslet implements PrefixParslet {

    public NameParslet() {
    }

    public Expression parse(Parser parser, Lexeme lexeme) throws ParserException {

        NameExpression nameExpression = new NameExpression();

        nameExpression.name = lexeme;

        return nameExpression;
    }
}
