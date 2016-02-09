package org.bromano.benscript.parser;

import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.lexer.LexemeKind;
import org.bromano.benscript.nodes.expressions.Expression;
import org.bromano.benscript.nodes.Program;
import org.bromano.benscript.nodes.statements.Statement;

import java.util.List;

public interface Parser {
    void setLexemeStream(List<Lexeme> lexemes);
    Program parse() throws ParserException;
    Expression parseExpression(int precedence) throws ParserException;
    Statement parseStatement() throws ParserException;
    Lexeme match(LexemeKind kind) throws ParserException;
    Lexeme match() throws ParserException;
    boolean isAMatch(LexemeKind kind);
    boolean isAMatch(LexemeKind[] kinds);
    boolean isAMatch(Lexeme lexeme, LexemeKind kind);
    Lexeme lookAhead(int x);
}
