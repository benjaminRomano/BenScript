package org.bromano.benscript.evaluator.primaries;

import org.bromano.benscript.evaluator.BenScriptObject;
import org.bromano.benscript.evaluator.EvaluatorException;

import java.util.*;

public class ObjectPrimary extends BasePrimary<BenScriptObject> {

    private BenScriptObject bsObject;

    public ObjectPrimary(BenScriptObject bsObject) {

        this.type = PrimaryType.Object;
        this.bsObject = bsObject;
    }

    @Override
    public BenScriptObject getValue() throws EvaluatorException {
        return this.bsObject;
    }

    @Override
    public BooleanPrimary castToBoolean() throws EvaluatorException {
        return new BooleanPrimary(true);
    }

    @Override
    public StringPrimary castToString() throws EvaluatorException {
        StringBuilder sb = new StringBuilder();

        Iterator<Map.Entry<String, Primary>> entries = this.bsObject.values.entrySet().iterator();

        sb.append("{");

        while (entries.hasNext()) {
            Map.Entry<String, Primary> entry = entries.next();
            sb.append("\n" + entry + ": " + entry.getValue().castToString().getValue());

            if (entries.hasNext()) {
                sb.append(",");
            }
        }

        sb.append("\n}");

        return new StringPrimary(sb.toString());
    }

    @Override
    public Primary equals(Primary rhs) throws EvaluatorException {
        return new BooleanPrimary(this.bsObject.equals(rhs.getValue()));
    }

    @Override
    public Primary index(Primary index) throws EvaluatorException {
        return this.access(index.castToString().getValue());
    }

    @Override
    public Primary access(String prop) throws EvaluatorException {
        List<String> props = new ArrayList<>();

        return new ObjectIdentifierPrimary(this.bsObject, prop);
    }

    @Override
    public Primary or(Primary rhs) throws EvaluatorException {
        return this.castToBoolean().or(rhs.castToBoolean());
    }

    @Override
    public Primary and(Primary rhs) throws EvaluatorException {
        return this.castToBoolean().or(rhs.castToBoolean());
    }

    @Override
    public Primary optional() throws EvaluatorException {
        return new OptionalPrimary<>(this);
    }

    @Override
    public Primary not() throws EvaluatorException {
        return new BooleanPrimary(false);
    }
}
