package org.bromano.benscript.lexer;

public class Lexeme {
    public LexemeKind kind;
    public String value;

    public Lexeme(LexemeKind kind) {
        this.kind = kind;
    }

    public Lexeme(LexemeKind kind, String value) {
       this.kind = kind;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.kind.name() + " " + this.value;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Lexeme lexeme = (Lexeme) obj;
        return (lexeme.kind == this.kind
                &&  (lexeme.value == null && this.value == null
                || (lexeme.value != null && this.value != null && lexeme.value.equals(this.value))));
    }
}
