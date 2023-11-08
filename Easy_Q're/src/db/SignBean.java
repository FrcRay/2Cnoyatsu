package db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SignBean implements Serializable{
	static Connection con;
	private String usercode = "";
	private String username = "";
	private String gender = "";
	private String birthday = "";
	private int age;
	private int statuscode;
	
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
	public int getStatuscode() {
		return statuscode;
	}
	public void setStatuscode(int statuscode) {
		this.statuscode = statuscode;
	}
	
	//ユーザ情報をデータベースに登録
	public void insertUserInfo() throws Exception{
		String url = "jdbc:postgresql://tokushima.data.ise.shibaura-it.ac.jp:5432/qredb";
		con = DriverManager.getConnection(url,"al21020","bond");
		
		String sql = "INSERT INTO ui VALUES"
				+ "('" + this.usercode + "','" + this.username + "','" + this.gender + "','" + this.birthday + "','" + this.age + "')";
		
		Statement stmt = con.createStatement();
		stmt.executeUpdate(sql);

		stmt.close();
		con.close();
	}
	
	//ユーザコードが存在するか確認
	public void selectUserCode() throws Exception{
		String url = "jdbc:postgresql:tokushima.data.ise.shibaura-it.ac.jp:5432/qredb";
		con = DriverManager.getConnection(url,"al21020","bond");
		Statement stmt = con.createStatement();
		
		String sql = "SELECT * FROM UI WHERE USERCODE = '" + this.usercode + '\'';
		ResultSet rs = stmt .executeQuery(sql);
		
		//データ取得
		while (rs.next()) {
			this.usercode = rs.getString("usercode");
			this.username = rs.getString("username");
			this.gender = rs.getString("gender");
			this.birthday = rs.getString("birthday");
			this.age = rs.getInt("age");
		}
		con.close();
		stmt.close();
	}
	
	//ユーザコードに結びついているユーザネームを取り出す
	public String selectUserName() throws Exception{
		String url = "jdbc:postgresql:tokushima.data.ise.shibaura-it.ac.jp:5432/qredb";
		con = DriverManager.getConnection(url,"al21020","bond");
		Statement stmt = con.createStatement();
		
		String sql = "SELECT USERNAME FROM USERINFO WHERE ID='" + this.usercode+ '\'';
		ResultSet rs = stmt .executeQuery(sql);
		//データ取得
		String username = "";
		while (rs.next()) {
		    username = rs.getString("USERNAME");
		}
		con.close();
		stmt.close();
		return username;
	}
}
