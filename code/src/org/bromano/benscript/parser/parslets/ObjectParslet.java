package org.bromano.benscript.parser.parslets;

import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.lexer.LexemeKind;
import org.bromano.benscript.nodes.expressions.Expression;
import org.bromano.benscript.nodes.expressions.ObjectExpression;
import org.bromano.benscript.nodes.expressions.UnaryExpression;
import org.bromano.benscript.parser.Parser;
import org.bromano.benscript.parser.ParserException;
import org.bromano.benscript.parser.Precedence;

import java.util.HashMap;

public class ObjectParslet implements PrefixParslet {

    public Expression parse(Parser parser, Lexeme lexeme) throws ParserException {

        ObjectExpression objectExpression = new ObjectExpression();

        objectExpression.keyValues = new HashMap<>();

        if (!parser.isAMatch(LexemeKind.CloseBrace)) {
            lexeme = parser.match(LexemeKind.Identifier);
            parser.match(LexemeKind.Colon);
            objectExpression.keyValues.put(lexeme, parser.parseExpression(0));
        }

        while (parser.isAMatch(LexemeKind.Comma)) {
            parser.match(LexemeKind.Comma);
            lexeme = parser.match(LexemeKind.Identifier);
            parser.match(LexemeKind.Colon);
            objectExpression.keyValues.put(lexeme, parser.parseExpression(0));
        }

        parser.match(LexemeKind.CloseBrace);

        return objectExpression;
    }
}
