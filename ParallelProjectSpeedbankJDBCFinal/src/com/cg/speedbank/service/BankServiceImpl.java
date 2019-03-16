package com.cg.speedbank.service;

import java.sql.SQLException;
import java.util.List;

import com.cg.speedbank.bean.Bank;
import com.cg.speedbank.bean.Transaction;
import com.cg.speedbank.exception.SpeedBankException;
import com.cg.speedbank.repo.BankDaoImpl;
import com.cg.speedbank.repo.IBankDao;

public class BankServiceImpl implements IBankService {
		
			IBankDao ibd = new BankDaoImpl();
	
	@Override
	public int createAccount(Bank b) throws SpeedBankException {
		// TODO Auto-generated method stub
		return ibd.createAccount(b);
		
	}

	@Override
	public double showBalance(Integer cusAccNo) throws SpeedBankException {
		// TODO Auto-generated method stub
		return ibd.showBalance(cusAccNo);
		
	}

	@Override
	public boolean deposit(Integer cusAccNo , long amount) throws SpeedBankException {
		// TODO Auto-generated method stub
		return ibd.deposit(cusAccNo, amount);
		
	}

	@Override
	public boolean withdraw(Integer cusAccNo , long amount) throws SpeedBankException {
		// TODO Auto-generated method stub
		return ibd.withdraw(cusAccNo, amount);
		
	}

	@Override
	public boolean fundTransfer(Integer AccNo1, Integer AccNo2, long amount) throws SpeedBankException {
		// TODO Auto-generated method stub
		return ibd.fundTransfer(AccNo1,AccNo2,amount);
		
	}

	@Override
	public List<Transaction> printTransactions(int accNo) throws SpeedBankException {
		// TODO Auto-generated method stub
		return ibd.printTransactions(accNo);
		
	}
	@Override
	public boolean accHolderValidation(String accHolder) {
		String regex = "[A-Z][a-z]{3,10}";
		if(accHolder == null)
			return false;
	
		return accHolder.matches(regex);
	}
	@Override
	public boolean validatePhoneNumber(String pn) {
		String regEx = "(91|0)?[6-9][0-9]{9}";
		if(pn.matches(regEx))
			return true;
		else
			return false;
	}
	@Override
	public boolean validateEmail(String em) {
		String regEx = "[a-zA-Z0-9]+[@][a-zA-Z0-9]+([.][a-zA-Z]+)+";
		if(em.matches(regEx))
			return true;
		else
			return false;
	}

	
	
	
	

}
