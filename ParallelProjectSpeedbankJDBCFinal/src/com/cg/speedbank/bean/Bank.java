package com.cg.speedbank.bean;

import java.util.ArrayList;

public class Bank {		
	private static String bankName;
	private int cusAccNo;
	private String cusName;
	private long cusBalance;
	private String cusAddress;
	private String cusEmail;
	private String cusPhNo;
	private ArrayList<Transaction> trans = new ArrayList<>();
	public static String getBankName() {
		return bankName;
	}
	public static void setBankName(String bankName) {
		Bank.bankName = bankName;
	}
	public int getCusAccNo() {
		return cusAccNo;
	}
	public void setCusAccNo(int cusAccNo) {
		this.cusAccNo = cusAccNo;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public long getCusBalance() {
		return cusBalance;
	}
	public void setCusBalance(long cusBalance) {
		this.cusBalance = cusBalance;
	}
	public String getCusAddress() {
		return cusAddress;
	}
	public void setCusAddress(String cusAddress) {
		this.cusAddress = cusAddress;
	}
	public String getCusEmail() {
		return cusEmail;
	}
	public void setCusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
	}
	public String getCusPhNo() {
		return cusPhNo;
	}
	public void setCusPhNo(String cusPhNo) {
		this.cusPhNo = cusPhNo;
	}
	
	public ArrayList<Transaction> getTrans() {
		return trans;
	}
	public void setTrans(Transaction trans) {
		this.trans.add(trans);
	}
	@Override
	public String toString() {
		return "Bank [cusAccNo=" + cusAccNo + ", cusName=" + cusName + ", cusBalance=" + cusBalance + ", cusAddress="
				+ cusAddress + ", cusEmail=" + cusEmail + ", cusPhNo=" + cusPhNo + "]";
	}
	
	
	
	
}
