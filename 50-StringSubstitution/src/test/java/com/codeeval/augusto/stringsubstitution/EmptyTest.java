package com.codeeval.augusto.stringsubstitution;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Created by Augusto on 20/06/2014.
 */
public class EmptyTest {

    @Test
    public void testTruth() {
        MatcherAssert.assertThat(true, Matchers.is(true));
    }
}
