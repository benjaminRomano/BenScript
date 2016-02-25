package org.bromano.benscript.nodes.statements;

import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.NullPrimary;
import org.bromano.benscript.evaluator.primaries.Primary;
import org.bromano.benscript.evaluator.primaries.special.BreakPrimary;
import org.bromano.benscript.evaluator.primaries.special.ContinuePrimary;
import org.bromano.benscript.evaluator.primaries.special.ReturnPrimary;
import org.bromano.benscript.nodes.expressions.Expression;

public class ForStatement implements Statement {
    public VariableDeclarationStatement initializationStatement;
    public Expression conditionalExpression;
    public Expression incrementalExpression;
    public Statement statement;

    @Override
    public Primary evaluate(Environment context) throws EvaluatorException {

        Primary result;

        initializationStatement.evaluate(context);

        while (conditionalExpression.evaluate(context).castToBoolean().getValue()) {

            Environment newContext = new Environment(context);

            if (this.statement instanceof VariableDeclarationStatement) {
                throw new EvaluatorException("Cannot have single variable declaration as for-loop body");
            } else if (this.statement instanceof FunctionDeclarationStatement) {
                throw new EvaluatorException("Cannot have single function declaration as for-loop body");
            }

            result = this.statement.evaluate(newContext);

            if (result instanceof ReturnPrimary) {
                return result;
            } else if (result instanceof BreakPrimary) {
               return new NullPrimary();
            }

            if (this.incrementalExpression != null) {
                this.incrementalExpression.evaluate(context);
            }
        }

        return new NullPrimary();
    }
}
