package ru.spbau.mit.command;

import org.junit.After;
import org.junit.Test;
import ru.spbau.mit.execute.Scope;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class EchoTest {

    private Echo echo;
    private Scope scope = new Scope();

    @After
    public void tearDown() throws Exception {
        echo = null;
    }

    @Test
    public void testSingleArgument() {
        // given
        String arg = "sample\ttext\t";
        List<String> arguments = Arrays.asList(arg);
        // when
        echo = new Echo(arguments);
        String output = echo.execute(scope, "");
        // then
        assertEquals(output, arg);
    }

    @Test
    public void testMultipleArguments() {
        // given
        List<String> arguments = Arrays.asList("foo     uj", "wertyu");
        // when
        echo = new Echo(arguments);
        String output = echo.execute(scope, "");
        // then
        assertEquals(output, String.join(" ", arguments));
    }

    @Test
    public void testInput() {
        // given
        List<String> arguments = new ArrayList<>();
        // when
        echo = new Echo(arguments);
        String output = echo.execute(scope, "test");
        // then
        assertEquals(output, "test");
    }
}