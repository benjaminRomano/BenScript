package org.bromano.benscript.parser.parslets;

import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.lexer.LexemeKind;
import org.bromano.benscript.nodes.expressions.AccessorExpression;
import org.bromano.benscript.nodes.expressions.Expression;
import org.bromano.benscript.parser.Parser;
import org.bromano.benscript.parser.ParserException;
import org.bromano.benscript.parser.Precedence;

public class AccessorParslet implements InfixParslet {

    public Expression parse(Parser parser, Expression lhs, Lexeme lexeme) throws ParserException {

        AccessorExpression accessorExpression = new AccessorExpression();

        accessorExpression.expression = lhs;

        accessorExpression.accessorExpression = parser.parseExpression(0);

        parser.match(LexemeKind.CloseBracket);

        return accessorExpression;
    }

    public int getPrecedence() {
        return Precedence.POSTFIX;
    }
}
