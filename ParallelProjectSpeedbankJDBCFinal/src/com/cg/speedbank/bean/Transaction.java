package com.cg.speedbank.bean;

import java.time.LocalDate;

public class Transaction {
		double amount ;
		String type;
		LocalDate date;
		double accNo;
		
		public double getAccNo() {
			return accNo;
		}
		public void setAccNo(double accNo) {
			this.accNo = accNo;
		}
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public LocalDate getDate() {
			return date;
		}
		public void setDate(LocalDate date) {
			this.date = date;
		}
		@Override
		public String toString() {
			return "Transaction [amount=" + amount + ", type=" + type + ", date=" + date + "]";
		}
		
		
}
