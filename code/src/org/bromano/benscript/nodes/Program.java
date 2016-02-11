package org.bromano.benscript.nodes;

import org.bromano.benscript.nodes.statements.Statement;

import java.util.List;

public class Program {
    public List<Statement> statements;

    public String emit() {

        String prettyPrintedString = "";

        for (Statement statement : this.statements) {
            prettyPrintedString += statement.emit(0);
        }

        return prettyPrintedString;
    }
}
