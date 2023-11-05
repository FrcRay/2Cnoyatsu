package db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class InsertBean implements Serializable{
	static Connection con;
	private String usercode = "";
	private String username = "";
	private String gender = "";
	private String birthday = "";
	private int age;
	
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public void createUITable() throws Exception{
		String url = "jdbc:postgresql:tokushima.data.ise.shibaura-it.ac.jp:5432/qredb";
		con = DriverManager.getConnection(url,"al21020","bond");
		
		String sql = "CREATE TABLE " + "UI"+ this.usercode + "("
				+ "USERCODE varchar(5) primary key,"
				+ "USERNAME varchar(10),"
				+ "GENDER char(1),"
				+ "BIRTHDAY char(10),"
				+ "AGE int)";
		Statement stmt = con.createStatement();
		stmt.executeUpdate(sql);
		
		con.close();
		stmt.close();
	}
	
	public void insertUserInfo() throws Exception{
		String url = "jdbc:postgresql:tokushima.data.ise.shibaura-it.ac.jp:5432/qredb";
		con = DriverManager.getConnection(url,"al21020","bond");
		
		String sql = "INSERT INTO UI0 VALUES(?.?.?.?.?)";
		PreparedStatement prestmt = con.prepareStatement(sql);
		
		//データベースに送る情報の設定
		prestmt.setString(1, this.usercode);
		prestmt.setString(2, this.username);
		prestmt.setString(3, this.gender);
		prestmt.setString(3, this.birthday);
		prestmt.setInt(3, this.age);
		
		prestmt.close();
		con.close();
	}
}
