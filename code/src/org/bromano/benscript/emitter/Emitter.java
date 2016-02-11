package org.bromano.benscript.emitter;

import org.bromano.benscript.nodes.Program;

public interface Emitter {
    String emit(Program program);
}
