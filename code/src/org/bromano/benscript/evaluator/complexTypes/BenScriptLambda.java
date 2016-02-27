package org.bromano.benscript.evaluator.complexTypes;

import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.Primary;

import java.util.List;

public interface BenScriptLambda {
    Primary evaluate(List<Primary> args) throws EvaluatorException;
    List<String> getParamNames();
}
