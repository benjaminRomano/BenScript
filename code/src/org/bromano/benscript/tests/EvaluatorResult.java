package org.bromano.benscript.tests;

import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.Primary;

public class EvaluatorResult {

    public String output;
    public Primary value;

    public EvaluatorResult(String output, Primary value) {
        this.output = output;
        this.value = value;
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof EvaluatorResult)) {
            return false;
        }

        EvaluatorResult er = (EvaluatorResult) obj;

        try {
            return this.output.equals(er.output) && this.value.castToString().equals(er.value).castToBoolean().getValue();
        } catch (EvaluatorException e) {
            return false;
        }
    }
}
