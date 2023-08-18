package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> substrings = new ArrayList<>();
        StringBuilder currentSubstring = new StringBuilder();

        for (char c : source.toCharArray()) {
            String currentChar = String.valueOf(c);

            if (delimiters.contains(currentChar)) {
                if (currentSubstring.length() > 0) {
                    substrings.add(currentSubstring.toString());
                    currentSubstring = new StringBuilder();
                }
            } else {
                currentSubstring.append(currentChar);
            }
        }

        if (currentSubstring.length() > 0) {
            substrings.add(currentSubstring.toString());
        }

        return substrings;
    }
}
