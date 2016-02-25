package org.bromano.benscript.evaluator;

import org.bromano.benscript.evaluator.primaries.Primary;
import org.bromano.benscript.nodes.statements.Statement;

import java.util.List;
import java.util.Map;

public class BenScriptObject {

    public Map<String, Primary> values;

    public BenScriptObject(Map<String, Primary> values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BenScriptObject)) {
            return false;
        }

        BenScriptObject bsObject = (BenScriptObject) obj;

        return bsObject.values.equals(this.values);
    }
}
