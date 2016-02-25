package org.bromano.benscript.nodes;

import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.Primary;

public interface ParserNode {
    Primary evaluate(Environment context) throws EvaluatorException;
}
