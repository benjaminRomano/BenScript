package org.bromano.benscript.evaluator;

import org.bromano.benscript.evaluator.primaries.NullPrimary;
import org.bromano.benscript.evaluator.primaries.Primary;

import java.util.HashMap;
import java.util.Map;

public class Environment {
    public Environment parent;
    public Map<String, Primary> variables;

    public Environment() {
        this.parent = null;
        this.variables = new HashMap<>();
    }

    public Environment(Environment parent) {
        this.parent = parent;
        this.variables = new HashMap<>();
    }

    public void updateVariable(String name, Primary value) throws EvaluatorException {
        if (this.variables.containsKey(name)) {
            this.variables.put(name, value);
            return;
        }

        if (this.parent == null) {
            throw new EvaluatorException("Could not find variable: " + name);
        }

        this.parent.updateVariable(name, value);
    }

    public Primary getVariable(String name) {
        if (this.variables.containsKey(name)) {
            return this.variables.get(name);
        }

        if (this.parent == null) {
            return new NullPrimary();
        }

        return this.parent.getVariable(name);
    }
}
