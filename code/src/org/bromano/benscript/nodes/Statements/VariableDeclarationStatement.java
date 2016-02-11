package org.bromano.benscript.nodes.statements;

import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.nodes.expressions.Expression;

public class VariableDeclarationStatement implements Statement {
    public Lexeme name;
    public ExpressionStatement statement;
}
