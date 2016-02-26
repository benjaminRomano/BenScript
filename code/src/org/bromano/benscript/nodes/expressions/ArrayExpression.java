package org.bromano.benscript.nodes.expressions;

import org.bromano.benscript.evaluator.complexTypes.BenScriptArray;
import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.ArrayPrimary;
import org.bromano.benscript.evaluator.primaries.Primary;

import java.util.ArrayList;
import java.util.List;

public class ArrayExpression implements Expression {
    public List<Expression> values;

    @Override
    public Primary evaluate(Environment context) throws EvaluatorException {

        List<Primary> primaries = new ArrayList<>();

        for (Expression value : this.values) {
            primaries.add(value.evaluate(context));
        }

        return new ArrayPrimary(new BenScriptArray(primaries));
    }
}
