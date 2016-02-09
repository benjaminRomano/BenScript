package org.bromano.benscript.parser;

import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.nodes.expressions.Expression;
import org.bromano.benscript.nodes.Program;

import java.util.List;

public interface Parser {
    void setLexemeStream(List<Lexeme> lexemes);
    Program parse() throws ParserException;
    Expression parseExpression(int precedence) throws ParserException;
}
