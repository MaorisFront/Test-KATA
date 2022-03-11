import org.testng.annotations.Test;

import static org.junit.Assert.*;

public class StringCalculatorTest {

    @Test
    public  void shouldReturnZeroOnEmptyString() {
        assertEquals(0, StringCalculator.add(""));
    }

    @Test
    public void shouldReturnSumOnTwoNumbersDelimteByComma() {
        assertEquals(3, StringCalculator.add("1,2"));
    }

    @Test
    public void shouldAcceptNewLineAsValidDelimiter() {
        assertEquals(6, StringCalculator.add("1,2\n3"));
    }

    @Test
    public void shouldAcceptCustomDelimiterSyntax() {
        assertEquals(3, StringCalculator.add("//;\n1;2"));
    }

    @Test
    public void shouldRaiseExceptionOnNegative() {
        try {
            StringCalculator.add("-1,2,3");
            fail("Exception expected.");
        } catch (RuntimeException runtimeException) {
            assertEquals("negatives not allowed", runtimeException.getMessage());
        }
    }
}