package org.bromano.benscript.evaluator.complexTypes;

import org.bromano.benscript.evaluator.primaries.NullPrimary;
import org.bromano.benscript.evaluator.primaries.Primary;

import java.util.HashMap;
import java.util.Map;

public class BenScriptBasicObject implements BenScriptObject {

    public Map<String, Primary> values;

    public BenScriptBasicObject(Map<String, Primary> values) {
        this.values = values;
    }

    public BenScriptBasicObject() {

        this.values = new HashMap<>();
    }

    @Override
    public Primary getValue(String prop) {
        if (this.values.containsKey(prop)) {
            return this.values.get(prop);
        }

        return new NullPrimary();
    }

    @Override
    public Map<String, Primary> getValues() {
        return this.values;
    }

    @Override
    public void setValue(String prop, Primary value) {
        this.values.put(prop, value);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BenScriptBasicObject)) {
            return false;
        }

        BenScriptBasicObject bsObject = (BenScriptBasicObject) obj;

        return bsObject.values.equals(this.values);
    }
}
