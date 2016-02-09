package org.bromano.benscript.nodes.Expression;

import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.nodes.Statements.Statement;

import java.util.List;

public class LambdaExpression {
    public List<Lexeme> parameters;
    public List<Statement> statements;

}
