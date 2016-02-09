package org.bromano.benscript.nodes.statements;
import org.bromano.benscript.lexer.Lexeme;

import java.util.List;

public class FunctionDeclarationStatement implements Statement {
    public Lexeme name;
    public List<Lexeme> parameters;
    public Statement statement;

}
