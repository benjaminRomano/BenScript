package org.bromano.benscript.nodes.Expression;

import org.bromano.benscript.lexer.Lexeme;

import java.util.List;

public class CallExpression implements Expression {
    Expression  function;
    List<Expression> arguments;

}
