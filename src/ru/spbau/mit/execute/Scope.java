package ru.spbau.mit.execute;

import java.util.HashMap;
import java.util.Map;

/**
 * Scope is a plain container that holds values for $variables that are expanded in runtime.
 */
public class Scope {

    private Map<String, String> scope = new HashMap<>();

    /**
     * Registers a new value for the given variable name (key), overwriting the previous value if any.
     * @param key a variable name
     * @param value
     */
    public void add(String key, String value){
        scope.put(key, value);
    }

    /**
     * Returns a binded value for the given variable name (key) or an empty string if no such key exists.
     * @param key a variable name
     * @return value for that variable or empty string
     */
    public String get(String key) {
        if (scope.containsKey(key)){
            return scope.get(key);
        } else {
            return "";
        }
    }
}
