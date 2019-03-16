package com.cg.speedbank.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.cg.speedbank.bean.Bank;
import com.cg.speedbank.bean.Transaction;
import com.cg.speedbank.exception.SpeedBankException;
import com.cg.speedbank.service.BankServiceImpl;
import com.cg.speedbank.service.IBankService;

public class TestBank {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
		Scanner sc = new Scanner(System.in);
		IBankService bsi = new BankServiceImpl();
		while (true) {
			System.out.println("\nWelcome to The SPEED BANK\n\n");
			System.out.println("Choose a service :\n" + "1) Create Account\n" + "2) Show Balance\n"
					+ "3) Deposit money to your account\n" + "4) Withdraw money from your account\n"
					+ "5) Transfer funds to another account\n" + "6) Print All transactions of a customer\n" + "7) Exit ");

			int x = sc.nextInt();
			switch (x) {

			case 1: /* CREATE ACCOUNT */
				Bank b = new Bank();
				/* Setting customer details to bank object */
				System.out.println("Enter Customer Bank Details");
				/* REGREX CUSTOMER NAME */
				String name;
				boolean choice;
				do {

					System.out.println(
							"Enter customer name (If you enter wrong number, you will be redirected to enter name again.)");
					name = sc.next();
					choice = bsi.accHolderValidation(name);
					if (choice == true)
						b.setCusName(name);
				} while (choice == false);
				System.out.println("Enter customer address");
				b.setCusAddress(sc.next());
				/* REGREX PHONE NUMBER */
				String pn;
				do {
					System.out.println(
							"Enter customer phone number (If you enter wrong number, you will be redirected to enter phone number again.)");
					pn = sc.next();
					choice = bsi.validatePhoneNumber(pn);
				} while (choice == false);
				b.setCusPhNo(pn);
				// REGREX EMAIL
				String em;
				do {
					System.out.println(
							"Enter customer Email id (If you enter wrong Email, you will be redirected to enter Email again.)");
					em = sc.next();
					choice = bsi.validateEmail(em);
				} while (choice == false);
				b.setCusEmail(em);
				// Setting customer balance to the database
				b.setCusBalance(1000);
				int CustomerAccountid;
				try {
					CustomerAccountid = bsi.createAccount(b);
					System.out.println("Bank Account details have been Set.\n");
					System.out.println("Bonus account balance RS 1000 will be CREDITED!");
					System.out.println("Wait while we generate your Bank Account Number \n");
					/* LOADING DESIGN */
					LoadingDesign ld = new LoadingDesign();
					ld.processing();

					System.out.println("          Your Account Id is : " + CustomerAccountid);
				} catch (SpeedBankException e) {
					System.err.println(e.getMessage());
				}

				break;

			case 2: // Show Balance //
				double bal = 0;
				try {
					System.out.println("Enter account number to retrieve balance ");
					bal = bsi.showBalance(sc.nextInt());
					if (bal != 0.0) {
						System.out.println("The balance in your Account is : " + bal);
					}

				} catch (SpeedBankException e) {
					System.err.println(e.getMessage());
				}

				break;

			case 3: // Deposit money to your account //
				try {
					System.out.println("Enter Account number and Amount to be deposited(AccNo-enter-Amount)");
					bsi.deposit(sc.nextInt(), sc.nextLong());
				} catch (SpeedBankException e) {
					System.err.println(e.getMessage());
				}
				System.out.println("The money has been deposited to your account!");
				break;

			case 4: // Withdraw money from your account //
				try {
					System.out.println("Enter Account number and Amount to be withdrawn(AccNo-enter-Amount)");
					bsi.withdraw(sc.nextInt(), sc.nextLong());
				} catch (SpeedBankException e) {
					System.err.println(e.getMessage());
				}
				System.out.println("The money has been withdrawn from your account!");
				break;

			case 5: // Transfer funds to another account //
				System.out.println("Enter Details required for fund transaction\n");
				try {
					System.out.println("Enter source account number : ");
					int souAcc = sc.nextInt();
					System.out.println("Enter beneficiary account number : ");
					int benAcc = sc.nextInt();
					System.out.println("Enter amount to be transferred : ");
					long amt = sc.nextLong();
					bsi.fundTransfer(souAcc, benAcc, amt);
					System.out.println("The transaction has been carried out Successfully.");
				} catch (SpeedBankException e) {
					System.err.println(e.getMessage());
				}
				break;

			case 6: // Print your all transactions //
				try {
					System.out.println(
							"You have asked for details about your transactions! This piece of information is private!");
					System.out.println("Enter Password to access details of previous transactions.");
					String setPass = "root";
					String pass = sc.next();
					if (pass.equals(setPass)) {
						System.out.println("Enter your Account Number");
						List<Transaction> transactions = bsi.printTransactions(sc.nextInt());
						System.out.println(transactions);

					} else {
						System.out.println(
								"Password entered by you is WRONG! You will be directed back to the main menu");

					}
				} catch (SpeedBankException e) {
					System.err.println(e.getMessage());
				}
				break;
			case 7: // Exit //
				System.out.println("Thankyou for choosing SpeedBank for your banking services needs.");
				System.exit(0);
				break;
			default:// default
				System.out.println("You have entered an INVALID option. Please try again.");
				break;

			}
		}
	}
}
