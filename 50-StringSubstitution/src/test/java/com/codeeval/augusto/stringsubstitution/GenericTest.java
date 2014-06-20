package com.codeeval.augusto.stringsubstitution;

import com.codeeval.augusto.stringsubstitution.iterativeandlinkedlist.Main;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public abstract class GenericTest {

    protected abstract  String invoke(String input);


    @Test
    public void replace_shouldReplace() {
        String input = "10011011001;0110,1001,1001,0,10,11";

        assertThat(invoke(input), equalTo("11100110"));
    }

    @Test
    public void replace_shouldReplaceAll() {
        String input = "0110;0110,1001";

        assertThat(invoke(input), equalTo("1001"));
    }

    @Test
    public void replace_shouldReplaceNothing() {
        String input = "0110;0111,1001";

        assertThat(invoke(input), equalTo("0110"));
    }

    @Test
    public void replace_shouldWorkWithEmptyStrings() {
        String input = ";1,0";

        assertThat(invoke(input), equalTo(""));
    }

    @Test
    public void replace_shouldWorkWithAllZeros() {
        String input = "00000;00,0";

        assertThat(invoke(input), equalTo("000"));
    }

    @Test
    public void replace_shouldWorkWithAllZerosReplacement() {
        String input = "000000;00,0";

        assertThat(invoke(input), equalTo("000"));
    }
}
