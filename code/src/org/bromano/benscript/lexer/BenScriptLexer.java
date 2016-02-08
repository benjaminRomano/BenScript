package org.bromano.benscript.lexer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BenScriptLexer implements Lexer {

    private String text;
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

    public Map<String, LexemeKind> generateKeywordMap() {
        Map<String, LexemeKind> keywordMap = new HashMap<>();
        keywordMap.put("true", LexemeKind.BooleanLiteral);
        keywordMap.put("false", LexemeKind.BooleanLiteral);
        keywordMap.put("false", LexemeKind.BooleanLiteral);
        keywordMap.put("null", LexemeKind.NullKeyword);
        keywordMap.put("if", LexemeKind.IfKeyword);
        keywordMap.put("var", LexemeKind.VarKeyword);
        keywordMap.put("func", LexemeKind.FuncKeyword);
        keywordMap.put("break", LexemeKind.BreakKeyword);
        keywordMap.put("return", LexemeKind.ReturnKeyword);
        keywordMap.put("return", LexemeKind.ReturnKeyword);
        keywordMap.put("continue", LexemeKind.ContinueKeyword);
        keywordMap.put("else", LexemeKind.ElseKeyword);
        keywordMap.put("for", LexemeKind.ForKeyword);
        keywordMap.put("while", LexemeKind.WhileKeyword);

        return keywordMap;
    }

    @Override
    public void setText(String text) {
        this.text = text;
        this.pos = 0;
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

            if (this.pos >= this.end) {
                return null;
            }

            char ch = text.charAt(this.pos);

            switch (ch) {
                case '\t':
                case '\n':
                case '\r':
                case '\f':
                case ' ':
                    this.pos++;
                    continue;
                case '!':
                    this.pos++;
                    if(isAMatch(this.pos, "=")) {
                        this.pos++;
                        return new Lexeme(LexemeKind.ExclamationEquals);
                    }

                    return new Lexeme(LexemeKind.Exclamation);
                case '"':
                case '\'':
                    value = this.scanString();

                    return new Lexeme(LexemeKind.StringLiteral, value);
                case '%':
                    this.pos++;

                    if(isAMatch(this.pos, "=")) {
                        this.pos++;
                        return new Lexeme(LexemeKind.PercentEquals);
                    }

                    return new Lexeme(LexemeKind.Percent);
                case '&':
                    this.pos++;

                    if(!isAMatch(this.pos, "&")) {
                        throw new LexerException("Ampersand Expected");
                    }

                    this.pos++;
                    return new Lexeme(LexemeKind.AmpersandAmpersand);
                case '(':
                    this.pos++;
                    return new Lexeme(LexemeKind.OpenParen);
                case ')':
                    this.pos++;
                    return new Lexeme(LexemeKind.OpenParen);
                case '*':
                    this.pos++;

                    if(isAMatch(this.pos, "=")) {
                        this.pos++;
                        return new Lexeme(LexemeKind.AsteriskEquals);
                    }

                    return new Lexeme(LexemeKind.Asterisk);
                case '+':
                    this.pos++;

                    if(isAMatch(this.pos, "=")) {
                        this.pos++;
                        return new Lexeme(LexemeKind.PlusEquals);
                    } else if(isAMatch(this.pos, "+")) {
                        this.pos++;
                        return new Lexeme(LexemeKind.PlusPlus);
                    }

                    return new Lexeme(LexemeKind.Plus);
                case ',':
                    this.pos++;
                    return new Lexeme(LexemeKind.Comma);
                case '-':
                    this.pos++;

                    if(isAMatch(this.pos, "=")) {
                        this.pos++;
                        return new Lexeme(LexemeKind.MinusEquals);
                    } else if(isAMatch(this.pos, "-")) {
                        this.pos++;
                        return new Lexeme(LexemeKind.MinusMinus);
                    }

                    return new Lexeme(LexemeKind.Minus);
                case '.':
                    this.pos++;
                    return new Lexeme(LexemeKind.Dot);
                case '/':
                    this.pos++;

                    if(isAMatch(this.pos, "=")) {
                        this.pos++;
                        return new Lexeme(LexemeKind.SlashEquals);
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

                    return new Lexeme(LexemeKind.Slash);
                case ';':
                    this.pos++;
                    return new Lexeme(LexemeKind.Semicolon);
                case '<':
                    this.pos++;

                    if (isAMatch(this.pos, "=")) {
                        this.pos++;
                        return new Lexeme(LexemeKind.LessThanEquals);
                    }

                    return new Lexeme(LexemeKind.LessThan);
                case '=':
                    this.pos++;

                    if (isAMatch(this.pos, "=")) {
                        this.pos++;
                        return new Lexeme(LexemeKind.EqualsEquals);
                    }

                    return new Lexeme(LexemeKind.Equals);
                case '>':
                    this.pos++;

                    if (isAMatch(this.pos, "=")) {
                        this.pos++;
                        return new Lexeme(LexemeKind.GreaterThanEquals);
                    }

                    return new Lexeme(LexemeKind.GreaterThan);
                case '?':
                    this.pos++;
                    return new Lexeme(LexemeKind.QuestionMark);
                case '[':
                    this.pos++;
                    return new Lexeme(LexemeKind.OpenBracket);
                case ']':
                    this.pos++;
                    return new Lexeme(LexemeKind.CloseBracket);
                case '^':
                    this.pos++;

                    if (isAMatch(this.pos, "=")) {
                        this.pos++;
                        return new Lexeme(LexemeKind.CaretEquals);
                    }

                    return new Lexeme(LexemeKind.Caret);
                case '_':
                    value = this.scanIdentifier();
                    return new Lexeme(LexemeKind.Identifier, value);
                case '{':
                    this.pos++;
                    return new Lexeme(LexemeKind.OpenBrace);
                case '}':
                    this.pos++;
                    return new Lexeme(LexemeKind.CloseBrace);
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
                    value = this.scanInteger();
                    return new Lexeme(LexemeKind.IntegerLiteral, value);
                default:

                    if (this.isAlphabetOrUnderscore(ch)) {

                        String identifierOrKeyword = this.scanIdentifier();

                        if (this.keywordMap.containsKey(identifierOrKeyword)) {
                            return new Lexeme(keywordMap.get(identifierOrKeyword));
                        }

                        return new Lexeme(LexemeKind.Identifier, identifierOrKeyword);
                    }

                    throw new LexerException("Char " + ch + " cannot be lexed");

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
        this.pos++;

        if (quote != '"' && quote != '\'') {
            throw new LexerException("String must start with a quote");
        }

        StringBuilder sb = new StringBuilder();

        sb.append(quote);

        boolean hasClosingQuote = false;

        while (this.pos < this.end) {
            char ch = this.text.charAt(this.pos);

            if (isAMatch(this.pos, "\\")) {
                sb.append(scanEscapeSequence());
            } else if (isAMatch(this.pos, "\n")) {
                throw new LexerException("Unexpected new-line character in string");
            } else if (quote == ch) {
                this.pos++;
                sb.append(quote);
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

    private String scanEscapeSequence() throws LexerException {

        StringBuilder sb = new StringBuilder();

        if (!isAMatch(this.pos, "\\")) {
            throw new LexerException("Expected backslash in escape sequence");
        }

        sb.append(this.text.charAt(this.pos));
        this.pos++;

        if (!isAMatch(this.pos, new char[] { '\'', '\"', '\\', '\t', '\n' })) {
            throw new LexerException("Unexpected char found in escape sequence");
        }

        sb.append(this.text.charAt(this.pos));
        this.pos++;

        return sb.toString();
    }

}
