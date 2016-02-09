package org.bromano.benscript.nodes.Statements;

import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.nodes.Expression.Expression;

public class VariableDeclarationStatement implements Statement {
    public Lexeme name;
    public Expression expression;
}
