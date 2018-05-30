package ru.spbau.mit.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.spbau.mit.execute.Scope;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CatTest {

    Cat cat;
    Scope scope = new Scope();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        cat = null;
    }

    @Test
    public void testWithoutArgumentsButInput() throws Exception {
        // given
        List<String> arguments = new ArrayList<>();
        String input = "foobar";
        // when
        cat = new Cat(arguments);
        String output = cat.execute(scope, input);
        // then
        assertEquals(input, output);
    }

    @Test
    public void testWithArgumentsNoInput() throws Exception {
        // given
        PrintWriter out = new PrintWriter("temp_file_testWithArgumentsNoInput.txt");
        out.print("sample text");
        out.close();

        List<String> arguments = Arrays.asList("temp_file_testWithArgumentsNoInput.txt");
        // when
        cat = new Cat(arguments);
        String output = cat.execute(scope, "");
        // then
        assertEquals("sample text", output);
        new File("temp_file_testWithArgumentsNoInput.txt").delete();
    }
}