package ru.spbau.mit.execute;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.spbau.mit.command.Cat;
import ru.spbau.mit.command.Command;
import ru.spbau.mit.command.Echo;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ExecutorTest {

    private Executor executor;
    private Scope scope = new Scope();

    @Before
    public void setUp() throws Exception {
        executor = new Executor();
    }

    @After
    public void tearDown() throws Exception {
        executor = null;
    }

    @Test
    public void testPipe() throws Exception {
        // given
        Command echo = new Echo(Arrays.asList("foo bar"));
        Command cat = new Cat(new ArrayList<>());
        // when
        String output = executor.exec(scope, Arrays.asList(echo, cat));
        // then
        assertEquals("foo bar", output);
    }
}