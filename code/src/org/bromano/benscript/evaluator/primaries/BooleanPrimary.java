package org.bromano.benscript.evaluator.primaries;

import org.bromano.benscript.evaluator.EvaluatorException;

public class BooleanPrimary extends BasePrimary {

   private Boolean value;

    public BooleanPrimary(boolean value) {
        this.type = PrimaryType.Boolean;
        this.value = value;
    }

    public Boolean getValue() {
        return this.value;
    }

    @Override
    public StringPrimary castToString() throws EvaluatorException {
        return this.value ? new StringPrimary("true") : new StringPrimary("false");
    }

    @Override
    public BooleanPrimary castToBoolean() throws EvaluatorException {
        return super.castToBoolean();
    }

    @Override
    public Primary or(Primary rhs) throws EvaluatorException {
        return new BooleanPrimary(this.value || rhs.castToBoolean().getValue());
    }

    @Override
    public Primary and(Primary rhs) throws EvaluatorException {
        return new BooleanPrimary(this.value && rhs.castToBoolean().getValue());
    }

    @Override
    public Primary optional() throws EvaluatorException {
        return new OptionalPrimary(this);
    }

    @Override
    public Primary not() throws EvaluatorException {
        return new BooleanPrimary(!this.value);
    }

    @Override
    public Primary equals(Primary rhs) throws EvaluatorException {
        return new BooleanPrimary(this.value == rhs.castToBoolean().getValue());
    }
}
