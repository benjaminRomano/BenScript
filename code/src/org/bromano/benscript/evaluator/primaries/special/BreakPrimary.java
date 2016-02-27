package org.bromano.benscript.evaluator.primaries.special;

import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.BasePrimary;
import org.bromano.benscript.evaluator.primaries.Primary;
import org.bromano.benscript.evaluator.primaries.PrimaryType;

public class BreakPrimary extends BasePrimary {

    public BreakPrimary() {
        this.type = PrimaryType.Break;
    }
}
