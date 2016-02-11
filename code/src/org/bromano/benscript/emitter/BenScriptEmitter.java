package org.bromano.benscript.emitter;

import org.bromano.benscript.nodes.Program;
import org.bromano.benscript.nodes.statements.Statement;

public class BenScriptEmitter implements Emitter {

    @Override
    public String emit(Program program) {

        StringBuilder sb = new StringBuilder();

        for (Statement s : program.statements) {

        }
    }
}
