package ru.spbau.mit.parse;

import ru.spbau.mit.exceptions.UnbalancedQuotesException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Parser is responsible for tokenizing an input string (i.e. building tokens from raw user input)
 */
public class Parser {

    /**
     * This method takes an command line input and converts it into a list of tokens that could be
     * interpreted by other parts of CLI.
     * @param toProcess a raw user input as a string
     * @return a list of strings - tokens each of which represents a single command line argument
     */
    public List<String> parse(String toProcess) throws UnbalancedQuotesException {
        if (toProcess == null || toProcess.length() == 0) {
            //no command? no string
            return new ArrayList<>();
        }
        // parse with a simple finite state machine

        final int normal = 0;
        final int inQuote = 1;
        final int inDoubleQuote = 2;
        int state = normal;
        final StringTokenizer tok = new StringTokenizer(toProcess, "\"\' ", true);
        final ArrayList<String> result = new ArrayList<String>();
        final StringBuilder current = new StringBuilder();
        boolean lastTokenHasBeenQuoted = false;

        while (tok.hasMoreTokens()) {
            String nextTok = tok.nextToken();
            switch (state) {
                case inQuote:
                    if ("\'".equals(nextTok)) {
                        lastTokenHasBeenQuoted = true;
                        state = normal;
                        current.append(nextTok);
                    } else {
                        current.append(nextTok);
                    }
                    break;
                case inDoubleQuote:
                    if ("\"".equals(nextTok)) {
                        lastTokenHasBeenQuoted = true;
                        state = normal;
                    } else {
                        current.append(nextTok);
                    }
                    break;
                default:
                    if ("\'".equals(nextTok)) {
                        state = inQuote;
                        current.append(nextTok);
                    } else if ("\"".equals(nextTok)) {
                        state = inDoubleQuote;
                    } else if (" ".equals(nextTok)) {
                        if (lastTokenHasBeenQuoted || current.length() != 0) {
                            result.add(current.toString());
                            current.setLength(0);
                        }
                    } else {
                        current.append(nextTok);
                    }
                    lastTokenHasBeenQuoted = false;
                    break;
            }
        }
        if (lastTokenHasBeenQuoted || current.length() != 0) {
            result.add(current.toString());
        }
        if (state == inQuote || state == inDoubleQuote) {
            throw new UnbalancedQuotesException(toProcess);
        }
        return Arrays.asList(result.toArray(new String[result.size()]));
    }
}
