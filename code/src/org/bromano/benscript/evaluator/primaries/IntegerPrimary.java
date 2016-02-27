package org.bromano.benscript.evaluator.primaries;

import org.bromano.benscript.evaluator.EvaluatorException;

public class IntegerPrimary extends BasePrimary {

    private Integer value;

    public IntegerPrimary(int value) {

        this.type = PrimaryType.Integer;
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

    @Override
    public IntegerPrimary castToInteger() throws EvaluatorException {
        return this;
    }

    @Override
    public StringPrimary castToString() throws EvaluatorException {
        return new StringPrimary(this.value.toString());
    }

    @Override
    public Primary add(Primary rhs) throws EvaluatorException {
        return new IntegerPrimary(this.value + rhs.castToInteger().getValue());
    }

    @Override
    public Primary subtract(Primary rhs) throws EvaluatorException {
        return new IntegerPrimary(this.value - rhs.castToInteger().getValue());
    }

    @Override
    public Primary multiply(Primary rhs) throws EvaluatorException {
        return new IntegerPrimary(this.value * rhs.castToInteger().getValue());
    }

    @Override
    public Primary divide(Primary rhs) throws EvaluatorException {
        return new IntegerPrimary(this.value / rhs.castToInteger().getValue());
    }

    @Override
    public Primary modulo(Primary rhs) throws EvaluatorException {
        return new IntegerPrimary(this.value % rhs.castToInteger().getValue());
    }

    @Override
    public Primary exponent(Primary rhs) throws EvaluatorException {
        return new IntegerPrimary((int) Math.pow(this.value, rhs.castToInteger().getValue()));
    }

    @Override
    public Primary lessThan(Primary rhs) throws EvaluatorException {
        return new BooleanPrimary(this.value < rhs.castToInteger().getValue());
    }

    @Override
    public Primary lessThanEquals(Primary rhs) throws EvaluatorException {
        return new BooleanPrimary(this.value <= rhs.castToInteger().getValue());
    }

    @Override
    public Primary greaterThan(Primary rhs) throws EvaluatorException {
        return new BooleanPrimary(this.value > rhs.castToInteger().getValue());
    }

    @Override
    public Primary greaterThanEquals(Primary rhs) throws EvaluatorException {
        return new BooleanPrimary(this.value >= rhs.castToInteger().getValue());
    }

    @Override
    public Primary equals(Primary rhs) throws EvaluatorException {
        return new BooleanPrimary(this.value.equals(rhs.castToInteger().getValue()));
    }

    @Override
    public Primary minus() throws EvaluatorException {
        return new IntegerPrimary(-this.value);
    }

    @Override
    public Primary optional() throws EvaluatorException {
        return new OptionalPrimary(this);
    }
}
