package db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class kkcBean implements Serializable{
	
	private static String url = "jdbc:postgresql://tokushima.data.ise.shibaura-it.ac.jp:5432/qredb";
	private static String user = "al21020";
	private static String pass = "bond";
	
	
	public int getQuestionCode() {
		Random rand = new Random();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		int MAX = 0;
		int questionCode = 0;
		String sql = "SELECT MAX(questionCode) FROM QI";
		try {
			con = DriverManager.getConnection(url, user ,pass);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				MAX = rs.getInt("MAX");
			}
			questionCode = rand.nextInt(MAX + 1);
		}catch(Exception e){
			throw new IllegalStateException(e);
		}
		if(stmt != null) try {stmt.close();}catch(SQLException ignore){}
		if(con != null) try {con.close();}catch(SQLException ignore) {}
		return questionCode;
	}
	
	public String getQuestion(int questionCode){
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String question = "";
		String sql = "SELECT question FROM QI WHERE questionCode = '" + questionCode + "'";
		try {
			con = DriverManager.getConnection(url, user ,pass);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				question = rs.getString("question");
			}
		}catch(Exception e){
			throw new IllegalStateException(e);
		}
		
		if(stmt != null) try {stmt.close();}catch(SQLException ignore){}
		if(con != null) try {con.close();}catch(SQLException ignore) {}
		return question;
	}
	
	public String getOption(String optionNumber, int questionCode){
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String option = "";
		String sql = "SELECT outcomeOption"+ optionNumber +" FROM QI WHERE questionCode = '" + questionCode + "'";
		try {
			con = DriverManager.getConnection(url, user ,pass);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				option = rs.getString("outcomeOption"+ optionNumber);
			}
		}catch(Exception e){
			throw new IllegalStateException(e);
		}
		if(stmt != null) try {stmt.close();}catch(SQLException ignore){}
		if(con != null) try {con.close();}catch(SQLException ignore) {}
		return option;
	}
	
	public void insertChoice(String c) {
		;
	}
}