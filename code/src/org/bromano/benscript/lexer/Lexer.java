package org.bromano.benscript.lexer;

import java.util.List;

public interface Lexer {
    Lexeme lex() throws LexerException;
    List<Lexeme> getLexStream() throws LexerException;
    void setText(String text);
}
