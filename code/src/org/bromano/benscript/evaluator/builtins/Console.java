package org.bromano.benscript.evaluator.builtins;

import org.bromano.benscript.evaluator.complexTypes.BenScriptBasicObject;
import org.bromano.benscript.evaluator.primaries.LambdaPrimary;

import java.util.HashMap;

public class Console extends BenScriptBasicObject {

    public Console() {
        super();

        this.values.put("print", new LambdaPrimary(new PrintLambda()));
        this.values.put("println", new LambdaPrimary(new PrintlnLambda()));
    }
}
