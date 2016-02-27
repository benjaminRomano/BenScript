package org.bromano.benscript.evaluator.primaries;

import org.bromano.benscript.evaluator.EvaluatorException;

public class StringPrimary extends BasePrimary {

    private String value;

    public StringPrimary(String value) {
        this.type = PrimaryType.String;
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public BooleanPrimary castToBoolean() throws EvaluatorException {
        return new BooleanPrimary(this.value != null);
    }

    @Override
    public StringPrimary castToString() throws EvaluatorException {
        return this;
    }

    @Override
    public Primary add(Primary rhs) throws EvaluatorException {
        return new StringPrimary(this.value + rhs.castToString().getValue());
    }

    @Override
    public Primary or(Primary rhs) throws EvaluatorException {
        return this.castToBoolean().or(rhs);
    }

    @Override
    public Primary and(Primary rhs) throws EvaluatorException {
        return this.castToBoolean().and(rhs);
    }

    @Override
    public Primary equals(Primary rhs) throws EvaluatorException {
        return new BooleanPrimary(this.value.equals(rhs.castToString().getValue()));
    }

    @Override
    public Primary optional() throws EvaluatorException {
        return new OptionalPrimary(this);
    }

    @Override
    public Primary not() throws EvaluatorException {
        return new BooleanPrimary(this.value == null);
    }

    @Override
    public Primary multiply(Primary rhs) throws EvaluatorException {
        StringBuilder s = new StringBuilder();
        int count = rhs.castToInteger().getValue();

        for (int i = 0; i < count; i++) {
            s.append(this.value);
        }

        return new StringPrimary(s.toString());
    }
}
