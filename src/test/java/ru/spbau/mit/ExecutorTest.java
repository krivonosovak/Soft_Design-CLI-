package ru.spbau.mit;

import org.junit.Test;
import ru.spbau.mit.command.Cat;
import ru.spbau.mit.command.Command;
import ru.spbau.mit.command.Echo;
import ru.spbau.mit.execute.Executor;
import ru.spbau.mit.execute.Scope;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ExecutorTest {

    @Test
    public void testPipe() throws Exception {
        // given
        Scope scope = new Scope();
        Command echo = new Echo(Arrays.asList("foo bar"));
        Command cat = new Cat(new ArrayList<>());
        Executor executor = new Executor();
        // when
        String output = executor.exec(scope, Arrays.asList(echo, cat));
        // then
        assertEquals("foo bar", output);
    }
}