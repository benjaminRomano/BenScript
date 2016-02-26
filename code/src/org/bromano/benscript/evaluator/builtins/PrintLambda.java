package org.bromano.benscript.evaluator.builtins;

import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.complexTypes.BenScriptBuiltinLambda;
import org.bromano.benscript.evaluator.primaries.NullPrimary;
import org.bromano.benscript.evaluator.primaries.Primary;

import java.util.List;

public class PrintLambda extends BenScriptBuiltinLambda {

    public PrintLambda() {

        this.paramNames = null;
    }

    @Override
    public Primary evaluate(List<Primary> args) throws EvaluatorException {
        StringBuilder sb = new StringBuilder();

        for (Primary p : args) {
            sb.append(p.castToString().getValue());
        }

        System.out.print(sb);

        return new NullPrimary();
    }
}
