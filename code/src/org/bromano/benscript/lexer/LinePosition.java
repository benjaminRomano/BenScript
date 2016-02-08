package org.bromano.benscript.lexer;

public class LinePosition {
    public int lineNum;
    public int colNum;

    public LinePosition(int lineNum, int colNum) {
        this.lineNum = lineNum;
        this.colNum = colNum;
    }

    @Override
    public String toString() {
        return "(" + lineNum + "," + colNum + ")";
    }
}
