package ru.spbau.mit.execute;

import ru.spbau.mit.command.Command;
import ru.spbau.mit.exceptions.BadArguments;


import java.io.IOException;
import java.util.List;

/**
 * The main purpose of Executor is to create a chain (a pipeline) of commands and wire their inputs and outputs together
 */
public class Executor {

    /**
     * This method executes the given list of commands in the given scope by chaining them into a single pipeline
     * and wiring one command's output to the next command's input. The last command's output is returned to the caller
     * as a result of the whole command pipline run.
     * @param scope the current CLI scope
     * @param chainOfCommand a list of command to be executed in this particular order
     * @return an output of the last command (if any)
     */
    public String exec(Scope scope, List<Command> chainOfCommand) throws IOException, BadArguments {
        String outPut = "";
        for (Command command: chainOfCommand){
            outPut = command.execute(scope, outPut);
        }
        return outPut;
    }
}
