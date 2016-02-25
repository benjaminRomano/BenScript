package org.bromano.benscript.lexer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BenScriptLexer implements Lexer {

    private String text;
    private int lineNum;
    private int lineStart;
    private int pos;
    private int end;
    private Map<String, LexemeKind> keywordMap;

    public BenScriptLexer() {
        this.keywordMap = this.generateKeywordMap();
    }

    public BenScriptLexer(String text) {
        this.setText(text);
        this.keywordMap = this.generateKeywordMap();
    }

    @Override
    public void setText(String text) {
        this.text = text;
        this.pos = 0;
        this.lineNum = 0;
        this.lineStart = 0;
        this.end = this.text.length();
    }

    public boolean isAMatch(int start, String sequence) {
        int end = start + sequence.length();
        return start < this.end && end <= this.end
                && text.substring(start, end).equals(sequence);
    }

    public boolean isAMatch(int start, char[] chars) {
        if (start >= this.end) {
            return false;
        }

        char currChar = text.charAt(this.pos);

        for (char ch : chars) {
            if (currChar == ch) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Lexeme lex() throws LexerException {

        while(true) {
            String value;
            LinePosition linePos;

            if (this.pos >= this.end) {
                return null;
            }

            char ch = text.charAt(this.pos);

            switch (ch) {
                case '\n':
                    this.pos++;
                    this.lineNum++;
                    this.lineStart = this.pos;
                    continue;
                case '\t':
                case '\r':
                case '\f':
                case ' ':
                    this.pos++;
                    continue;
                case '!':
                    linePos = getLinePosition();
                    this.pos++;
                    if(isAMatch(this.pos, "=")) {
                        this.pos++;
                        return new Lexeme(LexemeKind.ExclamationEquals, "!=", linePos);
                    }

                    return new Lexeme(LexemeKind.Exclamation, "!", linePos);
                case '"':
                case '\'':
                    linePos = getLinePosition();
                    value = this.scanString();

                    return new Lexeme(LexemeKind.StringLiteral, value, linePos);
                case '%':
                    linePos = getLinePosition();
                    this.pos++;

                    if(isAMatch(this.pos, "=")) {
                        this.pos++;
                        return new Lexeme(LexemeKind.PercentEquals, "%=", linePos);
                    }

                    return new Lexeme(LexemeKind.Percent, "%", linePos);
                case '&':
                    linePos = getLinePosition();
                    this.pos++;

                    if(!isAMatch(this.pos, "&")) {
                        throw new LexerException(error("Ampersand Expected"));
                    }

                    this.pos++;
                    return new Lexeme(LexemeKind.AmpersandAmpersand, "&&", linePos);
                case '(':
                    linePos = getLinePosition();
                    this.pos++;
                    return new Lexeme(LexemeKind.OpenParen, linePos);
                case ')':
                    linePos = getLinePosition();
                    this.pos++;
                    return new Lexeme(LexemeKind.CloseParen, linePos);
                case '*':
                    linePos = getLinePosition();
                    this.pos++;

                    if(isAMatch(this.pos, "=")) {
                        this.pos++;
                        return new Lexeme(LexemeKind.AsteriskEquals, "*=", linePos);
                    }

                    return new Lexeme(LexemeKind.Asterisk, "*", linePos);
                case '+':
                    linePos = getLinePosition();
                    this.pos++;

                    if(isAMatch(this.pos, "=")) {
                        this.pos++;
                        return new Lexeme(LexemeKind.PlusEquals, "+=", linePos);
                    }

                    return new Lexeme(LexemeKind.Plus, "+", linePos);
                case ',':
                    linePos = getLinePosition();
                    this.pos++;
                    return new Lexeme(LexemeKind.Comma, ",", linePos);
                case '-':
                    linePos = getLinePosition();
                    this.pos++;

                    if(isAMatch(this.pos, "=")) {
                        this.pos++;
                        return new Lexeme(LexemeKind.MinusEquals, "-=", linePos);
                    }

                    return new Lexeme(LexemeKind.Minus, "-", linePos);
                case '.':
                    linePos = getLinePosition();
                    this.pos++;
                    return new Lexeme(LexemeKind.Dot, ".", linePos);
                case '/':
                    linePos = getLinePosition();
                    this.pos++;

                    if(isAMatch(this.pos, "=")) {
                        this.pos++;
                        return new Lexeme(LexemeKind.SlashEquals, "/=", linePos);
                    } else if (isAMatch(this.pos, "/")) {
                        //Line comments
                        this.pos++;

                        while (this.pos < this.end) {
                            if (isAMatch(this.pos, "\n")) {
                                this.pos++;
                                break;
                            }
                            this.pos++;
                        }

                        continue;
                    } else if (isAMatch(this.pos, "*")) {
                        //Block comments
                        this.pos++;

                        while (pos < end) {
                            if (isAMatch(pos, "*/")) {
                                this.pos += 2;
                                break;
                            }
                            this.pos++;
                        }

                        continue;
                    }

                    return new Lexeme(LexemeKind.Slash, "/", linePos);
                case ':':
                    linePos = getLinePosition();
                    this.pos++;
                    return new Lexeme(LexemeKind.Colon, ":", linePos);
                case ';':
                    linePos = getLinePosition();
                    this.pos++;
                    return new Lexeme(LexemeKind.Semicolon, ";", linePos);
                case '<':
                    linePos = getLinePosition();
                    this.pos++;

                    if (isAMatch(this.pos, "=")) {
                        this.pos++;
                        return new Lexeme(LexemeKind.LessThanEquals, "<=", linePos);
                    }

                    return new Lexeme(LexemeKind.LessThan, "<", linePos);
                case '=':
                    linePos = getLinePosition();
                    this.pos++;

                    if (isAMatch(this.pos, "=")) {
                        this.pos++;
                        return new Lexeme(LexemeKind.EqualsEquals, "==", linePos);
                    } else if(this.isAMatch(this.pos, ">")) {
                        this.pos++;
                        return new Lexeme(LexemeKind.EqualsGreaterThan, "=>", linePos);
                    }

                    return new Lexeme(LexemeKind.Equals, "=", linePos);
                case '>':
                    linePos = getLinePosition();
                    this.pos++;

                    if (isAMatch(this.pos, "=")) {
                        this.pos++;
                        return new Lexeme(LexemeKind.GreaterThanEquals, ">=", linePos);
                    }

                    return new Lexeme(LexemeKind.GreaterThan, ">", linePos);
                case '?':
                    linePos = getLinePosition();
                    this.pos++;
                    return new Lexeme(LexemeKind.QuestionMark, "?", linePos);
                case '[':
                    linePos = getLinePosition();
                    this.pos++;
                    return new Lexeme(LexemeKind.OpenBracket, linePos);
                case ']':
                    linePos = getLinePosition();
                    this.pos++;
                    return new Lexeme(LexemeKind.CloseBracket, linePos);
                case '^':
                    linePos = getLinePosition();
                    this.pos++;

                    if (isAMatch(this.pos, "=")) {
                        this.pos++;
                        return new Lexeme(LexemeKind.CaretEquals, "^=", linePos);
                    }

                    return new Lexeme(LexemeKind.Caret, "^", linePos);
                case '_':
                    linePos = getLinePosition();
                    value = this.scanIdentifier();
                    return new Lexeme(LexemeKind.Identifier, value, linePos);
                case '{':
                    linePos = getLinePosition();
                    this.pos++;
                    return new Lexeme(LexemeKind.OpenBrace, linePos);
                case '}':
                    linePos = getLinePosition();
                    this.pos++;
                    return new Lexeme(LexemeKind.CloseBrace, linePos);
                case '|':
                    linePos = getLinePosition();
                    this.pos++;

                    if (!isAMatch(this.pos, "|")) {
                        throw new LexerException(error("Expected a |"));
                    }

                    this.pos++;
                    return new Lexeme(LexemeKind.BarBar, "||", linePos);
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    linePos = getLinePosition();
                    value = this.scanInteger();
                    return new Lexeme(LexemeKind.IntegerLiteral, value, linePos);
                default:
                    linePos = getLinePosition();

                    if (this.isAlphabetOrUnderscore(ch)) {

                        String identifierOrKeyword = this.scanIdentifier();

                        if (identifierOrKeyword.trim().toLowerCase().equals("true")
                                || identifierOrKeyword.trim().toLowerCase().equals("false")) {
                            return new Lexeme(LexemeKind.BooleanLiteral, identifierOrKeyword.trim().toLowerCase());
                        }

                        if (this.keywordMap.containsKey(identifierOrKeyword.trim().toLowerCase())) {
                            return new Lexeme(keywordMap.get(identifierOrKeyword.trim().toLowerCase()), linePos);
                        }

                        return new Lexeme(LexemeKind.Identifier, identifierOrKeyword, linePos);
                    }

                    throw new LexerException(error("Char " + ch + " cannot be lexed"));

           }
        }
    }

    @Override
    public List<Lexeme> getLexStream() throws LexerException {
        List<Lexeme> lexemes = new ArrayList<>();

        Lexeme lexeme = this.lex();

        while(lexeme != null) {
            lexemes.add(lexeme);

            lexeme = this.lex();
        }

        return lexemes;
    }

    private boolean isAlphabetOrUnderscore(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || ch == '_';
    }

    private boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    private String scanInteger() {
        StringBuilder sb = new StringBuilder();

        while (this.pos < this.end) {
            char ch = this.text.charAt(this.pos);

            if (ch == '0') {
                this.pos++;
                return "0";
            } else if (this.isDigit(ch)) {
                sb.append(ch);
                this.pos++;
            } else {
                break;
            }
        }

        return sb.toString();
    }

    private String scanIdentifier() throws LexerException {
        StringBuilder sb = new StringBuilder();

        boolean isFirstCharacter = true;

        while (this.pos < this.end) {
            char ch = text.charAt(this.pos);

            if (this.isAlphabetOrUnderscore(ch)) {
                isFirstCharacter = false;
                sb.append(ch);
                this.pos++;
            } else if (isDigit(ch)) {
                if (isFirstCharacter) {
                    throw new LexerException("Identifier cannot start with a digit");
                }
                sb.append(ch);
                this.pos++;
            } else {
                break;
            }
        }

        return sb.toString();
    }

    private String scanString() throws LexerException {

        if (this.pos >= this.end) {
            return "";
        }

        char quote = this.text.charAt(this.pos);
        boolean usesDoubleQuotes = quote == '"';
        this.pos++;

        if (quote != '"' && quote != '\'') {
            throw new LexerException("String must start with a quote");
        }

        StringBuilder sb = new StringBuilder();

        boolean hasClosingQuote = false;

        while (this.pos < this.end) {
            char ch = this.text.charAt(this.pos);

            if (isAMatch(this.pos, "\\")) {
                sb.append(scanEscapeSequence(usesDoubleQuotes));
            } else if (isAMatch(this.pos, "\n")) {
                throw new LexerException("Unexpected new-line character in string");
            } else if (quote == ch) {
                this.pos++;
                hasClosingQuote = true;
                break;
            } else {
                sb.append(ch);
                this.pos++;
            }
        }

        if (!hasClosingQuote) {
            throw new LexerException("String-literal is missing closing quote");
        }

        return sb.toString();
    }

    private String scanEscapeSequence(boolean usesDoubleQuotes) throws LexerException {

        StringBuilder sb = new StringBuilder();

        if (!isAMatch(this.pos, "\\")) {
            throw new LexerException("Expected backslash in escape sequence");
        }

        sb.append(this.text.charAt(this.pos));
        this.pos++;

        if (isAMatch(this.pos, "'") && !usesDoubleQuotes) {
            throw new LexerException("Cannot use escaped single quote within single quotes");
        }

        if (isAMatch(this.pos, "\"") && usesDoubleQuotes) {
            throw new LexerException("Cannot use escaped double quote within double quotes");
        }

        if (!isAMatch(this.pos, new char[] { '\'', '"', '\\', 't', 'n' })) {
            throw new LexerException(error("Unexpected char " + this.text.charAt(this.pos) + " found in escape sequence"));
        }

        sb.append(this.text.charAt(this.pos));
        this.pos++;

        return sb.toString();
    }

    private LinePosition getLinePosition() {
        return new LinePosition(this.lineNum, this.pos - this.lineStart);
    }

    private String error(String s) {
        return getLinePosition().toString() + " " + s;
    }

    private Map<String, LexemeKind> generateKeywordMap() {
        Map<String, LexemeKind> keywordMap = new HashMap<>();
        keywordMap.put("null", LexemeKind.NullKeyword);
        keywordMap.put("if", LexemeKind.IfKeyword);
        keywordMap.put("var", LexemeKind.VarKeyword);
        keywordMap.put("func", LexemeKind.FuncKeyword);
        keywordMap.put("break", LexemeKind.BreakKeyword);
        keywordMap.put("return", LexemeKind.ReturnKeyword);
        keywordMap.put("continue", LexemeKind.ContinueKeyword);
        keywordMap.put("else", LexemeKind.ElseKeyword);
        keywordMap.put("for", LexemeKind.ForKeyword);
        keywordMap.put("while", LexemeKind.WhileKeyword);

        return keywordMap;
    }
}
