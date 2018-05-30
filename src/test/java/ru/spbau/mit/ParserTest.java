package ru.spbau.mit;


import org.junit.Test;
import ru.spbau.mit.exceptions.UnbalancedQuotes;
import ru.spbau.mit.parse.Parser;

import java.util.List;

import static org.junit.Assert.*;

public class ParserTest {


    @Test
    public void parseEmptyString() throws UnbalancedQuotes {
        // given
        String input = "";
        Parser parser = new Parser();
        // when
        List<String> tokens = parser.parse(input);
        // then
        assertEquals(0, tokens.size());
    }

    @Test
    public void parseAssignment() throws UnbalancedQuotes {
        // given
        String input = "foo=bar test";
        Parser parser = new Parser();
        // when
        List<String> tokens = parser.parse(input);
        // then
        assertEquals(2, tokens.size());
        assertEquals("foo=bar", tokens.get(0));
    }


    @Test
    public void parseAssignmentWhitespace() throws UnbalancedQuotes {
        // given
        Parser parser = new Parser();
        String input = "foo = bar";
        // when
        List<String> tokens = parser.parse(input);
        // then
        assertEquals(3, tokens.size());
    }

    @Test
    public void parsePipes() throws UnbalancedQuotes {
        // given
        Parser parser = new Parser();
        String input = "foo=bar | test | another";
        // when
        List<String> tokens = parser.parse(input);
        // then
        assertEquals(5, tokens.size());
        assertEquals("another", tokens.get(4));
    }

    @Test
    public void parsePipesWhitespace() throws UnbalancedQuotes {
        // given
        Parser parser = new Parser();
        String input = "echo $foo |       test |         another";
        // when
        List<String> tokens = parser.parse(input);
        // then
        assertEquals(6, tokens.size());
        assertEquals("another", tokens.get(5));
    }

    @Test
    public void parseQuotes() throws UnbalancedQuotes {
        // given
        Parser parser = new Parser();
        String input = "echo \"$foo\" '|       test |'         another";
        // when
        List<String> tokens = parser.parse(input);
        // then
        assertEquals(4, tokens.size());
        assertEquals("$foo", tokens.get(1));
    }
}