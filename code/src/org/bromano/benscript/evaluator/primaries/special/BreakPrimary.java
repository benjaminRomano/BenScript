package org.bromano.benscript.evaluator.primaries.special;

import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.BasePrimary;
import org.bromano.benscript.evaluator.primaries.Primary;
import org.bromano.benscript.evaluator.primaries.PrimaryType;

public class BreakPrimary extends BasePrimary<Object> {

    Object value;

    public BreakPrimary() {
        this.type = PrimaryType.Break;
        this.value = null;
    }

    @Override
    public Object getValue() throws EvaluatorException {

        return this.value;
    }
}
