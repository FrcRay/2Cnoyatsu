package db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class kkcBean implements Serializable{
	
	private static String url = "jdbc:postgresql://tokushima.data.ise.shibaura-it.ac.jp:5432/qredb";
	private static String user = "al21016";
	private static String pass = "bond";
	
	
	public static Connection getConnection(){
		try {
			Connection con = null;
			con = DriverManager.getConnection(url, user ,pass);
			return con;
		}catch(Exception e) {
			throw new IllegalStateException(e);
		}
	}
	
	public String getQuestion(){
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String Q = "";
		String sql = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Q = rs.getString("アンケート内容");
			}
		}catch(Exception e){
			throw new IllegalStateException(e);
		}	
		if(stmt != null) try {stmt.close();}catch(SQLException ignore){}
		if(con != null) try {con.close();}catch(SQLException ignore) {}
		return Q;
	}
	
	public String getChoices(String n) throws Exception{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String choice = "";
		String sql = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				choice = rs.getString("アンケート内容");
			}
		}catch(Exception e){
			throw new IllegalStateException(e);
		}
		if(stmt != null) try {stmt.close();}catch(SQLException ignore){}
		if(con != null) try {con.close();}catch(SQLException ignore) {}
		return choice;
	}
	
	public void insertChoice(String c) {
		;
	}
}