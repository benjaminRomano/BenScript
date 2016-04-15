package org.bromano.benscript.evaluator.primaries;

import org.bromano.benscript.emitter.EmitterException;
import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.evaluator.EvaluatorException;

import java.util.List;

public interface Primary {
    PrimaryType getType();
    StringPrimary castToString() throws EvaluatorException;
    BooleanPrimary castToBoolean() throws EvaluatorException;
    IntegerPrimary castToInteger() throws EvaluatorException;
    Primary getPrimary() throws EvaluatorException;

    Primary add(Primary rhs) throws EvaluatorException;
    Primary subtract(Primary rhs) throws EvaluatorException;
    Primary multiply(Primary rhs) throws EvaluatorException;
    Primary divide(Primary rhs) throws EvaluatorException;
    Primary modulo(Primary rhs) throws EvaluatorException;
    Primary exponent(Primary rhs) throws EvaluatorException;

    Primary or(Primary rhs) throws EvaluatorException;
    Primary and(Primary rhs) throws EvaluatorException;

    Primary lessThan(Primary rhs) throws EvaluatorException;
    Primary greaterThan(Primary rhs) throws EvaluatorException;
    Primary lessThanEquals(Primary rhs) throws EvaluatorException;
    Primary greaterThanEquals(Primary rhs) throws EvaluatorException;
    Primary equals(Primary rhs) throws EvaluatorException;
    Primary notEquals(Primary rhs) throws EvaluatorException;

    Primary assign(Primary rhs) throws EvaluatorException;
    Primary addAssign(Primary rhs) throws EvaluatorException;
    Primary subtractAssign(Primary rhs) throws EvaluatorException;
    Primary multiplyAssign(Primary rhs) throws EvaluatorException;
    Primary divideAssign(Primary rhs) throws EvaluatorException;
    Primary moduloAssign(Primary rhs) throws EvaluatorException;
    Primary exponentAssign(Primary rhs) throws EvaluatorException;

    Primary index(Primary index) throws EvaluatorException;
    Primary access(String prop) throws EvaluatorException;
    Primary call(List<Primary> args) throws EvaluatorException;

    Primary optional() throws EvaluatorException;
    Primary minus() throws EvaluatorException;
    Primary not() throws EvaluatorException;
}
