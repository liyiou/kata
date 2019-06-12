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
	
	@Test
	public void returnSumOnMultipleInputs() throws Exception {
		assertEquals(10L, Calculator.add("1, 2, 3, 4"));	
	}
	
	// 2. Test calculator to handle new lines between numbers, e.g. "1\n2,3"
	@Test
	public void returnSumOnNewlineSeparatedInputs() throws Exception {
		assertEquals(6L, Calculator.add("1\n2,3"));
	}
	
	@Test
	public void returnSumOnNewlineSeparatedInputsWithEmptyInput() throws Exception {
		assertEquals(1L, Calculator.add("1,\n"));
	}
	
	// 3. Test customized delimiter at the first line of the string
	@Test
	public void returnSumOnCustomizedDelimiter() throws Exception {
		assertEquals(3L, Calculator.add("//;\n1;2"));
	}
	
	// 4. Calling Add with a negative number will throw an exception “negatives not allowed” 
	@Test
	public void throwExceptionOnNegativeInputs() {
		try {
			Calculator.add("-1, -3");
			fail("Exception expected.");
		} catch (Exception e) {
			assertThat(e.getMessage(), is("Negative not allowed : -1 -3"));
		}
	}
	
	// 5. Ignore input bigger than 1000
	@Test
	public void ignoreInputBiggerThan1000() throws Exception {
		assertEquals(2L, Calculator.add("2, 1001"));
	}
	
}

