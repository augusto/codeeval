package com.codeeval.augusto.stringsubstitution.recursivewithregex;

import com.codeeval.augusto.stringsubstitution.GenericTest;

public class Recursive extends GenericTest {

    @Override
    protected String invoke(String input) {
        return Main.substitute(input);
    }
}