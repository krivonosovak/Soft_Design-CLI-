package ru.spbau.mit.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.spbau.mit.execute.Scope;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class EqualsTest {

    private Equals equals;
    private Scope scope;

    @Before
    public void setUp() throws Exception {
        scope = new Scope();
    }

    @After
    public void tearDown() throws Exception {
        equals = null;
        scope = null;
    }

    @Test
    public void testEmptyScope() {
        // given
        List<String> arguments = Arrays.asList("foo", "bar");
        equals = new Equals(arguments);
        // when
        equals.execute(scope, "");
        // then
        assertEquals("bar", scope.get("foo"));
    }

    @Test
    public void testOverwriteExistingScope() {
        // given
        List<String> arguments = Arrays.asList("foo", "bar");
        equals = new Equals(arguments);
        // when
        scope.add("foo", "bigger FOO");
        equals.execute(scope, "");
        // then
        assertEquals("bar", scope.get("foo"));
    }
}