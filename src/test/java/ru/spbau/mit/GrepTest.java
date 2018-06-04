package ru.spbau.mit;

import org.junit.Test;
import ru.spbau.mit.command.Grep;
import ru.spbau.mit.execute.Scope;
import ru.spbau.mit.parse.Lexer;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GrepTest {

    @Test
    public void testSimpleFind() throws Exception {
        // given
        Scope scope = new Scope();
        List<String> arguments = Arrays.asList("foo");
        String input = "foobar\n";
        // when
        Grep grep = new Grep(arguments);
        String output = grep.execute(scope, input);
        // then
        assertEquals(input, output);
    }

    @Test
    public void testSimpleFindInFile() throws Exception {
        // given
        Scope scope = new Scope();
        String filename = "temp_GrepTest_testSimpleFindInFile.txt";
        String input = "foobar\n";
        try (PrintWriter out = new PrintWriter(filename)) {
            out.print(input);
        }
        List<String> arguments = Arrays.asList("foo", filename);
        // when
        Grep grep = new Grep(arguments);
        String output = grep.execute(scope, "");
        // then
        assertEquals(input, output);
        // cleanup
        new File(filename).delete();
    }

    @Test
    public void testCaseInsensitiveFind() throws Exception {
        // given
        Scope scope = new Scope();
        List<String> arguments = Arrays.asList("foo", "-i");
        String input = "FoObAR\n";
        // when
        Grep grep = new Grep(arguments);
        String output = grep.execute(scope, input);
        // then
        assertEquals(input, output);
    }

    @Test
    public void testWordFind() throws Exception {
        // given
        Scope scope = new Scope();
        List<String> arguments = Arrays.asList("-w", "foo");
        String input = "foobar\nfoo bar\n";
        String expectedOutput = "foo bar\n";
        // when
        Grep grep = new Grep(arguments);
        String output = grep.execute(scope, input);
        // then
        assertEquals(expectedOutput, output);
    }

    @Test
    public void testContextFind() throws Exception {
        // given
        Scope scope = new Scope();
        List<String> arguments = Arrays.asList("-A", "2", "foo");
        String input = "foobar\n1\n2\n3\n\4\n";
        String expectedOutput = "foobar\n1\n2\n";
        // when
        Grep grep = new Grep(arguments);
        String output = grep.execute(scope, input);
        // then
        assertEquals(expectedOutput, output);
    }
}
