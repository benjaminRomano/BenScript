package org.bromano.benscript.nodes.expressions;

import org.bromano.benscript.lexer.Lexeme;

import java.util.Map;

public class ObjectExpression  implements Expression {
    public Map<Lexeme, Expression> keyValues;
}
