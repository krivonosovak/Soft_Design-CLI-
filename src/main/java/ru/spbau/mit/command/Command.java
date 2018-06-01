package ru.spbau.mit.command;

import ru.spbau.mit.exceptions.BadArgumentsException;
import ru.spbau.mit.execute.Scope;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Command is an abstract class that provides a single interface for all command in this CLI.
 *
 * Whenever one needs to create a new command, they should create a new class inherited from Command and implement
 * all its abstract methods.
 */
public abstract class Command {

    protected List<String> arguments;

    /**
     * Creates a new command with the given arguments
     */
    public Command(List<String> arguments){
        this.arguments = new ArrayList<>(arguments);
    }

    /**
     * Executes the command in the given scope and with the given input. Returns its output as a result
     */
    public abstract String execute(Scope scope, String inStream) throws IOException, BadArgumentsException;
}
