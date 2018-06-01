package ru.spbau.mit.command;

import ru.spbau.mit.execute.Scope;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ExternalProcess is a special meta-command that tries to execute an external program by its name
 */
public class ExternalProcess extends Command {

    public ExternalProcess(List<String> arguments) {
        super(arguments);
    }

    /**
     * When executed this command tries to to execute an external program by its name (passed as the first argument)
     * Other arguments are interpreted as arguments for this external program. Its output is then returned as an output
     * of this command.
     */
    @Override
    public String execute(Scope scope, String inStream) throws IOException {
        ProcessBuilder probuilder = new ProcessBuilder(arguments);
        Process process = probuilder.start();

        OutputStream os = process.getOutputStream();
        PrintStream printStream = new PrintStream(os);
        printStream.print(inStream);
        printStream.close();

        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        return br.lines().collect(Collectors.joining("\n"));
    }
}
