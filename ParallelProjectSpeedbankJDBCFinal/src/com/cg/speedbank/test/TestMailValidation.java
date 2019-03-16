package com.cg.speedbank.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.cg.speedbank.service.BankServiceImpl;
import com.cg.speedbank.service.IBankService;

public class TestMailValidation {
	
	IBankService service = new BankServiceImpl();
	@Test
	public void testRightMail() {
		String mail = "a@gmail.com";
		assertTrue(service.validateEmail(mail));
	}
	
	@Test // start with number
	public void testRightMail2() {
		String mail = "123@gmail.com";
		assertTrue(service.validateEmail(mail));
	}
	
	@Test // not minimum character before '@'
	public void testWrongMail() {
		String mail = "@gmail.com";
		assertFalse(service.validateEmail(mail));
	}
	
	@Test // no '@' 
	public void testWrongMail2() {
		String mail = "agmail.com";
		assertFalse(service.validateEmail(mail));
	}
	
	@Test // without (.) dot 
	public void testWrongMail3() {
		String mail = "a@gmailcom";
		assertFalse(service.validateEmail(mail));
	}
	

}
