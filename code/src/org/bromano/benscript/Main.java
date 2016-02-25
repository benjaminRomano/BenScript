package org.bromano.benscript;

import org.bromano.benscript.emitter.BenScriptEmitter;
import org.bromano.benscript.emitter.Emitter;
import org.bromano.benscript.emitter.EmitterException;
import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.evaluator.EvaluatorException;
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

    public static void main(String[] args) throws LexerException, ParserException, EmitterException, EvaluatorException {

//        Lexer s = new BenScriptLexer("" +
//                "var a = 1 * 2 + a['hello'](arg1,arg2);\n" +
//                "var a = { a: 5 + 5, b: 6 - 1 };\n" +
//                "var b = \"hel'lo\";\n" +
//                "(a,b) => { console.println('this should work'); };");

        Lexer s = new BenScriptLexer("" +
                "var a = { a: 5, b: {} };" +
                "a.a = 4;" +
                "a.b = {};" +
                "a.b.c = 1;" +
                "a.b.d?.e;" +
                "a['b']?.c;"
        );

        List<Lexeme> lexemeStream = s.getLexStream();

        lexemeStream.forEach(System.out::println);

        Parser p = new BenScriptParser(lexemeStream);

        Program program = p.parse();

        Emitter emitter = new BenScriptEmitter();

        System.out.println(emitter.emit(program));

        System.out.println(program.evaluate(new Environment()));

    }
}
