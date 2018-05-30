package ru.spbau.mit;



import org.junit.Test;
import ru.spbau.mit.command.Echo;
import ru.spbau.mit.execute.Scope;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EchoTest {


    @Test
    public void testSingleArgument() {
        // given
        Scope scope = new Scope();
        String arg = "sample\ttext\t";
        List<String> arguments = Arrays.asList(arg);
        // when
        Echo echo = new Echo(arguments);
        String output = echo.execute(scope, "");
        // then
        assertEquals(arg, output);
    }

    @Test
    public void testMultipleArguments() {
        // given
        Scope scope = new Scope();
        List<String> arguments = Arrays.asList("foo     uj", "wertyu");
        // when
        Echo echo = new Echo(arguments);
        String output = echo.execute(scope, "");
        // then
        assertEquals(String.join(" ", arguments), output);
    }

    @Test
    public void testInput() {
        // given
        Scope scope = new Scope();
        List<String> arguments = new ArrayList<>();
        // when
        Echo echo = new Echo(arguments);
        String output = echo.execute(scope, "test");
        // then
        assertEquals("test", output);
    }
}