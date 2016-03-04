package org.bromano.benscript.interpreter;

import org.bromano.benscript.emitter.EmitterException;
import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.lexer.LexerException;
import org.bromano.benscript.parser.ParserException;

public class Interpreter {

    public static void main(String[] args) throws LexerException, ParserException, EmitterException, EvaluatorException {

        BenScriptInterpreter interpreter = new BenScriptInterpreter();

        interpreter.startInterpreter();
    }
}
