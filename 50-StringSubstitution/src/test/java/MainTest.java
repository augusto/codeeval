
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by Augusto on 20/06/2014.
 */
public class MainTest {

    @Test
    public void replace_shouldReplace() {
        String input = "10011011001;0110,1001,1001,0,10,11";

        Main stringSubstitutor = new Main(input);

        assertThat(stringSubstitutor.substitute(), equalTo("11100110"));
    }

    @Test
    public void replace_shouldReplaceAll() {
        String input = "0110;0110,1001";

        Main stringSubstitutor = new Main(input);

        assertThat(stringSubstitutor.substitute(), equalTo("1001"));
    }

    @Test
    public void replace_shouldReplaceNothing() {
        String input = "0110;0111,1001";

        Main stringSubstitutor = new Main(input);

        assertThat(stringSubstitutor.substitute(), equalTo("0110"));
    }

    @Test
    public void replace_shouldWorkWithEmptyStrings() {
        String input = ";1,0";

        Main stringSubstitutor = new Main(input);

        assertThat(stringSubstitutor.substitute(), equalTo(""));
    }

    @Test
    public void replace_shouldWorkWithAllZeros() {
        String input = "00000;00,0";

        Main stringSubstitutor = new Main(input);

        assertThat(stringSubstitutor.substitute(), equalTo("000"));
    }

    @Test
    public void replace_shouldWorkWithAllZerosReplacement() {
        String input = "000000;00,0";

        Main stringSubstitutor = new Main(input);

        assertThat(stringSubstitutor.substitute(), equalTo("000"));
    }
}
