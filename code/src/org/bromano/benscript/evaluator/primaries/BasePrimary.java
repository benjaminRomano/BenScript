package org.bromano.benscript.evaluator.primaries;

import org.bromano.benscript.evaluator.EvaluatorException;

import java.util.List;

public abstract class BasePrimary<T> implements Primary<T> {

    protected PrimaryType type;

    public abstract T getValue() throws EvaluatorException;

    public PrimaryType getType() {
        return this.type;
    }

    public StringPrimary castToString() throws EvaluatorException {
        throw new EvaluatorException("Cannot cast " + type.name() + " to string");
    }

    public BooleanPrimary castToBoolean() throws EvaluatorException, EvaluatorException {
        throw new EvaluatorException("Cannot cast " + type.name() + " to boolean");
    }

    public IntegerPrimary castToInteger() throws EvaluatorException {
        throw new EvaluatorException("Cannot cast " + type.name() + " to integer");
    }

    public Primary add(Primary rhs) throws EvaluatorException {
        throw new EvaluatorException("Addition not supported by " + type.name());
    }

    public Primary subtract(Primary rhs) throws EvaluatorException {
        throw new EvaluatorException("Subtraction not supported by " + type.name());
    }

    public Primary multiply(Primary rhs) throws EvaluatorException {
        throw new EvaluatorException("Multiplication not supported by " + type.name());
    }

    public Primary divide(Primary rhs) throws EvaluatorException {
        throw new EvaluatorException("Division not supported by " + type.name());
    }

    public Primary modulo(Primary rhs) throws EvaluatorException {
        throw new EvaluatorException("Modulo not supported by " + type.name());
    }

    public Primary exponent(Primary rhs) throws EvaluatorException {
        throw new EvaluatorException("Exponentiation not supported by " + type.name());
    }

    public Primary or(Primary rhs) throws EvaluatorException {
        throw new EvaluatorException("Logical Or not supported by " + type.name());
    }

    public Primary and(Primary rhs) throws EvaluatorException {
        throw new EvaluatorException("Logical And not supported by " + type.name());
    }

    public Primary lessThan(Primary rhs) throws EvaluatorException {
        throw new EvaluatorException("Less than not supported by " + type.name());
    }

    public Primary lessThanEquals(Primary rhs) throws EvaluatorException {
        throw new EvaluatorException("Less Than or Equal not supported by " + type.name());
    }

    public Primary greaterThan(Primary rhs) throws EvaluatorException {
        throw new EvaluatorException("Greater than not supported by " + type.name());
    }

    public Primary greaterThanEquals(Primary rhs) throws EvaluatorException {
        throw new EvaluatorException("Greater than or equal not supported by " + type.name());
    }

    public Primary equals(Primary rhs) throws EvaluatorException {
        throw new EvaluatorException("Equal not supported by " + type.name());
    }

    public Primary notEquals(Primary rhs) throws EvaluatorException {
        try {
           return this.equals(rhs).not();
        } catch (EvaluatorException e) {
            throw new EvaluatorException("Not Equal not supported by " + type.name());
        }
    }

    public Primary index(Primary index) throws EvaluatorException {
        throw new EvaluatorException("Indexing access not supported by " + type.name());
    }

    public Primary access(String prop) throws EvaluatorException {
        throw new EvaluatorException("Property access not supported by " + type.name());
    }

    public Primary call(List<Primary> args) throws EvaluatorException {
        throw new EvaluatorException("Function call not supported by " + type.name());
    }

    public Primary optional() throws EvaluatorException {
        throw new EvaluatorException("Optional not supported by " + type.name());
    }

    public Primary minus() throws EvaluatorException {
        throw new EvaluatorException("Minus not supported by " + type.name());
    }

    public Primary not() throws EvaluatorException {
        throw new EvaluatorException("Not not supported by " + type.name());
    }

    @Override
    public String toString() {
        try {
            return this.type.name() + ": " + this.castToString().getValue();
        } catch (EvaluatorException e) {
            //Do nothing
        }

        return this.type.name();
    }
}

