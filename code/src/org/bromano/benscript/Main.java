package org.bromano.benscript;

import org.bromano.benscript.emitter.EmitterException;
import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.interpreter.BenScriptInterpreter;
import org.bromano.benscript.lexer.LexerException;
import org.bromano.benscript.parser.ParserException;

public class Main {

    public static void main(String[] args) throws LexerException, ParserException, EmitterException, EvaluatorException {

        BenScriptInterpreter interpreter = new BenScriptInterpreter();

        interpreter.startInterpreter();
    }
}
