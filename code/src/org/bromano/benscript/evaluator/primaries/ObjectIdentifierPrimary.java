package org.bromano.benscript.evaluator.primaries;

import org.bromano.benscript.evaluator.BenScriptObject;
import org.bromano.benscript.evaluator.EvaluatorException;

import java.util.List;

public class ObjectIdentifierPrimary extends BasePrimary<Object> {

    private BenScriptObject base;
    private String prop;

    public ObjectIdentifierPrimary(BenScriptObject base, String prop) {

        this.type = PrimaryType.ObjectIdentifier;
        this.base = base;
        this.prop = prop;
    }

    @Override
    public Object getValue() throws EvaluatorException {
        return this.extractProperty().getValue();
    }

    @Override
    public PrimaryType getType() {
        return this.extractProperty().getType();
    }

    @Override
    public BooleanPrimary castToBoolean() throws EvaluatorException {
        return this.extractProperty().castToBoolean();
    }

    @Override
    public StringPrimary castToString() throws EvaluatorException {
        return this.extractProperty().castToString();
    }

    @Override
    public IntegerPrimary castToInteger() throws EvaluatorException {
        return this.extractProperty().castToInteger();
    }

    @Override
    public Primary add(Primary rhs) throws EvaluatorException {
        return this.extractProperty().add(rhs);
    }

    @Override
    public Primary subtract(Primary rhs) throws EvaluatorException {
        return this.extractProperty().subtract(rhs);
    }

    @Override
    public Primary multiply(Primary rhs) throws EvaluatorException {
        return this.extractProperty().multiply(rhs);
    }

    @Override
    public Primary divide(Primary rhs) throws EvaluatorException {
        return this.extractProperty().divide(rhs);
    }

    @Override
    public Primary modulo(Primary rhs) throws EvaluatorException {
        return this.extractProperty().modulo(rhs);
    }

    @Override
    public Primary exponent(Primary rhs) throws EvaluatorException {
        return this.extractProperty().exponent(rhs);
    }

    @Override
    public Primary or(Primary rhs) throws EvaluatorException {
        return this.extractProperty().or(rhs);
    }

    @Override
    public Primary and(Primary rhs) throws EvaluatorException {
        return this.extractProperty().and(rhs);
    }

    @Override
    public Primary lessThan(Primary rhs) throws EvaluatorException {
        return this.extractProperty().lessThan(rhs);
    }

    @Override
    public Primary greaterThan(Primary rhs) throws EvaluatorException {
        return this.extractProperty().greaterThan(rhs);
    }

    @Override
    public Primary greaterThanEquals(Primary rhs) throws EvaluatorException {
        return this.extractProperty().greaterThanEquals(rhs);
    }

    @Override
    public Primary equals(Primary rhs) throws EvaluatorException {
        return this.extractProperty().equals(rhs);
    }

    @Override
    public Primary notEquals(Primary rhs) throws EvaluatorException {
        return this.extractProperty().notEquals(rhs);
    }

    @Override
    public Primary call(List<Primary> args) throws EvaluatorException {
        return this.extractProperty().call(args);
    }

    @Override
    public Primary minus() throws EvaluatorException {
        return this.extractProperty().minus();
    }

    @Override
    public Primary not() throws EvaluatorException {
        return this.extractProperty().not();
    }

    @Override
    public Primary optional() throws EvaluatorException {
        return new OptionalPrimary<>(this);
    }

    @Override
    public Primary access(String prop) throws EvaluatorException {
        return this.extractProperty().access(prop);
    }

    @Override
    public Primary index(Primary index) throws EvaluatorException {
        return this.extractProperty().index(index);
    }

    @Override
    public Primary assign(Primary rhs) throws EvaluatorException {
        this.base.values.put(this.prop, rhs);
        return this.extractProperty();
    }

    private Primary extractProperty() {
        if (this.base.values.containsKey(this.prop)) {
            return this.base.values.get(this.prop);
        }

        return new NullPrimary();
    }
}
