package org.bromano.benscript.parser.parslets;

import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.lexer.LexemeKind;
import org.bromano.benscript.nodes.expressions.ArrayExpression;
import org.bromano.benscript.nodes.expressions.Expression;
import org.bromano.benscript.nodes.expressions.ObjectExpression;
import org.bromano.benscript.parser.Parser;
import org.bromano.benscript.parser.ParserException;

import java.util.ArrayList;
import java.util.HashMap;

public class ArrayParslet implements PrefixParslet {

    public Expression parse(Parser parser, Lexeme lexeme) throws ParserException {

        ArrayExpression arrayExpression = new ArrayExpression();

        arrayExpression.values = new ArrayList<>();

        if (!parser.isAMatch(LexemeKind.CloseBracket)) {
            arrayExpression.values.add(parser.parseExpression(0));
        }

        while (parser.isAMatch(LexemeKind.Comma)) {
            parser.match(LexemeKind.Comma);
            arrayExpression.values.add(parser.parseExpression(0));
        }

        parser.match(LexemeKind.CloseBracket);

        return arrayExpression;
    }
}
