package org.bromano.benscript.evaluator.complexTypes;

import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.Primary;

import java.util.List;

public abstract class BenScriptBuiltinLambda implements BenScriptLambda {

    protected List<String> paramNames;

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof BenScriptBuiltinLambda)) {
            return false;
        }

        BenScriptBuiltinLambda bsLambda = (BenScriptBuiltinLambda) obj;

        return bsLambda.paramNames.equals(this.paramNames);
    }

    @Override
    public List<String> getParamNames() {
        return this.paramNames;
    }
}
