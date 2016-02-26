package org.bromano.benscript.evaluator.complexTypes;

import org.bromano.benscript.evaluator.primaries.Primary;

import java.util.Map;

public interface BenScriptObject {
    Primary getValue(String prop);
    Map<String, Primary> getValues();
    void setValue(String prop, Primary value);
}
