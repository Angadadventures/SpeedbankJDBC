package com.cg.speedbank.repo;

public class QueryMapping {

	public static final String INSERT_QUERY = "INSERT INTO Bank VALUES(accno.nextval,?,?,?,?,?)";
	public static final String SEQ_CURR_VAL="SELECT accno.currval FROM dual";
	public static final String SELECT_QUERY_BALANCE = "SELECT balance FROM Bank WHERE AccNo=";
	public static final String UPDATE_QUERY_BALANCE = "update Bank set balance=? where ACCNO=?";
	public static final String INSERT_QUERY_TRANSACTION = "Insert into Transaction values(?,?,?,?)";
	public static final String SELECT_QUERY_TRANSACTION ="select * from Transaction where accno=?";
}
