package org.bromano.benscript.parser.parslets;

import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.lexer.LexemeKind;
import org.bromano.benscript.nodes.expressions.CallExpression;
import org.bromano.benscript.nodes.expressions.Expression;
import org.bromano.benscript.parser.Parser;
import org.bromano.benscript.parser.ParserException;
import org.bromano.benscript.parser.Precedence;
import java.util.ArrayList;

public class CallParslet implements InfixParslet {

    public Expression parse(Parser parser, Expression lhs, Lexeme lexeme) throws ParserException {

        CallExpression callExpression = new CallExpression();

        callExpression.function = lhs;

        callExpression.arguments = new ArrayList<>();

        if (!parser.isAMatch(LexemeKind.CloseParen)) {

            callExpression.arguments.add(parser.parseExpression(0));
        }

        while (parser.isAMatch(LexemeKind.Comma)) {
            parser.match(LexemeKind.Comma);

            callExpression.arguments.add(parser.parseExpression(0));
        }

        parser.match(LexemeKind.CloseParen);

        return callExpression;
    }

    public int getPrecedence() {
        return Precedence.CALL;
    }
}
