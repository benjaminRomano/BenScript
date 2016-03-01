package org.bromano.benscript.tests;

import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.complexTypes.BenScriptArray;
import org.bromano.benscript.evaluator.primaries.ArrayPrimary;
import org.bromano.benscript.evaluator.primaries.IntegerPrimary;
import org.bromano.benscript.evaluator.primaries.NullPrimary;
import org.bromano.benscript.evaluator.primaries.Primary;
import org.bromano.benscript.lexer.BenScriptLexer;
import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.lexer.Lexer;
import org.bromano.benscript.lexer.LexerException;
import org.bromano.benscript.nodes.Program;
import org.bromano.benscript.parser.BenScriptParser;
import org.bromano.benscript.parser.Parser;
import org.bromano.benscript.parser.ParserException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BenScriptEvaluatorTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testMissingSemicolon() throws IOException, URISyntaxException, EvaluatorException, ParserException, LexerException {
        String code = this.getFileContent("missingSemicolon.bs");
        assertError(code, ParserException.class, "Expected a Semicolon, but received a EOF");
    }

    @Test
    public void testIfWithoutStatement() throws EvaluatorException, ParserException, LexerException, IOException, URISyntaxException {
        String code = this.getFileContent("ifWithoutStatement.bs");
        assertError(code, ParserException.class, "Expected a statement.");
    }

    @Test
    public void testInvalidFunctionParameter() throws IOException, URISyntaxException, EvaluatorException, ParserException, LexerException {
        String code = this.getFileContent("invalidFunctionParameter.bs");
        assertError(code, ParserException.class, "Expected a CloseParen, but received a IntegerLiteral");
    }

    @Test
    public void testArrays() throws IOException, URISyntaxException, EvaluatorException, ParserException, LexerException {
        String code = this.getFileContent("arrays.bs");

        assertEvaluatorResults(new EvaluatorResult("[5, 6, 7]\n5\n", new NullPrimary()), this.evaluateCode(code));
    }

    @Test
    public void testRecursion() throws IOException, URISyntaxException, EvaluatorException, ParserException, LexerException {
        String code = this.getFileContent("recursion.bs");

        assertEvaluatorResults(new EvaluatorResult("1\n1\n2\n3\n", new NullPrimary()), this.evaluateCode(code));
    }

    @Test
    public void testIteration() throws IOException, URISyntaxException, EvaluatorException, ParserException, LexerException {
        String code = this.getFileContent("iteration.bs");

        assertEvaluatorResults(new EvaluatorResult("0\n1\n2\n3\n4\n1\n2\n3\n4\n", new NullPrimary()), this.evaluateCode(code));
    }

    @Test
    public void testConditionals() throws IOException, URISyntaxException, EvaluatorException, ParserException, LexerException {
        String code = this.getFileContent("conditionals.bs");

        assertEvaluatorResults(new EvaluatorResult("1\n2\n", new NullPrimary()), this.evaluateCode(code));
    }

    private void assertError(String code, Class<? extends Throwable> type, String msg) throws EvaluatorException, ParserException, LexerException {

        this.exception.expect(type);
        this.exception.expectMessage(msg);
        this.evaluateCode(code);
    }

    private String getFileContent(String fileName) throws URISyntaxException, IOException {

        Path path = Paths.get(getClass().getResource("programs/" + fileName).toURI());

        return Files.readAllLines(path).stream().reduce("", (s, s2) -> s + "\n" + s2);
    }

    public void assertEvaluatorResults(EvaluatorResult expected, EvaluatorResult actual) {
        assertEquals(true, expected.equals(actual));
    }

    private EvaluatorResult evaluateCode(String code) throws LexerException, ParserException, EvaluatorException {
        Lexer lexer = new BenScriptLexer(code);
        Parser parser = new BenScriptParser();

        List<Lexeme> lexemes = lexer.getLexStream();
        parser.setLexemeStream(lexemes);

        Program p = parser.parse();

        Environment global = Environment.createGlobalEnvironment();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;

        // Capture output
        System.setOut(ps);

        Primary result = p.evaluate(global);

        System.out.flush();
        System.setOut(old);

        return new EvaluatorResult(baos.toString().replace("\r", ""), result);
    }
}
