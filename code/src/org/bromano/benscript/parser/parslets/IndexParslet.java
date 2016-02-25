package org.bromano.benscript.parser.parslets;

import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.lexer.LexemeKind;
import org.bromano.benscript.nodes.expressions.IndexExpression;
import org.bromano.benscript.nodes.expressions.Expression;
import org.bromano.benscript.parser.Parser;
import org.bromano.benscript.parser.ParserException;
import org.bromano.benscript.parser.Precedence;

public class IndexParslet implements InfixParslet {

    public Expression parse(Parser parser, Expression lhs, Lexeme lexeme) throws ParserException {

        IndexExpression indexExpression = new IndexExpression();

        indexExpression.expression = lhs;

        indexExpression.accessorExpression = parser.parseExpression(0);

        parser.match(LexemeKind.CloseBracket);

        return indexExpression;
    }

    public int getPrecedence() {
        return Precedence.POSTFIX;
    }
}
