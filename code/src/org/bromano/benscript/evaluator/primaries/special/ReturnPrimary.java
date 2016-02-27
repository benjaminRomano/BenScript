package org.bromano.benscript.evaluator.primaries.special;

import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.BasePrimary;
import org.bromano.benscript.evaluator.primaries.Primary;
import org.bromano.benscript.evaluator.primaries.PrimaryType;

public class ReturnPrimary extends BasePrimary {

    Primary value;

    public ReturnPrimary(Primary value) {

        this.type = PrimaryType.Return;
        this.value = value;
    }

    public Primary getValue() throws EvaluatorException {

        return value;
    }
}
