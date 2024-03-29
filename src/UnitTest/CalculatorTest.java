package UnitTest;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

import StringCalculator.Calculator;

public class CalculatorTest {

	// 1. Test simple calculator that takes up two or more numbers separated by comma and return the sum
	@Test
	public void returnZeroOnEmptyInput() throws Exception {
		assertEquals(0L, Calculator.add(""));
	}
	
	@Test
	public void returnNumberOnOneInput() throws Exception {
		assertEquals(13L, Calculator.add("13"));
	}
	
	@Test
	public void returnSumOnTwoInputs() throws Exception {
		assertEquals(27L, Calculator.add("13, 14"));
	}
	
	// 2. Allow unknow amount of numbers
	
	@Test
	public void returnSumOnMultipleInputs() throws Exception {
		assertEquals(10L, Calculator.add("1, 2, 3, 4"));	
	}
	
	// 3. Test calculator to handle new lines between numbers, e.g. "1\n2,3"
	@Test
	public void returnSumOnNewlineSeparatedInputs() throws Exception {
		assertEquals(6L, Calculator.add("1\n2,3"));
	}
	
	@Test
	public void returnSumOnNewlineSeparatedInputsWithEmptyInput() throws Exception {
		assertEquals(1L, Calculator.add("1,\n"));
	}
	
	// 4. Test customized delimiter at the first line of the string
	@Test
	public void returnSumOnCustomizedDelimiter() throws Exception {
		assertEquals(3L, Calculator.add("//;\n1;2"));
	}
	
	// 5. Calling Add with a negative number will throw an exception �negatives not allowed� 
	@Test
	public void throwExceptionOnNegativeInputs() {
		try {
			Calculator.add("-1, -3");
			fail("Exception expected.");
		} catch (Exception e) {
			assertThat(e.getMessage(), is("Negative not allowed : -1 -3"));
		}
	}
	
	// 6. Ignore input bigger than 1000
	@Test
	public void ignoreInputBiggerThan1000() throws Exception {
		assertEquals(2L, Calculator.add("2, 1001"));
	}
	
	// 7. Delimiters can be of any length with the following format: �//[delimiter]\n� 
	@Test
	public void delimiterCanBeOfAnyLength() throws Exception {
		assertEquals(6L, Calculator.add("//[***]\n1***2***3"));
	}
	
	@Test
	public void delimiterCanBeOfAnyLengthDiffChar() throws Exception {
		try {
			Calculator.add("//[*&*]\n1*&*2**3");
			fail("Number format exception expected.");
		} catch (Exception e) {
			assertThat(e.getMessage(), is("Illegal number format detected in inputs: 2**3"));
		}
	}
	
	@Test
	public void emptyDelimiterIsNotAllowed() throws Exception {
		try {
			Calculator.add("//\n1*&*2**3");
			fail("Number format exception expected.");
		} catch (Exception e) {
			assertThat(e.getMessage(), is("Empty delimiter is not allowed."));
		}
	}
	
	@Test
	public void emptyDelimiterEnclosedByBracketIsNotAllowed() throws Exception {
		try {
			Calculator.add("//[]\n1*&*2**3");
			fail("Number format exception expected.");
		} catch (Exception e) {
			assertThat(e.getMessage(), is("Empty delimiter is not allowed."));
		}
	}
	
	// 8. Allow multiple delimiters like this: �//[delim1][delim2]\n� 
	@Test
	public void multipleDelimiters() throws Exception {
		assertEquals(6L, Calculator.add("//[*][%]\n1*2%3"));
	}
	
	// 9. Also handle multiple delimiters with length longer than one char
	@Test
	public void multipleDelimitersWithMultipleChars() throws Exception {
		assertEquals(6L, Calculator.add("//[***][%%&]\n1***2%%&3"));
	}
}

