package com.cg.speedbank.repo;

import java.sql.SQLException;
import java.util.List;

import com.cg.speedbank.bean.Bank;
import com.cg.speedbank.bean.Transaction;
import com.cg.speedbank.exception.SpeedBankException;

public interface IBankDao {
	
	public int createAccount(Bank b) throws SpeedBankException;
	
	public double showBalance(Integer cusAccNo) throws SpeedBankException;
	
	public boolean deposit(Integer cusAccNo , long amount) throws SpeedBankException;
	
	public boolean withdraw(Integer cusAccNo , long amount) throws SpeedBankException;
	
	public boolean fundTransfer (Integer AccNo1, Integer AccNo2, long amount) throws SpeedBankException;
	
	public List<Transaction> printTransactions(int accNo) throws SpeedBankException;

}
