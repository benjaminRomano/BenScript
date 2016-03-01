package org.bromano.benscript.evaluator.builtins;

import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.complexTypes.BenScriptBuiltinLambda;
import org.bromano.benscript.evaluator.primaries.*;

import java.lang.reflect.Array;
import java.util.List;

public class LengthLambda extends BenScriptBuiltinLambda {

    public LengthLambda() {

        this.paramNames = null;
    }

    @Override
    public Primary evaluate(List<Primary> args) throws EvaluatorException {

        if (args.size() > 1) {
           throw new EvaluatorException("Too many arguments supplied.");
        } else if (args.size() < 1) {
            throw new EvaluatorException("Not enough arguments supplied.");
        }

        Primary p = args.get(0);

        if (p instanceof ArrayPrimary) {
            return new IntegerPrimary(((ArrayPrimary) p).getValue().values.size());
        }

        throw new EvaluatorException("Cannot use length on type: " + p.getType());
    }
}
