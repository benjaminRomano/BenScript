package org.bromano.benscript.nodes.expressions;

import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.nodes.statements.Statement;

import java.util.List;

public class LambdaExpression implements Expression {
    public List<Lexeme> parameters;
    public Statement statement;

}
