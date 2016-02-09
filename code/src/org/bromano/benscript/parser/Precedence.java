package org.bromano.benscript.parser;

public class Precedence {
    public static final int ASSIGNMENT = 1;
    public static final int EQUALITY = 2;
    public static final int RELATIONAL = 3;
    public static final int ADDITIVE = 4;
    public static final int MULTIPLICATIVE = 5;
    public static final int EXPONENT = 6;
    public static final int PREFIX = 7;
    public static final int POSTFIX = 8;
    public static final int CALL = 9;
}
