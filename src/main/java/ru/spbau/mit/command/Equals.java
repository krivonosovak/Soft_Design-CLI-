package ru.spbau.mit.command;

import ru.spbau.mit.execute.Scope;

import java.util.List;

/**
 * Equals is a special meta-command that is responsible for manipulating the CLI scope. Equals is not supposed to
 * be used as a part of piped expression.
 */
public class Equals extends Command{

    private String var;
    private String value;

    /**
     * Equals takes exactly 2 arguments: the first one is a variable name and the second one is a new value to be
     * binded to this variable
     */
    public Equals(List<String> arguments)  {
        super(arguments);
        var = this.arguments.get(0);
        value = this.arguments.get(1);
    }

    /**
     * Modifies the given scope by registering the new value for the given variable. Always returns an empty string and
     * does not read anything from input.
     */
    @Override
    public String execute(Scope scope, String inStream)
    {
        scope.add(var, value);
        return "";
    }
}
