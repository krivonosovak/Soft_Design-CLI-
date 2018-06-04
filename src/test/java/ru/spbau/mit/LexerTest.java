package ru.spbau.mit;

import org.junit.Test;
import ru.spbau.mit.execute.Scope;
import ru.spbau.mit.parse.Lexer;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LexerTest {

    @Test
    public void testNothingToExpandWithEmptyScope() {
        // given
        Lexer lexer = new Lexer();
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
        Lexer lexer = new Lexer();
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
        Lexer lexer = new Lexer();
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
        Lexer lexer = new Lexer();
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
        Lexer lexer = new Lexer();
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
        Lexer lexer = new Lexer();
        List<String> tokens = Arrays.asList("echo", "x'$bar'x");
        Scope scope = new Scope();
        scope.add("bar", "foo");
        // when
        List<String> results = lexer.expand(scope, tokens);
        // then
        assertEquals(Arrays.asList("echo", "x'foo'x"), results);
    }
}