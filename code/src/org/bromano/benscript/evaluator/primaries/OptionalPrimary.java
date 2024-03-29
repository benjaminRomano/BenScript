package org.bromano.benscript.evaluator.primaries;

import org.bromano.benscript.evaluator.EvaluatorException;

import java.util.List;

public class OptionalPrimary extends BasePrimary {

    private Primary value;

    public OptionalPrimary(Primary value) throws EvaluatorException {

        this.value = value;
        this.type = value.getType();
    }

    @Override
    public StringPrimary castToString() throws EvaluatorException {
        return this.value.castToString();
    }

    @Override
    public BooleanPrimary castToBoolean() throws EvaluatorException {
            return this.value.castToBoolean();
    }

    @Override
    public IntegerPrimary castToInteger() throws EvaluatorException {
        return this.value.castToInteger();
    }

    @Override
    public Primary add(Primary rhs) throws EvaluatorException {
        return this.value.add(rhs);
    }

    @Override
    public Primary subtract(Primary rhs) throws EvaluatorException {
        return this.value.subtract(rhs);
    }

    @Override
    public Primary multiply(Primary rhs) throws EvaluatorException {
        return this.value.multiply(rhs);
    }

    @Override
    public Primary divide(Primary rhs) throws EvaluatorException {
        return this.value.divide(rhs);
    }

    @Override
    public Primary modulo(Primary rhs) throws EvaluatorException {
        return this.value.modulo(rhs);
    }

    @Override
    public Primary exponent(Primary rhs) throws EvaluatorException {
        return this.value.exponent(rhs);
    }

    @Override
    public Primary or(Primary rhs) throws EvaluatorException {
        return this.value.or(rhs);
    }

    @Override
    public Primary and(Primary rhs) throws EvaluatorException {
        return this.value.and(rhs);
    }

    @Override
    public Primary lessThan(Primary rhs) throws EvaluatorException {
        return this.value.lessThan(rhs);
    }

    @Override
    public Primary greaterThan(Primary rhs) throws EvaluatorException {
        return this.value.greaterThan(rhs);
    }

    @Override
    public Primary lessThanEquals(Primary rhs) throws EvaluatorException {
        return this.value.lessThanEquals(rhs);
    }

    @Override
    public Primary greaterThanEquals(Primary rhs) throws EvaluatorException {
        return this.value.greaterThanEquals(rhs);
    }

    @Override
    public Primary equals(Primary rhs) throws EvaluatorException {
        return this.value.equals(rhs);
    }

    @Override
    public Primary index(Primary index) throws EvaluatorException {
        if (this.value instanceof NullPrimary) {
            return new NullPrimary();
        }

        return this.value.index(index);
    }

    @Override
    public Primary access(String prop) throws EvaluatorException {
        if (this.value.getType() == PrimaryType.Null) {
            return new NullPrimary();
        }

        return this.value.access(prop);
    }

    @Override
    public Primary call(List<Primary> args) throws EvaluatorException {
        if (this.value instanceof NullPrimary) {
            return new NullPrimary();
        }

        return this.value.call(args);
    }

    @Override
    public Primary optional() throws EvaluatorException {
        return new OptionalPrimary(this);
    }

    @Override
    public Primary minus() throws EvaluatorException {
        return this.value.minus();
    }

    @Override
    public Primary not() throws EvaluatorException {
        return this.value.not();
    }

    @Override
    public Primary assign(Primary rhs) throws EvaluatorException {
        return this.value.assign(rhs);
    }

    @Override
    public Primary getPrimary() throws EvaluatorException {
        return this.value.getPrimary();
    }
}
