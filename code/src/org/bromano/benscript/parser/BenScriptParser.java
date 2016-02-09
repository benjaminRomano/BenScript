package org.bromano.benscript.parser;

import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.lexer.LexemeKind;
import org.bromano.benscript.nodes.expressions.Expression;
import org.bromano.benscript.nodes.Program;
import org.bromano.benscript.nodes.statements.*;
import org.bromano.benscript.parser.parslets.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BenScriptParser implements Parser {
    private List<Lexeme> lexemes;
    private int pos;
    private int end;

    //Handles prefix and identifiers
    private Map<LexemeKind, PrefixParslet> prefixParslets;
    //Handles everything but prefix. Not a great... Think of something better
    private Map<LexemeKind, InfixParslet> infixParslets;

    public BenScriptParser(List<Lexeme> lexemes) {
        this.setLexemeStream(lexemes);

        this.prefixParslets = this.generatePrefixParsletMap();
        this.infixParslets = this.generateInfixParsletMap();
    }

    public BenScriptParser() {
        this.prefixParslets = this.generatePrefixParsletMap();
        this.infixParslets = this.generateInfixParsletMap();
    }

    @Override
    public void setLexemeStream(List<Lexeme> lexemes) {
        this.lexemes = lexemes;
        this.pos = 0;
        this.end = lexemes.size();
    }

    @Override
    public Program parse() throws ParserException {
        Program program = new Program();

        program.statements = new ArrayList<>();

        while (statementPending()) {
            program.statements.add(parseStatement());

        }

        return program;
    }

    private Expression parseExpression() throws ParserException {
        // TODO: Implement this!!!!!!!!!!!!

        Lexeme lexeme = this.match();

        throw new NotImplementedException();
    }

    public Expression parseExpression(int precedence) {

        // TODO: Implement this!!!!!!!!!!!!
        throw new NotImplementedException();
    }

    private Statement parseStatement() throws ParserException {

        if (this.expressionStatementPending()) {

            return this.parseExpressionStatement();

        } else if (this.ifStatementPending()) {

            return this.parseIfStatement();

        } else if (this.whileStatementPending()) {

            return this.parseWhileStatement();

        } else if (this.forStatementPending()) {

            return this.parseForStatement();

        } else if (this.returnStatementPending()) {

            return this.parseReturnStatement();

        } else if (this.breakStatementPending()) {

            return this.parseBreakStatement();

        } else if (this.continueStatementPending()) {

            return this.parseContinueStatement();

        } else if (this.variableDeclarationPending()) {

            return this.parseVariableDeclarationStatement();

        } else if (this.functionDeclarationStatementPending()) {

            return this.parseFunctionDeclarationStatement();
        }

        throw new ParserException("Expected a statement");
    }

    private ExpressionStatement parseExpressionStatement() throws ParserException {

        ExpressionStatement expressionStatement = new ExpressionStatement();

        expressionStatement.expression = this.parseExpression();

        this.match(LexemeKind.Semicolon);

        return expressionStatement;
    }

    private IfStatement parseIfStatement() throws ParserException {

        IfStatement ifStatement = new IfStatement();

        this.match(LexemeKind.OpenParen);

        ifStatement.conditional = this.parseExpression();

        this.match(LexemeKind.CloseParen);

        ifStatement.statement = this.parseStatement();

        if (isAMatch(LexemeKind.ElseKeyword)) {
            this.match(LexemeKind.ElseKeyword);

            ifStatement.elseStatement = this.parseStatement();
        }

        return ifStatement;
    }

    private WhileStatement parseWhileStatement() throws ParserException {

        WhileStatement whileStatement = new WhileStatement();

        this.match(LexemeKind.WhileKeyword);

        this.match(LexemeKind.OpenParen);

        whileStatement.conditional = this.parseExpression();

        this.match(LexemeKind.CloseParen);

        whileStatement.statement = this.parseStatement();

        return whileStatement;
    }

    private ForStatement parseForStatement() throws ParserException {

        ForStatement forStatement = new ForStatement();

        this.match(LexemeKind.ForKeyword);

        this.match(LexemeKind.OpenParen);

        forStatement.initializationStatement = this.parseVariableDeclarationStatement();

        if (this.expressionPending()) {
            forStatement.conditionalStatement = this.parseExpression();
        }

        this.match(LexemeKind.Semicolon);

        if (this.expressionPending()) {
            forStatement.incrementalExpression = this.parseExpression();
        }

        this.match(LexemeKind.CloseParen);

        forStatement.statement = this.parseStatement();

        return forStatement;
    }

    private ReturnStatement parseReturnStatement() throws ParserException {

        ReturnStatement returnStatement = new ReturnStatement();

        this.match(LexemeKind.ReturnKeyword);

        returnStatement.expression = this.parseExpression();

        this.match(LexemeKind.Semicolon);

        return returnStatement;
    }

    private BreakStatement parseBreakStatement() throws ParserException {

        BreakStatement breakStatement = new BreakStatement();

        this.match(LexemeKind.BreakKeyword);

        this.match(LexemeKind.Semicolon);

        return breakStatement;
    }

    private ContinueStatement parseContinueStatement() throws ParserException {

        ContinueStatement continueStatement = new ContinueStatement();

        this.match(LexemeKind.ContinueKeyword);

        this.match(LexemeKind.Semicolon);

        return continueStatement;
    }

    private VariableDeclarationStatement parseVariableDeclarationStatement() throws ParserException {

        VariableDeclarationStatement variableDeclarationStatement = new VariableDeclarationStatement();

        this.match(LexemeKind.VarKeyword);

        variableDeclarationStatement.name = this.match(LexemeKind.Identifier);

        this.match(LexemeKind.Equals);

        variableDeclarationStatement.expression = this.parseExpression();

        this.match(LexemeKind.Semicolon);

        return variableDeclarationStatement;
    }

    private FunctionDeclarationStatement parseFunctionDeclarationStatement() throws ParserException {

        FunctionDeclarationStatement functionDeclarationStatement = new FunctionDeclarationStatement();

        this.match(LexemeKind.FuncKeyword);

        functionDeclarationStatement.name = this.match(LexemeKind.Identifier);

        this.match(LexemeKind.OpenParen);

        functionDeclarationStatement.parameters = this.parseParameterList();

        this.match(LexemeKind.CloseParen);

        functionDeclarationStatement.statement = this.parseStatement();

        return functionDeclarationStatement;
    }

    private List<Lexeme> parseParameterList() throws ParserException {

        List<Lexeme> lexemes = new ArrayList<>();

        if (!this.isAMatch(LexemeKind.Identifier)) {
            return lexemes;
        }

        lexemes.add(this.match(LexemeKind.Identifier));

        while (this.isAMatch(LexemeKind.Comma)) {
            this.match(LexemeKind.Comma);
            lexemes.add(this.match(LexemeKind.Identifier));
        }

        return lexemes;
    }

    private boolean statementPending() {
        return expressionStatementPending()
                || ifStatementPending()
                || whileStatementPending()
                || forStatementPending()
                || returnStatementPending()
                || breakStatementPending()
                || continueStatementPending()
                || variableDeclarationPending();
    }

    private boolean expressionStatementPending() {
        return expressionPending();
    }

    private boolean ifStatementPending() {
        return this.isAMatch(LexemeKind.IfKeyword);
    }

    private boolean whileStatementPending() {
        return this.isAMatch(LexemeKind.WhileKeyword);
    }

    private boolean forStatementPending() {
        return this.isAMatch(LexemeKind.ForKeyword);
    }

    private boolean breakStatementPending() {
        return this.isAMatch(LexemeKind.BreakKeyword);
    }

    private boolean returnStatementPending() {
        return this.isAMatch(LexemeKind.ReturnKeyword);
    }

    private boolean continueStatementPending() {
        return this.isAMatch(LexemeKind.ContinueKeyword);
    }

    private boolean variableDeclarationPending() {
        return this.isAMatch(LexemeKind.VarKeyword);
    }

    private boolean functionDeclarationStatementPending() {
        return this.isAMatch(LexemeKind.FuncKeyword);
    }

    private boolean expressionPending() {
        return this.isAMatch(new LexemeKind[]{
                LexemeKind.OpenBrace,
                LexemeKind.OpenParen,
                LexemeKind.OpenBracket,
                LexemeKind.Identifier,
                LexemeKind.BooleanLiteral,
                LexemeKind.IntegerLiteral,
                LexemeKind.StringLiteral,
                LexemeKind.Exclamation,
                LexemeKind.Minus
        });
    }

    private Lexeme lookAhead(int x) {
        if (this.pos + x >= this.end) {
            return null;
        }

        return this.lexemes.get(this.pos + x);
    }

    private boolean isAMatch(LexemeKind kind) {
        return this.lexemes.get(this.pos).kind == kind;
    }

    private boolean isAMatch(LexemeKind[] kinds) {
        Lexeme l = this.lexemes.get(this.pos);

        for (LexemeKind kind : kinds) {
            if (l.kind == kind) {
                return true;
            }
        }
        return false;
    }

    private boolean isAMatch(Lexeme lexeme, LexemeKind kind) {
        return lexeme.kind == kind;
    }

    private Lexeme match() throws ParserException {
        if (this.pos >= this.end) {
            throw new ParserException("Expected another lexeme");
        }

        Lexeme lexeme = this.lexemes.get(this.pos);

        this.pos++;

        return lexeme;
    }

    private Lexeme match(LexemeKind kind) throws ParserException {
        if (this.pos >= this.end || this.lexemes.get(this.pos).kind == kind) {
            throw new ParserException("Expected " + kind.name());
        }

        Lexeme lexeme = this.lexemes.get(this.pos);

        this.pos++;

        return lexeme;
    }

    private Map<LexemeKind, PrefixParslet> generatePrefixParsletMap() {
        Map<LexemeKind, PrefixParslet> map = new HashMap<>();

        map.put(LexemeKind.Exclamation, new PrefixOperatorParslet(Precedence.PREFIX));
        map.put(LexemeKind.Minus, new PrefixOperatorParslet(Precedence.PREFIX));

        map.put(LexemeKind.Identifier, new NameParslet());

        // TODO: lambda, group, arrays and objects

        return map;
    }

    private Map<LexemeKind, InfixParslet> generateInfixParsletMap() {
        Map<LexemeKind, InfixParslet> map = new HashMap<>();

        map.put(LexemeKind.Equals, new BinaryOperatorParslet(Precedence.ASSIGNMENT, true));
        map.put(LexemeKind.SlashEquals, new BinaryOperatorParslet(Precedence.ASSIGNMENT, true));
        map.put(LexemeKind.AsteriskEquals, new BinaryOperatorParslet(Precedence.ASSIGNMENT, true));
        map.put(LexemeKind.PercentEquals, new BinaryOperatorParslet(Precedence.ASSIGNMENT, true));
        map.put(LexemeKind.MinusEquals, new BinaryOperatorParslet(Precedence.ASSIGNMENT, true));
        map.put(LexemeKind.PlusEquals, new BinaryOperatorParslet(Precedence.ASSIGNMENT, true));
        map.put(LexemeKind.CaretEquals, new BinaryOperatorParslet(Precedence.ASSIGNMENT, true));

        map.put(LexemeKind.EqualsEquals, new BinaryOperatorParslet(Precedence.EQUALITY, true));

        map.put(LexemeKind.LessThan, new BinaryOperatorParslet(Precedence.RELATIONAL, false));
        map.put(LexemeKind.LessThanEquals, new BinaryOperatorParslet(Precedence.RELATIONAL, false));
        map.put(LexemeKind.GreaterThan, new BinaryOperatorParslet(Precedence.RELATIONAL, false));
        map.put(LexemeKind.GreaterThanEquals, new BinaryOperatorParslet(Precedence.RELATIONAL, false));

        map.put(LexemeKind.Plus, new BinaryOperatorParslet(Precedence.ADDITIVE, false));
        map.put(LexemeKind.Minus, new BinaryOperatorParslet(Precedence.ADDITIVE, false));

        map.put(LexemeKind.Asterisk, new BinaryOperatorParslet(Precedence.MULTIPLICATIVE, false));
        map.put(LexemeKind.Slash, new BinaryOperatorParslet(Precedence.MULTIPLICATIVE, false));
        map.put(LexemeKind.Percent, new BinaryOperatorParslet(Precedence.MULTIPLICATIVE, false));

        map.put(LexemeKind.Caret, new BinaryOperatorParslet(Precedence.EXPONENT, true));

        map.put(LexemeKind.PlusPlus, new PostfixOperatorParslet(Precedence.POSTFIX));
        map.put(LexemeKind.MinusMinus, new PostfixOperatorParslet(Precedence.POSTFIX));
        map.put(LexemeKind.QuestionMark, new PostfixOperatorParslet(Precedence.POSTFIX));

        // TODO: Array/Object accessor, funcCall

        return map;
    }
}

