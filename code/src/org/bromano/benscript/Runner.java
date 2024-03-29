package org.bromano.benscript;

import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.lexer.BenScriptLexer;
import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.nodes.Program;
import org.bromano.benscript.parser.BenScriptParser;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.List;

public class Runner {

    public static void main(String[] args) throws Exception {

        boolean loadWithLibraries = false;

        // TODO: Verify works
        if (args.length >= 1) {

            if (args.length == 2) {
                loadWithLibraries = args[1].equals("--libs");
            }

            run(loadFile(args[0]), loadWithLibraries);
            return;
        }

        run(readFromSystemIn(), false);
    }

    public static String loadFile(String filePath) throws Exception {
       return BenScriptLibraryLoader.readStream(new BufferedInputStream(new FileInputStream(filePath)));
    }

    public static String readFromSystemIn() {
        java.util.Scanner systemInScanner = new java.util.Scanner(System.in);

        StringBuilder inputStringBuilder = new StringBuilder();
        while(systemInScanner.hasNext()) {
            inputStringBuilder.append(systemInScanner.nextLine());
            inputStringBuilder.append("\n");
        }

        return inputStringBuilder.toString();
    }

    public static void run(String code, boolean loadWithLibraries) throws Exception {

        if (loadWithLibraries) {
            code = BenScriptLibraryLoader.loadLibraries() + code;
        }

        List<Lexeme> lexemes = new BenScriptLexer(code).getLexStream();
        Program program = new BenScriptParser(lexemes).parse();
        program.evaluate(Environment.createGlobalEnvironment()).castToString().getValue();
    }
}
