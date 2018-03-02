package ru.spbau.mit.command;

import ru.spbau.mit.execute.Scope;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Exit is a special meta-command that interrupts CLI and aborts all other commands.
 */
public class Exit extends Command{

    public Exit(List<String> arguments) {
        super(arguments);
    }

    /**
     * Executing this command means an immediate termination of the application.
     * @param scope ignored
     * @param inStream ignored
     * @return does not actually return
     */
    @Override
    public String execute(Scope scope, String inStream) {
        System.exit(1);
        return "";
    }
}
