package org.bromano.benscript.evaluator.primaries;

import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.complexTypes.BenScriptObject;

import java.util.*;

public class ObjectPrimary extends BasePrimary {

    private BenScriptObject bsObject;

    public ObjectPrimary(BenScriptObject bsObject) {

        this.type = PrimaryType.Object;
        this.bsObject = bsObject;
    }

    @Override
    public BooleanPrimary castToBoolean() throws EvaluatorException {
        return new BooleanPrimary(true);
    }

    @Override
    public StringPrimary castToString() throws EvaluatorException {
        StringBuilder sb = new StringBuilder();

        Iterator<Map.Entry<String, Primary>> entries = this.bsObject.getValues().entrySet().iterator();

        sb.append("{ ");

        while (entries.hasNext()) {
            Map.Entry<String, Primary> entry = entries.next();

            Primary p = entry.getValue();

            // Solves the problem of infinite recursion and large objects
            if (p instanceof ObjectPrimary) {
                sb.append(entry.getKey() + ": { ... }");
            } else {
                sb.append(entry.getKey() + ": " + entry.getValue().castToString().getValue());
            }

            if (entries.hasNext()) {
                sb.append(", ");
            }
        }

        sb.append(" }");

        return new StringPrimary(sb.toString());
    }

    public BenScriptObject getValue() {
       return this.bsObject;
    }

    @Override
    public Primary equals(Primary rhs) throws EvaluatorException {
        return new BooleanPrimary(this == rhs.getPrimary());
    }

    @Override
    public Primary index(Primary index) throws EvaluatorException {
        return this.access(index.castToString().getValue());
    }

    @Override
    public Primary access(String prop) throws EvaluatorException {
        return new ObjectIdentifierPrimary(this.bsObject, prop);
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
    public Primary optional() throws EvaluatorException {
        return new OptionalPrimary(this);
    }

    @Override
    public Primary not() throws EvaluatorException {
        return new BooleanPrimary(false);
    }
}
