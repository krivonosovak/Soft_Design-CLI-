package ru.spbau.mit.command;

import ru.spbau.mit.execute.Scope;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Echo echoes everything that comes to it.
 */
public class Echo extends Command{
    public Echo(List<String> arguments)
    {
        super(arguments);
    }

    /**
     * See Command.
     *
     * Echo implementation details: when executed without arguments, echo outputs whatever was passed as its input.
     * Otherwise it contacts all arguments and prints them as a whole.
     * @param scope
     * @param inStream
     * @return
     */
    @Override
    public String execute(Scope scope, String inStream)
    {
        if (arguments.size() == 0) {
            return inStream;
        }
        return String.join(" ", arguments);
    }
}
