package db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class kkcBean implements Serializable{
	
	static Connection con;
	private String url = "jdbc:postgresql://tokushima.data.ise.shibaura-it.ac.jp:5432/qredb";
	private String user = "al21016";
	private String pass = "bond";
	private String choice = "test";
	
	
	public String getQuestion() throws Exception{
		con = DriverManager.getConnection(url, user ,pass);
		Statement stmt = con.createStatement();
		String AN = "00001";
		String sql = "SELECT アンケート FROM questionnaire WHERE アンケートコード ='" + AN +"'";
		ResultSet rs = stmt .executeQuery(sql);
		
		String question = "";
		while (rs.next()) {
		    question = rs.getString("アンケート");
		}
		con.close();
		stmt.close();
		return question;
	}
	
	public String getChoices(int n) {
		return choice;
	}
	
	public void insertChoice(String c) {
		;
	}
}