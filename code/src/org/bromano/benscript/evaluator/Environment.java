package org.bromano.benscript.evaluator;

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
}
