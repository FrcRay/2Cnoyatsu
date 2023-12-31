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
	
	
	public int getQuestionCode(int userCode) {
		Random rand = new Random();
		Connection con = null;
		Statement stmt = null;
		ResultSet MaxRs = null;
		ResultSet flgRs = null;
		int MAX = 0;
		int questionCode = 0;
		String MaxSql = "SELECT MAX(questionCode) FROM QI";
		String SelectSql = "";
		try {
			con = DriverManager.getConnection(url, user ,pass);
			stmt = con.createStatement();
			MaxRs = stmt.executeQuery(MaxSql);
			while (MaxRs.next()) {
				MAX = MaxRs.getInt("MAX");
			}
		}catch(Exception e){
			throw new IllegalStateException(e);
		}
		for(int i=0;i <= MAX;i++) {
			questionCode = rand.nextInt(MAX + 1);
			SelectSql = "SELECT alMade FROM QUCODE WHERE questionCode = '"+ questionCode +"' AND userCode = '"+ userCode +"'";
			try {
				flgRs = stmt.executeQuery(SelectSql);
				if(flgRs.next()) {
				}else {
					if(stmt != null) try {stmt.close();}catch(SQLException ignore){}
					if(con != null) try {con.close();}catch(SQLException ignore) {}
					return questionCode;
				}
			}catch(Exception e){
				throw new IllegalStateException(e);
			}
		}
		if(stmt != null) try {stmt.close();}catch(SQLException ignore){}
		if(con != null) try {con.close();}catch(SQLException ignore) {}
		return -1;
	}
	
	public String[] getQuestion(int questionCode){
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String[] question;
		question = new String[4];
		String sql = "SELECT * FROM QI WHERE questionCode = '" + questionCode + "'";
		try {
			con = DriverManager.getConnection(url, user ,pass);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				question[0] = rs.getString("question");
				question[1] = rs.getString("option1");
				question[2] = rs.getString("option2");
				question[3] = rs.getString("option3");
			}
		}catch(Exception e){
			throw new IllegalStateException(e);
		}
		
		if(stmt != null) try {stmt.close();}catch(SQLException ignore){}
		if(con != null) try {con.close();}catch(SQLException ignore) {}
		return question;
	}
	
	public void selectOption(int userCode, int option, int questionCode) {
		Connection con = null;
		Statement stmt = null;
		boolean alMade = false;
		String sql = "INSERT INTO QUCODE VALUES('"+ questionCode +"' , '"+ userCode +"' , '"+ alMade +"' , ' "+ option +"' )";
		try {
			con = DriverManager.getConnection(url, user ,pass);
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
		}catch(Exception e){
			throw new IllegalStateException(e);
		}
		if(stmt != null) try {stmt.close();}catch(SQLException ignore){}
		if(con != null) try {con.close();}catch(SQLException ignore) {}
	}
}