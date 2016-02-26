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

        Lexer s = new BenScriptLexer("" +
                "func printOut(value) { console.println('the value passed in is ', value); }" +
                "var variableName123 = true;" +
                "var a = { a: 5, b: {} };" +
                "a.a = 4;" +
                "a.b = {};" +
                "a.b.c = 1;" +
                "a.b.d?.e?.f;" +
                "console.println('a.b is ', a.b);" +
                "a['b']?.c;" +
                "console.println((2 ^ 1 ^ 3 + 5 - 1 * 0) % 7 == 0);" +
                "var a = (b) => console.println(b);" +
                "a('hello');" +
                "printOut(null);"
        );

        List<Lexeme> lexemeStream = s.getLexStream();

//        System.out.println("Lexemes:");
//        System.out.println("--------");
//        lexemeStream.forEach(System.out::println);
//        System.out.println();

        Parser p = new BenScriptParser(lexemeStream);

        Program program = p.parse();

        System.out.println("Formatted Code: ");
        System.out.println("---------------");
        Emitter emitter = new BenScriptEmitter();
        System.out.println();

        System.out.println(emitter.emit(program));

        System.out.println("Output:");
        System.out.println("-------");
        program.evaluate(Environment.createGlobalEnvironment());
    }
}
