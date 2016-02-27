package org.bromano.benscript.evaluator.complexTypes;

import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.Primary;

import java.util.List;

public class BenScriptArray {

    public List<Primary> values;

    public BenScriptArray(List<Primary> values) {
        this.values = values;
    }

    public Primary getValue(int index) throws EvaluatorException {
        if (index >= values.size()) {
            throw new EvaluatorException("Index " + index + " outside of array bounds");
        }

        return this.values.get(index);
    }

    public void push(Primary value) {
        this.values.add(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BenScriptArray)) {
            return false;
        }

        BenScriptArray bsObject = (BenScriptArray) obj;

        return bsObject.values.equals(this.values);
    }
}
