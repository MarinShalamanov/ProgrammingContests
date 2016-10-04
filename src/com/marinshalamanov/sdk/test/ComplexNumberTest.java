package com.marinshalamanov.sdk.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.marinshalamanov.sdk.math.ComplexNumber;

public class ComplexNumberTest {

	private final double EPSILON = 1e-6;
	
	@Test
	public void testPow() {
		ComplexNumber c = new ComplexNumber(2, 0);
		
		ComplexNumber res1 = c.pow(2);
		assertEquals(4, res1.getRe(), EPSILON);
		assertEquals(0, res1.getIm(), EPSILON);
		
		ComplexNumber res = c.pow(10);
		assertEquals(1024, res.getRe(), EPSILON);
		assertEquals(0, res.getIm(), EPSILON);
	}
	
	@Test
	public void testMult() {
		ComplexNumber a = new ComplexNumber(0, 1);
		ComplexNumber b = new ComplexNumber(0, 1);
		
		ComplexNumber res = a.mult(b);
		ComplexNumber exp = new ComplexNumber(-1, 0);
		assertEquals(exp, res);
	}
	

}
