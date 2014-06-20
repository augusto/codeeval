package com.codeeval.augusto.stringsubstitution.iterativeandlinkedlist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * iterative solution to the problem using a linked list and a custom entry object.
 * Not super fast as it does multiple passes, and generates extra objects
 */
public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader in = new BufferedReader(new FileReader(file));
        String line;
        while ((line = in.readLine()) != null) {
            String[] lineArray = line.split("\\s");
            if (lineArray.length > 0) {
                for (String string : lineArray) {
                    Main.substitute(string);
                }
            }
        }
    }

    //Here starts the magic
    public static String substitute(String input) {
        String[] split = input.split(";");
        String stringToProcess = split[0];
        String[] replacements= split[1].split(",");

        LinkedList<Entry> initialStep = substitute(stringToProcess, replacements);

        return collectString(initialStep);
    }

    /**
     *
     * @param stringToProcess string to perform the substitution
     * @param replacements array of replacements (even index=string, odd index=replacement)
     * @return
     */

    private static LinkedList<Entry> substitute(String stringToProcess, String[] replacements) {
        LinkedList<Entry> initialStep = new LinkedList<>();
        initialStep.add(new Entry(stringToProcess, false));

        for (int i = 0; i < replacements.length; i += 2) {
            LinkedList<Entry> processed = new LinkedList<>();
            for (Entry entry : initialStep) {
                processed.addAll(entry.replace(replacements[i], replacements[i + 1]));
            }
            initialStep = processed;
//            log(initialStep);
        }
        return initialStep;
    }

    private static String collectString(LinkedList<Entry> initialStep) {
        StringBuilder sb = new StringBuilder();
        for (Entry entry : initialStep) {
            sb.append(entry.getText());
        }

        return sb.toString();
    }


    private static class Entry {
        private final String text;
        private final boolean previouslyModified;

        private Entry(String text, boolean previouslyModified) {
            this.text = text;
            this.previouslyModified = previouslyModified;
        }

        private List<Entry> replace(String pattern, String replacement) {
            if (previouslyModified) {
                return Collections.singletonList(this);
            }

            int nextMatch = text.indexOf(pattern);

            if (nextMatch < 0) {//not found
                return Collections.singletonList(this);
            }

            return performSubstitution(pattern, replacement, nextMatch);
        }

        private List<Entry> performSubstitution(String pattern, String replacement, int initialMatchIndex) {
            List<Entry> newEntries = new ArrayList<>();
            int endOfPreviousIteration = 0;
            int nextMatch = initialMatchIndex;
            while (true) {
                if (endOfPreviousIteration < nextMatch) {
                    newEntries.add(new Entry(text.substring(endOfPreviousIteration, nextMatch), false));
                }
                if (endOfPreviousIteration <= nextMatch) {
                    newEntries.add(new Entry(replacement, true));
                } else {
                    //end of string
                    if (endOfPreviousIteration < text.length()) {
                        newEntries.add(new Entry(text.substring(endOfPreviousIteration), false));
                    }
                    break;
                }

                endOfPreviousIteration = nextMatch + pattern.length();
                nextMatch = text.indexOf(pattern, endOfPreviousIteration);
            }
            return newEntries;
        }

        public String getText() {
            return text;
        }

        @Override
        public String toString() {
            if (previouslyModified)
                return "[" + text + "]";
            else
                return text;
        }
    }

    private static void log(LinkedList<Entry> initialStep) {
        for (Entry entry : initialStep) {
            System.out.print(entry);

        }

        System.out.print("\n");
    }
}
