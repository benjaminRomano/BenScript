package org.bromano.benscript.evaluator.primaries;

import org.bromano.benscript.evaluator.BenScriptArray;
import org.bromano.benscript.evaluator.EvaluatorException;

import java.util.Iterator;

public class ArrayPrimary extends BasePrimary<BenScriptArray> {

    private BenScriptArray bsArray;


    public ArrayPrimary(BenScriptArray bsArray) {

        this.type = PrimaryType.Array;
        this.bsArray = bsArray;
    }

    @Override
    public BenScriptArray getValue() throws EvaluatorException {
        return this.bsArray;
    }

    @Override
    public BooleanPrimary castToBoolean() throws EvaluatorException {
        return new BooleanPrimary(true);
    }

    @Override
    public StringPrimary castToString() throws EvaluatorException {
        StringBuilder sb = new StringBuilder();

        sb.append('[');

        Iterator<Primary> iterator = this.bsArray.values.iterator();

        while (iterator.hasNext()) {

            sb.append(iterator.next().castToString().getValue());

            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }

        sb.append("]");

        return new StringPrimary(sb.toString());
    }

    @Override
    public Primary or(Primary rhs) throws EvaluatorException {
        return this.castToBoolean().or(rhs.castToBoolean());
    }

    @Override
    public Primary and(Primary rhs) throws EvaluatorException {
        return this.castToBoolean().and(rhs.castToBoolean());
    }

    @Override
    public Primary index(Primary index) throws EvaluatorException {
        return this.bsArray.getValue(index.castToInteger().getValue());
    }

    @Override
    public Primary equals(Primary rhs) throws EvaluatorException {
        return new BooleanPrimary(this.bsArray.values.equals(rhs.getValue()));
    }

    @Override
    public Primary optional() throws EvaluatorException {
        return new OptionalPrimary<>(this);
    }

    @Override
    public Primary not() throws EvaluatorException {
        return new BooleanPrimary(false);
    }
}
