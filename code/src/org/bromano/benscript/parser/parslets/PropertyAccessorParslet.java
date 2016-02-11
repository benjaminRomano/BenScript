package org.bromano.benscript.parser.parslets;

import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.lexer.LexemeKind;
import org.bromano.benscript.nodes.expressions.PropertyAccessorExpression;
import org.bromano.benscript.nodes.expressions.Expression;
import org.bromano.benscript.parser.Parser;
import org.bromano.benscript.parser.ParserException;
import org.bromano.benscript.parser.Precedence;

public class PropertyAccessorParslet implements InfixParslet {

    public Expression parse(Parser parser, Expression lhs, Lexeme lexeme) throws ParserException {

        PropertyAccessorExpression propertyAccessorExpression = new PropertyAccessorExpression();

        propertyAccessorExpression.expression = lhs;

        propertyAccessorExpression.property = parser.match(LexemeKind.Identifier);

        return propertyAccessorExpression;
    }

    public int getPrecedence() {
        return Precedence.POSTFIX;
    }
}
