package org.bromano.benscript.evaluator.builtins;

import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.complexTypes.BenScriptBasicObject;
import org.bromano.benscript.evaluator.complexTypes.BenScriptBuiltinObject;
import org.bromano.benscript.evaluator.complexTypes.BenScriptObject;
import org.bromano.benscript.evaluator.primaries.LambdaPrimary;
import org.bromano.benscript.evaluator.primaries.NullPrimary;
import org.bromano.benscript.evaluator.primaries.Primary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Console extends BenScriptBuiltinObject {

    public Console() {

        this.values = new HashMap<>();
        this.values.put("print", new LambdaPrimary(new PrintLambda()));
        this.values.put("println", new LambdaPrimary(new PrintlnLambda()));
    }
}
