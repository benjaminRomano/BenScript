package org.bromano.benscript.nodes.expressions;

import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.BooleanPrimary;
import org.bromano.benscript.evaluator.primaries.Primary;
import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.lexer.LexemeKind;

public class BinaryExpression implements Expression {
    public Expression left;
    public Lexeme operator;
    public Expression right;

    @Override
    public Primary evaluate(Environment context) throws EvaluatorException {
        Primary lhs = left.evaluate(context);

        // These two should short circuit
        if (operator.kind == LexemeKind.AmpersandAmpersand) {
            if (!lhs.castToBoolean().getValue()) {
                return new BooleanPrimary(false);
            } else {
                return lhs.and(right.evaluate(context));
            }
        }

        if (operator.kind == LexemeKind.BarBar) {
            if (lhs.castToBoolean().getValue()) {
                return new BooleanPrimary(true);
            } else {
                return lhs.or(right.evaluate(context));
            }
        }


        Primary rhs = right.evaluate(context);

        switch (operator.kind) {
            case Plus: return lhs.add(rhs);
            case Minus: return lhs.subtract(rhs);
            case Asterisk: return lhs.multiply(rhs);
            case Slash: return lhs.divide(rhs);
            case Percent: return lhs.modulo(rhs);
            case Caret: return lhs.exponent(rhs);
            case LessThan: return lhs.lessThan(rhs);
            case LessThanEquals: return lhs.lessThanEquals(rhs);
            case GreaterThan: return lhs.greaterThan(rhs);
            case GreaterThanEquals: return lhs.greaterThanEquals(rhs);
            case EqualsEquals: return lhs.equals(rhs);
            case ExclamationEquals: return lhs.notEquals(rhs);
        }

        throw new EvaluatorException("Could not interpret binary operator: " + operator.value);
    }
}
