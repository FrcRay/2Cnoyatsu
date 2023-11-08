package db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class mrtBean implements Serializable{
	
	static Connection con;
	private String q_serchbox = "";
	private char usercord;
	
	public void setUSERCORD(char u) {
		this.usercord=u;
	}
	
	public void setQ_serchbox(String w) {
		this.q_serchbox=w;
	}
	
	//q_serchboxから一致する名前のアンケートのコードと質問文を取得する
	public /*char*/ void selectQ() throws Exception{
		String url = "jdbc:postgresql:tokushima.data.ise.shibaura-it.ac.jp:5432/qredb";
		con = DriverManager.getConnection(url,"al21020","bond");
		Statement stmt = con.createStatement();
		
		
		//DBのテーブル名、DB名は何だっけ?
		String sql = "SELECT (アンケートコード&質問文) FROM Ql"+ this.usercord +" WHERE (アンケート内容) like '" + this.q_serchbox + "%'";
		ResultSet rs = stmt .executeQuery(sql);
		//データ取得
		char Qcode;
		//どんな形でデータはかえされるんだっけ？
		while (rs.next()) {
		    //Qcode = rs.getString("USERNAME");
		}
		con.close();
		stmt.close();
		//return Qcode;
	}
	//アンケートのコードからそれに付随するデータを取得する
	public /*char*/ void Q() throws Exception{
		String url = "jdbc:postgresql:tokushima.data.ise.shibaura-it.ac.jp:5432/qredb";
		con = DriverManager.getConnection(url,"al21020","bond");
		Statement stmt = con.createStatement();
		
		
		//DBのテーブル名、DB名は何だっけ?
		String sql = "SELECT * FROM Ql"+ this.usercord +" WHERE (アンケートコード)='" + this.q_serchbox +"'";
		ResultSet rs = stmt .executeQuery(sql);
		//データ取得
		char Qcode;
		//どんな形でデータはかえされるんだっけ？
		while (rs.next()) {
		    //Qcode = rs.getString("USERNAME");
		}
		con.close();
		stmt.close();
		//return Qcode;
	}
}
