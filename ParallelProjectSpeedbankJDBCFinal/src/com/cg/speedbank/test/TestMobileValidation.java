package com.cg.speedbank.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.cg.speedbank.service.BankServiceImpl;
import com.cg.speedbank.service.IBankService;

public class TestMobileValidation {
	IBankService service = new BankServiceImpl();
	@Test//Phone number starting from 7 and 10 digits
	public void testRightMail() {
		String pn = "7696590232";
		assertTrue(service.validatePhoneNumber(pn));
	}
	
	@Test//Phone number with 9 digits
	public void testRightMail2() {
		String pn = "836608880";
		assertFalse(service.validatePhoneNumber(pn));
	}
	
	@Test//Phone number with starting from 4 of 10 digits
	public void testWrongMail() {
		String pn = "4087655432";
		assertFalse(service.validatePhoneNumber(pn));
	}
	
	@Test //Phone number with 11 digits
	public void testWrongMail2() {
		String pn = "98567654314";
		assertFalse(service.validatePhoneNumber(pn));
	}
	
	@Test // Phone number starting with O
	public void testWrongMail3() {
		String pn = "0765465321";
		assertFalse(service.validatePhoneNumber(pn));
	}
	
}
