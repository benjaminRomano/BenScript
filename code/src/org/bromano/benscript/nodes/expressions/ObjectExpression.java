package org.bromano.benscript.nodes.expressions;

import org.bromano.benscript.evaluator.complexTypes.BenScriptBasicObject;
import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.ObjectPrimary;
import org.bromano.benscript.evaluator.primaries.Primary;
import org.bromano.benscript.lexer.Lexeme;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ObjectExpression implements Expression {
    public Map<Lexeme, Expression> keyValues;

    @Override
    public Primary evaluate(Environment context) throws EvaluatorException {

        Map<String, Primary> primaryKeyValues = new HashMap<>();

        Iterator<Map.Entry<Lexeme, Expression>> iterator = this.keyValues.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Lexeme, Expression> keyValue = iterator.next();
            primaryKeyValues.put(keyValue.getKey().value, keyValue.getValue().evaluate(context));
        }

        return new ObjectPrimary(new BenScriptBasicObject(primaryKeyValues));
    }
}
