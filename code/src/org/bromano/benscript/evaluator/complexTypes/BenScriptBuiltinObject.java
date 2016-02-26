package org.bromano.benscript.evaluator.complexTypes;

import org.bromano.benscript.evaluator.primaries.NullPrimary;
import org.bromano.benscript.evaluator.primaries.Primary;

import java.util.Map;

public abstract class BenScriptBuiltinObject implements BenScriptObject {

    protected Map<String, Primary> values;

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
        if (!(obj instanceof BenScriptBuiltinObject)) {
            return false;
        }

        BenScriptBuiltinObject bsObject = (BenScriptBuiltinObject) obj;

        return bsObject.values.equals(this.values);
    }
}
