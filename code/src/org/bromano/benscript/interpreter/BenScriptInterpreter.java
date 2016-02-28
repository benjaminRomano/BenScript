package org.bromano.benscript.interpreter;

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
import java.util.Scanner;

public class BenScriptInterpreter {
    private Lexer lexer;
    private Parser parser;
    private Environment global;

    public BenScriptInterpreter() {
        this.lexer = new BenScriptLexer();
        this.parser = new BenScriptParser();
    }

    public void startInterpreter() throws EvaluatorException, ParserException, LexerException {
        Scanner scanner = new Scanner(System.in);
        String text;

        System.out.println("BenScript BenScriptInterpreter");
        System.out.println("---------------------");
        System.out.println("Enter quit or exit to stop");

        this.global = Environment.createGlobalEnvironment();

        while (true) {
            System.out.print("> ");
            text = scanner.nextLine();

            if (text.equals("quit") || text.equals("exit")) {
                return;
            }

            try {
                this.interpret(text);
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    public void interpret(String code) throws LexerException, ParserException, EvaluatorException {
        this.lexer.setText(code);
        List<Lexeme> lexemes = this.lexer.getLexStream();
        this.parser.setLexemeStream(lexemes);

        Program p = this.parser.parse();

        System.out.println(p.evaluate(this.global).castToString().getValue());
    }
}
