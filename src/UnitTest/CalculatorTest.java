package UnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import StringCalculator.Calculator;

public class CalculatorTest {
	
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
	
}
