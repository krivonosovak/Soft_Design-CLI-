package ru.spbau.mit;



import org.junit.Test;
import ru.spbau.mit.command.Equals;
import ru.spbau.mit.execute.Scope;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class EqualsTest {


    @Test
    public void testEmptyScope() {
        // given
        Scope scope = new Scope();
        List<String> arguments = Arrays.asList("foo", "bar");
        Equals equals = new Equals(arguments);
        // when
        equals.execute(scope, "");
        // then
        assertEquals("bar", scope.get("foo"));
    }

    @Test
    public void testOverwriteExistingScope() {
        // given
        Scope scope = new Scope();
        List<String> arguments = Arrays.asList("foo", "bar");
        Equals equals = new Equals(arguments);
        // when
        scope.add("foo", "bigger FOO");
        equals.execute(scope, "");
        // then
        assertEquals("bar", scope.get("foo"));
    }
}