package org.bromano.benscript.evaluator.primaries;


import org.bromano.benscript.evaluator.EvaluatorException;

public class NullPrimary extends BasePrimary<Object> {
    Object value;

    public NullPrimary() {
        this.type = PrimaryType.Null;
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public StringPrimary castToString() throws EvaluatorException {
        return new StringPrimary("null");
    }

    @Override
    public BooleanPrimary castToBoolean() throws EvaluatorException {
        return new BooleanPrimary(false);
    }

    @Override
    public Primary or(Primary rhs) throws EvaluatorException {
        return rhs.castToBoolean();
    }

    @Override
    public Primary and(Primary rhs) throws EvaluatorException {
        return new BooleanPrimary(false);
    }

    @Override
    public Primary optional() throws EvaluatorException {
        return new OptionalPrimary<>(this);
    }

    @Override
    public Primary not() throws EvaluatorException {
        return new BooleanPrimary(true);
    }
}
