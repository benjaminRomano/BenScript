package org.bromano.benscript;

import org.bromano.benscript.lexer.BenScriptLexer;
import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.lexer.Lexer;
import org.bromano.benscript.lexer.LexerException;
import org.bromano.benscript.nodes.Program;
import org.bromano.benscript.parser.BenScriptParser;
import org.bromano.benscript.parser.Parser;
import org.bromano.benscript.parser.ParserException;

import java.util.List;

public class Main {

    public static void main(String[] args) throws LexerException, ParserException {

        Lexer s = new BenScriptLexer("var a = 1 * 2 + a['hello'](arg1,arg2);");
        List<Lexeme> lexemeStream = s.getLexStream();

        lexemeStream.forEach(System.out::println);

        Parser p = new BenScriptParser(lexemeStream);

        Program program = p.parse();


        program.statements.forEach((x) -> System.out.println(x.getClass().getName()));
    }
}
