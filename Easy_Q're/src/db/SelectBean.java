package db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectBean implements Serializable{
	static Connection con;
	private int statuscode;
	
	public int getStatuscode() {
		return statuscode;
	}
	public void setStatuscode(int statuscode) {
		this.statuscode = statuscode;
	}
	
	public void selectQreCode() throws Exception{

		String url = "jdbc:postgresql:tokushima.data.ise.shibaura-it.ac.jp:5432/qredb";
		con = DriverManager.getConnection(url,"al21020","bond");
		Statement stmt = con.createStatement();
	}
	
	//ユーザコードが存在するか確認
	public void selectUserCode(String uc) throws Exception{
		String url = "jdbc:postgresql:tokushima.data.ise.shibaura-it.ac.jp:5432/qredb";
		con = DriverManager.getConnection(url,"al21020","bond");
		Statement stmt = con.createStatement();
		
		String sql = "SELECT ID FROM UI" + uc + " WHERE ID = '" + uc + '\'';
		ResultSet rs = stmt .executeQuery(sql);
		
		//データ取得
		String usercode = "";
		while (rs.next()) {
			usercode = rs.getString("USERCODE");
		}
		con.close();
		stmt.close();
	}
	
	//ユーザコードに結びついているユーザネームを取り出す
	public String selectUserName(String uc) throws Exception{
		String url = "jdbc:postgresql:tokushima.data.ise.shibaura-it.ac.jp:5432/qredb";
		con = DriverManager.getConnection(url,"al21020","bond");
		Statement stmt = con.createStatement();
		
		String sql = "SELECT USERNAME FROM USERINFO WHERE ID='" + uc + '\'';
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
