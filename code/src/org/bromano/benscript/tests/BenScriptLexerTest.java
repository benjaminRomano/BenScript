package org.bromano.benscript.tests;

import org.bromano.benscript.lexer.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BenScriptLexerTest {

    @Test
    public void testGetLexStream() throws Exception {
        //Test keywords
        this.testLexStream("var func break return continue if null else for while",
                new Lexeme[] {
                        new Lexeme(LexemeKind.VarKeyword),
                        new Lexeme(LexemeKind.FuncKeyword),
                        new Lexeme(LexemeKind.BreakKeyword),
                        new Lexeme(LexemeKind.ReturnKeyword),
                        new Lexeme(LexemeKind.ContinueKeyword),
                        new Lexeme(LexemeKind.IfKeyword),
                        new Lexeme(LexemeKind.NullKeyword),
                        new Lexeme(LexemeKind.ElseKeyword),
                        new Lexeme(LexemeKind.ForKeyword),
                        new Lexeme(LexemeKind.WhileKeyword)
                });

        //Test boolean literals
        this.testLexStream("true false", new Lexeme[] {
                new Lexeme(LexemeKind.BooleanLiteral, "true"),
                new Lexeme(LexemeKind.BooleanLiteral, "false")
        });

        //Test integer literals
        this.testLexStream("0 05 123456789", new Lexeme[] {
                new Lexeme(LexemeKind.IntegerLiteral, "0"),
                new Lexeme(LexemeKind.IntegerLiteral, "0"),
                new Lexeme(LexemeKind.IntegerLiteral, "5"),
                new Lexeme(LexemeKind.IntegerLiteral, "123456789")
        });

        //Test string literals
        this.testLexStream("\" \\\\ \\n \\' \\\" \\t \" \" This is a string! \"", new Lexeme[] {
                new Lexeme(LexemeKind.StringLiteral, " \\\\ \\n \\' \\\" \\t "),
                new Lexeme(LexemeKind.StringLiteral, " This is a string! ")
        });


        //Puncuation
        this.testLexStream("{ } [ ] ( ) < <= > >= == != ! ; ? && || + - * / % ^ = += -= *= /= %= ^= ++ -- , . : =>",
                new Lexeme[] {
                        new Lexeme(LexemeKind.OpenBrace),
                        new Lexeme(LexemeKind.CloseBrace),
                        new Lexeme(LexemeKind.OpenBracket),
                        new Lexeme(LexemeKind.CloseBracket),
                        new Lexeme(LexemeKind.OpenParen),
                        new Lexeme(LexemeKind.CloseParen),
                        new Lexeme(LexemeKind.LessThan),
                        new Lexeme(LexemeKind.LessThanEquals),
                        new Lexeme(LexemeKind.GreaterThan),
                        new Lexeme(LexemeKind.GreaterThanEquals),
                        new Lexeme(LexemeKind.EqualsEquals),
                        new Lexeme(LexemeKind.ExclamationEquals),
                        new Lexeme(LexemeKind.Exclamation),
                        new Lexeme(LexemeKind.Semicolon),
                        new Lexeme(LexemeKind.QuestionMark),
                        new Lexeme(LexemeKind.AmpersandAmpersand),
                        new Lexeme(LexemeKind.BarBar),
                        new Lexeme(LexemeKind.Plus),
                        new Lexeme(LexemeKind.Minus),
                        new Lexeme(LexemeKind.Asterisk),
                        new Lexeme(LexemeKind.Slash),
                        new Lexeme(LexemeKind.Percent),
                        new Lexeme(LexemeKind.Caret),
                        new Lexeme(LexemeKind.Equals),
                        new Lexeme(LexemeKind.PlusEquals),
                        new Lexeme(LexemeKind.MinusEquals),
                        new Lexeme(LexemeKind.AsteriskEquals),
                        new Lexeme(LexemeKind.SlashEquals),
                        new Lexeme(LexemeKind.PercentEquals),
                        new Lexeme(LexemeKind.CaretEquals),
                        new Lexeme(LexemeKind.PlusPlus),
                        new Lexeme(LexemeKind.MinusMinus),
                        new Lexeme(LexemeKind.Comma),
                        new Lexeme(LexemeKind.Dot),
                        new Lexeme(LexemeKind.Colon),
                        new Lexeme(LexemeKind.EqualsGreaterThan),
                });

        this.testLexStream("_a abcd a0b", new Lexeme[] {
                new Lexeme(LexemeKind.Identifier, "_a"),
                new Lexeme(LexemeKind.Identifier, "abcd"),
                new Lexeme(LexemeKind.Identifier, "a0b")
        });

    }

    private void testLexStream(String s, Lexeme[] arr) throws LexerException {
        Lexer l = new BenScriptLexer();
        l.setText(s);
        List<Lexeme> lexemeStream = l.getLexStream();
        Lexeme[] lexemeArray = lexemeStream.toArray(new Lexeme[lexemeStream.size()]);

        assertArrayEquals(arr, lexemeArray);
    }
}