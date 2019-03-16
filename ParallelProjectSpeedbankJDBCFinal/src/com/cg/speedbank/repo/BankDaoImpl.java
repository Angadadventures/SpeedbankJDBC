package com.cg.speedbank.repo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.cg.speedbank.bean.Bank;
import com.cg.speedbank.bean.Transaction;
import com.cg.speedbank.exception.SpeedBankException;
import com.cg.speedbank.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BankDaoImpl implements IBankDao {

	private Connection con = null;
	private PreparedStatement ps = null;
	private Date utilDate;

	@Override
	public int createAccount(Bank b) throws SpeedBankException {

		try {
			con = ConnectionFactory.getSigtonObj().getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			throw new SpeedBankException("Connection Issue " + e.getMessage());
		}

		int id;
		try {
			ps = con.prepareStatement(QueryMapping.INSERT_QUERY);
			ps.setString(1, b.getCusName());
			ps.setString(2, b.getCusAddress());
			ps.setString(3, b.getCusPhNo());
			ps.setString(4, b.getCusEmail());
			ps.setLong(5, b.getCusBalance());
			ps.executeUpdate();
			
			ps = con.prepareStatement(QueryMapping.SEQ_CURR_VAL);
			ResultSet rs = ps.executeQuery();
			rs.next();
			id = rs.getInt(1);
			
		} catch (Exception e) {
			throw new SpeedBankException("Database Issue " + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				System.out.println("Problem in closing action " + e.getMessage());
			}
		}
		return id;
	}

	@Override
	public double showBalance(Integer cusAccNo) throws SpeedBankException {

		try {
			con = ConnectionFactory.getSigtonObj().getConnection();
		} catch (Exception e) {
			System.out.println("Connection Issue " + e.getMessage());
		}

		double bal = 0;
		try {
			ps = con.prepareStatement(QueryMapping.SELECT_QUERY_BALANCE + " " + cusAccNo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			{
				bal = (rs.getLong(1));
			}
			con.close();
		} catch (Exception e) {
			System.out.println("Database Issue " + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				System.out.println("Problem in closing action " + e.getMessage());
			}
		}
		return bal;

	}

	@Override
	public boolean deposit(Integer cusAccNo, long amount) throws SpeedBankException {

		try {
			con = ConnectionFactory.getSigtonObj().getConnection();
		} catch (Exception e) {
			System.out.println("Connection Issue " + e.getMessage());
		}

		try {
			long bal1 = 0;
			ps = con.prepareStatement(QueryMapping.SELECT_QUERY_BALANCE + "" + cusAccNo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			bal1 = rs.getLong(1);

			bal1 = (bal1 + amount);
			ps = con.prepareStatement(QueryMapping.UPDATE_QUERY_BALANCE);
			ps.setLong(1, bal1);
			ps.setInt(2, cusAccNo);
			ps.executeUpdate();

			ps = con.prepareStatement(QueryMapping.INSERT_QUERY_TRANSACTION);
			ps.setString(1, "Deposited!!");
			ps.setString(2, Date.valueOf(LocalDate.now()).toString());
			ps.setDouble(3, amount);
			ps.setInt(4, cusAccNo);
			ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println("Database Issue " + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				System.out.println("Problem in closing action " + e.getMessage());
			}
		}

		return true;
	}

	@Override
	public boolean withdraw(Integer cusAccNo, long amount) throws SpeedBankException {

		try {
			con = ConnectionFactory.getSigtonObj().getConnection();
		} catch (Exception e) {
			System.out.println("Connection Issue " + e.getMessage());
		}

		try {
			long bal1 = 0;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(QueryMapping.SELECT_QUERY_BALANCE + "" + cusAccNo);
			rs.next();
			bal1 = rs.getLong(1);

			bal1 = (bal1 - amount);
			ps = con.prepareStatement(QueryMapping.UPDATE_QUERY_BALANCE);
			ps.setLong(1, bal1);
			ps.setInt(2, cusAccNo);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Database Issue " + e.getMessage());
		}

		try {
			ps = con.prepareStatement(QueryMapping.INSERT_QUERY_TRANSACTION);
			ps.setString(1, "Withdrawn");
			ps.setString(2, Date.valueOf(LocalDate.now()).toString());
			ps.setDouble(3, amount);
			ps.setInt(4, cusAccNo);
			ps.executeUpdate();

		} catch (Exception e) {
			System.out.println("Database Issue :" + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				System.out.println("Problem in closing action " + e.getMessage());
			}
		}

		return true;
	}

	@Override
	public boolean fundTransfer(Integer AccNo1, Integer AccNo2, long amount) throws SpeedBankException {

		try {
			con = ConnectionFactory.getSigtonObj().getConnection();
		} catch (Exception e) {
			System.out.println("Connection Issue " + e.getMessage());
		}

		try {
			withdraw(AccNo1, amount);
			deposit(AccNo2, amount);
			con.close();
		} catch (Exception e) {
			System.out.println("Database Issue " + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				System.out.println("Problem in closing action " + e.getMessage());
			}
		}

		return true;
	}

	@Override
	public List<Transaction> printTransactions(int accNo) throws SpeedBankException {

		try {
			con = ConnectionFactory.getSigtonObj().getConnection();
		} catch (Exception e) {
			System.out.println("Connection Issue :" + e.getMessage());
		}
		List<Transaction> transactions = null;

		try {
			ps = con.prepareStatement(QueryMapping.SELECT_QUERY_TRANSACTION);
			ps.setLong(1, accNo);
			ResultSet rs = ps.executeQuery();
			transactions = new ArrayList<Transaction>();
			while (rs.next()) {
				Transaction t = new Transaction();
				t.setType(rs.getString(1));
				t.setDate(rs.getDate(2).toLocalDate());
				t.setAmount(rs.getDouble(3));
				t.setAccNo(rs.getInt(4));
				transactions.add(t);
			}

		} catch (Exception e) {
			System.out.println("Database Issue " + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				System.out.println("Problem in closing action " + e.getMessage());
			}
		}

		return transactions;

	}

}
