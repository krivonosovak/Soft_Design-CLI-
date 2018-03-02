package ru.spbau.mit.parse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.spbau.mit.execute.Scope;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class LexerTest {

    Lexer lexer;

    @Before
    public void setUp() throws Exception {
        lexer = new Lexer();
    }

    @After
    public void tearDown() throws Exception {
        lexer = null;
    }

    @Test
    public void testNothingToExpandWithEmptyScope() {
        // given
        List<String> tokens = Arrays.asList("echo", "bar");
        Scope scope = new Scope();
        // when
        List<String> results = lexer.expand(scope, tokens);
        // then
        assertEquals(tokens, results);
    }

    @Test
    public void testNothingToExpandWithNonEmptyScope() {
        // given
        List<String> tokens = Arrays.asList("echo", "bar");
        Scope scope = new Scope();
        scope.add("bar", "i should not be replaced");
        // when
        List<String> results = lexer.expand(scope, tokens);
        // then
        assertEquals(tokens, results);
    }

    @Test
    public void testShouldExpandInDoubleQuotes() {
        // given
        List<String> tokens = Arrays.asList("echo", "$bar");
        Scope scope = new Scope();
        scope.add("bar", "foo");
        // when
        List<String> results = lexer.expand(scope, tokens);
        // then
        assertEquals(Arrays.asList("echo", "foo"), results);
    }

    @Test
    public void testShouldExpandUnknownVariableToEmptyString() {
        // given
        List<String> tokens = Arrays.asList("echo", "$bar");
        Scope scope = new Scope();
        // when
        List<String> results = lexer.expand(scope, tokens);
        // then
        assertEquals(Arrays.asList("echo", ""), results);
    }


    @Test
    public void testShouldNotExpandSingleQuotes() {
        // given
        List<String> tokens = Arrays.asList("echo", "'$bar'");
        Scope scope = new Scope();
        scope.add("bar", "foo");
        // when
        List<String> results = lexer.expand(scope, tokens);
        // then
        assertEquals(Arrays.asList("echo", "$bar"), results);
    }

    @Test
    public void testShouldExpandSingleQuotesInsideDoubleQuotes() {
        // given
        List<String> tokens = Arrays.asList("echo", "x'$bar'x");
        Scope scope = new Scope();
        scope.add("bar", "foo");
        // when
        List<String> results = lexer.expand(scope, tokens);
        // then
        assertEquals(Arrays.asList("echo", "x'foo'x"), results);
    }
}