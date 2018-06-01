//package ru.spbau.mit.command;
//
//import org.apache.commons.cli.*;
//import ru.spbau.mit.exceptions.BadArgumentsException;
//import ru.spbau.mit.execute.Scope;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class Grep extends Command {
//
//    CommandLine result;
//    /**
//     * Creates a new command with the given arguments
//     *
//     * @param arguments
//     */
//    public Grep(List<String> arguments) {
//        super(arguments);
//    }
//
//    @Override
//    public String execute(Scope scope, String inStream) throws IOException, BadArgumentsException {
//        String res = "";
//        int n = 0;
//        if (result.hasOption("A")) {
//            n = Integer.parseInt(result.getOptionValue("A"));
//        }
//        Pattern pattern = Pattern.compile(result.getArgs()[0]);
//        Matcher match;
//
//        if (result.getArgList().size() > 1) {
//            Path file = Paths.get(result.getArgs()[1]);
//            List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
//            int returnLine = 0;
//            for (String line: lines) {
//
//            }
//        }
//    }
//
//    private void parseParameters() throws ParseException {
//        if (arguments.size() > 0)
//        {
//            CommandLineParser parser = new DefaultParser();
//            Options options = new Options();
//            options.addOption("i", "ignore-case", false, "ignore case");
//            options.addOption("w", "word-regexp", false, "only the whole words");
//            options.addOption("A", "--after-context", true, "return n lines after result line");
//
//            String[] data = {"-i", "-A", "3",  "pattern", };
//            result = parser.parse(options, data);
//
//            if (result.hasOption("i")) {
//                result.getArgList().get(0).toLowerCase();
//            }
//            if (result.hasOption("w")) {
//                result.getArgs()[0] = "^" + result.getArgs()[0] + "$";
//            }
//            if (result.hasOption("A")){
//
//            }
//
//        }
//    }
//}
