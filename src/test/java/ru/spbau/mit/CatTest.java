package ru.spbau.mit;

import org.junit.Test;
import ru.spbau.mit.command.Cat;
import ru.spbau.mit.execute.Scope;

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
}