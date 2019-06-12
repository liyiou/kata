package UnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import StringCalculator.Calculator;

public class CalculatorTest {
	
	// 1. Test simple calculator that takes up two or more numbers separated by comma and return the sum
	@Test
	public void returnZeroOnEmptyInput() {
		assertEquals(0L, Calculator.add(""));
	}
	
	@Test
	public void returnNumberOnOneInput() {
		assertEquals(13L, Calculator.add("13"));
	}
	
	@Test
	public void returnSumOnTwoInputs() {
		assertEquals(27L, Calculator.add("13, 14"));
	}
	
	@Test
	public void returnSumOnMultipleInputs() {
		assertEquals(10L, Calculator.add("1, 2, 3, 4"));	
	}
	
	// 2. Test calculator to handle new lines between numbers, e.g. "1\n2,3"
	@Test
	public void returnSumOnNewlineSeparatedInputs() {
		assertEquals(6L, Calculator.add("1\n2,3"));
	}
	
	@Test
	public void returnSumOnNewlineSeparatedInputsWithEmptyInput() {
		assertEquals(1L, Calculator.add("1,\n"));
	}
	
	// 3. Test customized delimiter at the first line of the string
	@Test
	public void returnSumOnCustomizedDelimiter() {
		assertEquals(3L, Calculator.add("//;\n1;2"));
	}
}

