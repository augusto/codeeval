package com.codeeval.augusto.stringsubstitution.iterativeandlinkedlist;

import com.codeeval.augusto.stringsubstitution.GenericTest;


public class IterativeAndLinkedListTest extends GenericTest {
    @Override
    protected String invoke(String input) {
        return Main.substitute(input);
    }
}
