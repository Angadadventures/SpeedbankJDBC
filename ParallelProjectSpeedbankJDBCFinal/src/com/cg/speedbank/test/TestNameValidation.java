package com.cg.speedbank.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.cg.speedbank.service.BankServiceImpl;
import com.cg.speedbank.service.IBankService;

public class TestNameValidation {
	
	IBankService service = new BankServiceImpl();
	@Test
	public void test1() {
		String name = "guju";
		assertFalse(service.accHolderValidation(name));
	}
	
	@Test
	public void test2() {
		String name = "Guju";
		assertTrue(service.accHolderValidation(name));
	}
	
	@Test
	public void test3() {
		String name = "Guju1";
		assertFalse(service.accHolderValidation(name));
	}
	
	@Test
	public void test4() {
		String name = "12345";
		assertFalse(service.accHolderValidation(name));
	}
	
	@Test
	public void test5() {
		String name = "jsdfbvasjkdbf";
		assertFalse(service.accHolderValidation(name));
	}
	
}
