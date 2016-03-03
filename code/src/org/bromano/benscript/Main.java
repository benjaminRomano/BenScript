package org.bromano.benscript;

import org.bromano.benscript.emitter.EmitterException;
import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.lexer.BenScriptLexer;
import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.lexer.LexerException;
import org.bromano.benscript.nodes.Program;
import org.bromano.benscript.parser.BenScriptParser;
import org.bromano.benscript.parser.ParserException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws LexerException, ParserException, EmitterException, EvaluatorException, URISyntaxException, IOException {

        String code = getFileContent("tests/programs/dictionary.bs");

        code = loadLibraries() + code;

        List<Lexeme> lexemes = new BenScriptLexer(code).getLexStream();
        Program program = new BenScriptParser(lexemes).parse();
        program.evaluate(Environment.createGlobalEnvironment()).castToString().getValue();

    }

    private static String getFileContent(String fileName) throws URISyntaxException, IOException {

        Path path = Paths.get(Main.class.getResource(fileName).toURI());

        return Files.readAllLines(path).stream().reduce("", (s, s2) -> s + "\n" + s2);
    }

    public static String loadLibraries() throws URISyntaxException, IOException {
        Path path = Paths.get(Main.class.getResource("libraries/dictionary.lib.bs").toURI());
        String libraries = Files.readAllLines(path).stream().reduce("", (s, s2) -> s + "\n" + s2);
        libraries = libraries + "\n";

        return libraries;
    }
}
