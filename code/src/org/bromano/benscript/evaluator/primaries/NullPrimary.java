package org.bromano.benscript.evaluator.primaries;


import org.bromano.benscript.evaluator.EvaluatorException;

public class NullPrimary extends BasePrimary {
    Object value;

    public NullPrimary() {
        this.type = PrimaryType.Null;
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
    public Primary equals(Primary rhs) throws EvaluatorException {
        return new BooleanPrimary(rhs.getPrimary() instanceof NullPrimary);
    }

    @Override
    public Primary notEquals(Primary rhs) throws EvaluatorException {
        return this.equals(rhs).not();
    }

    @Override
    public Primary optional() throws EvaluatorException {
        return new OptionalPrimary(this);
    }

    @Override
    public Primary not() throws EvaluatorException {
        return new BooleanPrimary(true);
    }
}
