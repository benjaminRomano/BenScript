package org.bromano.benscript.tests;

import org.bromano.benscript.emitter.BenScriptEmitter;
import org.bromano.benscript.emitter.Emitter;
import org.bromano.benscript.emitter.EmitterException;
import org.bromano.benscript.lexer.BenScriptLexer;
import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.lexer.Lexer;
import org.bromano.benscript.lexer.LexerException;
import org.bromano.benscript.nodes.Program;
import org.bromano.benscript.parser.BenScriptParser;
import org.bromano.benscript.parser.Parser;
import org.bromano.benscript.parser.ParserException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class BenScriptParserTest {

    @Test
    public void testParse() throws LexerException, EmitterException, ParserException {

        this.assertParse("var a = 5;", "var a = 5;\n");

        this.assertParse("(1 + 2) * 5 + 6;", "(1 + 2) * 5 + 6;\n");

        //TODO: Add more test cases

    }

    private void assertParse(String input, String expected) throws EmitterException, LexerException, ParserException {

        Lexer lexer = new BenScriptLexer(input);

        List<Lexeme> lexemes = lexer.getLexStream();

        Parser parser = new BenScriptParser(lexemes);

        Program p = parser.parse();

        Emitter emitter = new BenScriptEmitter();

        String actual = emitter.emit(p);

        assertEquals(expected, actual);
    }
}