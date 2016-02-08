package org.bromano.benscript;

import org.bromano.benscript.lexer.BenScriptLexer;
import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.lexer.Lexer;
import org.bromano.benscript.lexer.LexerException;

import java.util.List;

public class Main {

    public static void main(String[] args) throws LexerException {

        Lexer s = new BenScriptLexer("var a = 5;");
        List<Lexeme> lexemeStream = s.getLexStream();

        lexemeStream.forEach(System.out::println);
    }
}
