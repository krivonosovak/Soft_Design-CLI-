package ru.spbau.mit.parse;

import ru.spbau.mit.execute.Scope;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Lexer is responsible for "expanding" existing tokens by replacing $variables with their values defined in the
 * given scope.
 */
public class Lexer {

    /**
     * This method expands the given tokens by replacing all $variable tokens by their corresponding values from
     * the given scope. The replacement does not occur if a $variable token is actually a part of a string enclosed
     * in single quotes. If the value of any $variable is undefined at the moment of replacement it is replaced by
     * an empty string.
     * @param scope a current scope in which expantion should occur
     * @param tokens a list of tokenized strings from Parser
     * @return a list of expanded tokens
     */
    public List<String> expand(Scope scope, List<String> tokens) {

        List<String> result = new ArrayList<String>();
        Pattern pattern = Pattern.compile("(\\$([\\w]+))");

        for (String token: tokens){

            String newToken = token;

            if (token.charAt(0) == '\'') {
                newToken = newToken.substring(1, newToken.length() - 1);
                result.add(newToken);
                continue;
            }

            Matcher matcher = pattern.matcher(token);
            while (matcher.find()) {
                String value_x = scope.get(matcher.group(2));
                newToken = newToken.replace(matcher.group(1), value_x);
            }

            if (token.charAt(0) ==  '"'){
                newToken = newToken.substring(1, newToken.length() - 1);
            }
            result.add(newToken);
        }
        return result;
    }
}
