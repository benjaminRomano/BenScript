package org.bromano.benscript.parser.parslets;

import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.lexer.LexemeKind;
import org.bromano.benscript.nodes.expressions.ArrayExpression;
import org.bromano.benscript.nodes.expressions.Expression;
import org.bromano.benscript.nodes.expressions.GroupExpression;
import org.bromano.benscript.nodes.expressions.LambdaExpression;
import org.bromano.benscript.parser.Parser;
import org.bromano.benscript.parser.ParserException;

import java.util.ArrayList;

public class LambdaOrGroupParslet implements PrefixParslet {

    public Expression parse(Parser parser, Lexeme lexeme) throws ParserException {


        int x = 0;
        Lexeme lookahead = parser.lookAhead(0);

        // Go until close paren
        while (lookahead != null && lookahead.kind != LexemeKind.CloseParen) {
            x++;
            lookahead = parser.lookAhead(x);
        }

        if (lookahead == null) {
            throw new ParserException("Expected Close Paren in expression");
        }

        //Case of group expression
        lookahead = parser.lookAhead(x + 1);
        if (lookahead == null || lookahead.kind != LexemeKind.EqualsGreaterThan) {

            GroupExpression groupExpression = new GroupExpression();

            groupExpression.expression = parser.parseExpression(0);

            parser.match(LexemeKind.CloseParen);

            return groupExpression;
        }

        //Case of lambda
        LambdaExpression lambdaExpression  = new LambdaExpression();

        lambdaExpression.parameters = new ArrayList<>();

        if (!parser.isAMatch(LexemeKind.CloseParen)) {

            lambdaExpression.parameters.add(parser.match(LexemeKind.Identifier));
        }

        while (parser.isAMatch(LexemeKind.Comma)) {
            parser.match(LexemeKind.Comma);

            lambdaExpression.parameters.add(parser.match(LexemeKind.Identifier));
        }

        parser.match(LexemeKind.CloseParen);
        parser.match(LexemeKind.EqualsGreaterThan);

        lambdaExpression.statement = parser.parseStatement();

        return lambdaExpression;
    }
}
