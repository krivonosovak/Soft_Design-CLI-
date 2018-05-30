package ru.spbau.mit;


import org.junit.Test;
import ru.spbau.mit.command.Cat;
import ru.spbau.mit.execute.Scope;
import ru.spbau.mit.parse.Lexer;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CatTest {


    @Test
    public void testWithoutArgumentsButInput() throws Exception {
        // given
        Scope scope = new Scope();
        List<String> arguments = new ArrayList<>();
        String input = "foobar";
        // when
        Cat cat = new Cat(arguments);
        String output = cat.execute(scope, input);
        // then
        assertEquals(input, output);
    }

    @Test
    public void testWithArgumentsNoInput() throws Exception {
        // given
        Scope scope = new Scope();
        try (PrintWriter out = new PrintWriter("temp_file_testWithArgumentsNoInput.txt")) {
            out.print("sample text");
        }
        List<String> arguments = Arrays.asList("temp_file_testWithArgumentsNoInput.txt");
        // when
        Cat cat = new Cat(arguments);
        String output = cat.execute(scope, "");
        // then
        assertEquals("sample text", output);
        new File("temp_file_testWithArgumentsNoInput.txt").delete();
    }

    public static class LexerTest {


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
}