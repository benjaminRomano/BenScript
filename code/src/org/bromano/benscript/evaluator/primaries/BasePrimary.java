package org.bromano.benscript.evaluator.primaries;

import org.bromano.benscript.evaluator.EvaluatorException;

import java.util.List;

public abstract class BasePrimary implements Primary {

    protected PrimaryType type;

    public PrimaryType getType() {
        return this.type;
    }

    public StringPrimary castToString() throws EvaluatorException {
        throw new EvaluatorException("Cannot cast " + this.getType().name() + " to string");
    }

    public BooleanPrimary castToBoolean() throws EvaluatorException {
        throw new EvaluatorException("Cannot cast " + this.getType().name() + " to boolean");
    }

    public IntegerPrimary castToInteger() throws EvaluatorException {
        throw new EvaluatorException("Cannot cast " + this.getType().name() + " to integer");
    }

    @Override
    public Primary getPrimary() {
        return this;
    }

    public Primary add(Primary rhs) throws EvaluatorException {
        throw new EvaluatorException("Addition not supported by " + this.getType().name());
    }

    public Primary subtract(Primary rhs) throws EvaluatorException {
        throw new EvaluatorException("Subtraction not supported by " + this.getType().name());
    }

    public Primary multiply(Primary rhs) throws EvaluatorException {
        throw new EvaluatorException("Multiplication not supported by " + this.getType().name());
    }

    public Primary divide(Primary rhs) throws EvaluatorException {
        throw new EvaluatorException("Division not supported by " + this.getType().name());
    }

    public Primary modulo(Primary rhs) throws EvaluatorException {
        throw new EvaluatorException("Modulo not supported by " + this.getType().name());
    }

    public Primary exponent(Primary rhs) throws EvaluatorException {
        throw new EvaluatorException("Exponentiation not supported by " + this.getType().name());
    }

    public Primary or(Primary rhs) throws EvaluatorException {
        throw new EvaluatorException("Logical Or not supported by " + this.getType().name());
    }

    public Primary and(Primary rhs) throws EvaluatorException {
        throw new EvaluatorException("Logical And not supported by " + this.getType().name());
    }

    public Primary lessThan(Primary rhs) throws EvaluatorException {
        throw new EvaluatorException("Less than not supported by " + this.getType().name());
    }

    public Primary lessThanEquals(Primary rhs) throws EvaluatorException {
        throw new EvaluatorException("Less Than or Equal not supported by " + this.getType().name());
    }

    public Primary greaterThan(Primary rhs) throws EvaluatorException {
        throw new EvaluatorException("Greater than not supported by " + this.getType().name());
    }

    public Primary greaterThanEquals(Primary rhs) throws EvaluatorException {
        throw new EvaluatorException("Greater than or equal not supported by " + this.getType().name());
    }

    public Primary equals(Primary rhs) throws EvaluatorException {
        throw new EvaluatorException("Equal not supported by " + this.getType().name());
    }

    public Primary notEquals(Primary rhs) throws EvaluatorException {
        try {
           return this.equals(rhs).not();
        } catch (EvaluatorException e) {
            throw new EvaluatorException("Not Equal not supported by " + this.getType().name());
        }
    }

    @Override
    public Primary assign(Primary rhs) throws EvaluatorException {
        throw new EvaluatorException("Assignment not supported by " + this.getType().name());
    }

    @Override
    public Primary addAssign(Primary rhs) throws EvaluatorException {
        return this.assign(this.add(rhs));
    }

    @Override
    public Primary subtractAssign(Primary rhs) throws EvaluatorException {
        return this.assign(this.subtract(rhs));
    }

    @Override
    public Primary multiplyAssign(Primary rhs) throws EvaluatorException {
        return this.assign(this.multiply(rhs));
    }

    @Override
    public Primary divideAssign(Primary rhs) throws EvaluatorException {
        return this.assign(this.divide(rhs));
    }

    @Override
    public Primary moduloAssign(Primary rhs) throws EvaluatorException {
        return this.assign(this.modulo(rhs));
    }

    @Override
    public Primary exponentAssign(Primary rhs) throws EvaluatorException {
        return this.assign(this.exponent(rhs));
    }

    public Primary index(Primary index) throws EvaluatorException {
        throw new EvaluatorException("Indexing access not supported by " + this.getType().name());
    }

    public Primary access(String prop) throws EvaluatorException {
        throw new EvaluatorException("Property access not supported by " + this.getType().name());
    }

    public Primary call(List<Primary> args) throws EvaluatorException {
        throw new EvaluatorException("Function call not supported by " + this.getType().name());
    }

    public Primary optional() throws EvaluatorException {
        throw new EvaluatorException("Optional not supported by " + this.getType().name());
    }

    public Primary minus() throws EvaluatorException {
        throw new EvaluatorException("Minus not supported by " + this.getType().name());
    }

    public Primary not() throws EvaluatorException {
        throw new EvaluatorException("Not not supported by " + this.getType().name());
    }

    @Override
    public String toString() {
        try {
            return this.getType().name() + ": " + this.castToString().getValue();
        } catch (EvaluatorException e) {
            //Do nothing
        }

        return this.getType().name();
    }
}

