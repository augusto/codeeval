package com.codeeval.augusto.stringsubstitution.recursivewithregex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                    System.out.println(Main.substitute(string));
                }
            }
        }
    }

    /**
     * not so pretty logic, but I precompute the Patterns to use later in the real substitution
     * @param input
     * @return
     */
    public static String substitute(String input) {
        String[] split = input.split(";");
        String stringToProcess = split[0];
        String[] substitutionRules = split[1].split(",");

        StringBuilder sb = new StringBuilder();
        Object[] substitutionPatterns = new Object[substitutionRules.length];
        for( int i = 0 ; i < substitutionRules.length ; i+=2) {
            substitutionPatterns[i] = Pattern.compile("("+substitutionRules[i]+")");
            substitutionPatterns[i+1] = substitutionRules[i+1];
        }

        substitute(sb, stringToProcess, substitutionPatterns, 0);
        return sb.toString();
    }

    /**
     * Java has no language support for pattern matching (like scala or ruby) so I end up using the old Regex api.
     *
     * @param sb  string builder to collect the final string
     * @param input text to process
     * @param substitutions list of all substitutions (even=Pattern, odd=string to replace)
     * @param substitutionIndex index in the current substitution
     */
    private static void substitute(StringBuilder sb, String input, Object[] substitutions, int substitutionIndex) {
        if(  (substitutionIndex < substitutions.length) == false) {
            sb.append(input);
            return;
        }

        Pattern pattern = (Pattern) substitutions[substitutionIndex];
        String replaceWith = (String) substitutions[substitutionIndex+1];

        Matcher m = pattern.matcher(input);
        int pos = 0;
        while (m.find()) {
            //execute substitutions on the text before the actual mach
            if (pos != m.start()) {
                String substring = input.substring(pos, m.start());
                substitute(sb, substring, substitutions, substitutionIndex + 2);
            }

            //replace the actual match.
            sb.append(replaceWith);
            pos = m.end();
        }
        //execute substitutions on the rest of the input
        if (pos != input.length()) {
            String substring = input.substring(pos);
            substitute(sb, substring, substitutions, substitutionIndex+2);
        }
    }

    private static void log(String s) {
        System.out.println(s);
    }
}
