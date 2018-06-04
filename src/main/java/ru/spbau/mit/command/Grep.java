package ru.spbau.mit.command;

import org.apache.commons.cli.*;
import ru.spbau.mit.exceptions.BadArgumentsException;
import ru.spbau.mit.execute.Scope;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Grep performs a substring search inside a file or an input stream of lines. Available options are:
 * - case-insensitive matching (-i)
 * - search by word boundaries (-w)
 * - print a few lines of context for each match (-A <N>)
 */
public class Grep extends Command {

    private String[] args;
    private int lineCount = 0;
    private boolean isIgnorCase = false;
    /**
     * Creates a new command with the given arguments
     */
    public Grep(List<String> arguments) {
        super(arguments);
    }

    /**
     * See Command.
     *
     * Grep implementation details: the input file name argument position is not fixed, it may come before or after
     * option arguments. If no input file name is specified, grep performs search on the input stream contents.
     */
    @Override
    public String execute(Scope scope, String inStream) throws IOException, BadArgumentsException {
        String result = "";
        parseParameters();
        Pattern pattern = Pattern.compile(args[0]);
        List<String> lines = null;
        if (args.length > 1) {
            Path file = Paths.get(args[1]);
            lines = Files.readAllLines(file, StandardCharsets.UTF_8);
            result = getMatchLines(pattern, lines);
        } else if (inStream.length() > 0) {
            lines = Arrays.asList(inStream.split("\n"));
            result = getMatchLines(pattern, lines);
        }
        return result;
    }

    private String getMatchLines(Pattern pattern, List<String> lines) {
        StringBuffer sb = new StringBuffer();
        int nextLine = 1;

        for (String line: lines) {
            String searchLine = line;
            if (isIgnorCase) {
                searchLine = line.toLowerCase();
            }
            if (pattern.matcher(searchLine).find()) {
                nextLine  = 1;
                sb.append(line + "\n");
            } else if (nextLine <= lineCount) {
                sb.append(line + "\n");
                nextLine++;
            }
        }
        return sb.toString();
    }

    private void parseParameters() throws BadArgumentsException {
        if (arguments.size() > 0) {
            CommandLineParser parser = new DefaultParser();
            Options options = new Options();
            options.addOption("i", "ignore-case", false, "ignore case");
            options.addOption("w", "word-regexp", false, "only the whole words");
            options.addOption("A", "after-context", true, "return n lines after result line");
            String[] data =  new String[arguments.size()];
            data  = arguments.toArray(data);
            CommandLine result = null;
            try {
                result = parser.parse(options, data);
            } catch (ParseException e) {
                throw new BadArgumentsException("grep");
            }
            args = result.getArgs();
            if (result.hasOption("i")) {
                args[0] = args[0].toLowerCase();
                isIgnorCase = true;
            }
            if (result.hasOption("w")) {
                args[0] = "\\b" + args[0] + "\\b";
            }
            if (result.hasOption("A")) {
                try {
                    lineCount = Integer.parseInt(result.getOptionValue("A"));
                } catch (NumberFormatException e) {
                    throw new BadArgumentsException("grep");
                }
            }
        }
    }
}
