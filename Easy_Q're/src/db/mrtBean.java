package db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class mrtBean implements Serializable{
	
	static Connection con;
	private String q_serchbox = "";
	private char usercord;
	private List<Integer> questionCode = new ArrayList<>();
	private List<String> question = new ArrayList<>();
	
	private static String url = "jdbc:postgresql://tokushima.data.ise.shibaura-it.ac.jp:5432/qredb";
	private static String user = "al21016";
	private static String pass = "bond";
	
	/*
	private Integer[] questionCode;
	private String[] question;
	*/
	
	public void setUSERCORD(char u) {
		this.usercord=u;
	}
	
	public void setQ_serchbox(String w) {
		this.q_serchbox=w;
	}
	
	public List<Integer> get_questionCode(){
		return this.questionCode;
	}
	
	public List<String> get_question(){
		return this.question;
	}
	
	//q_serchboxから一致する名前のアンケートのコードと質問文を取得する
	public /*char*/ void selectQ() throws Exception{

		con = DriverManager.getConnection(url,user,pass);
		Statement stmt = con.createStatement();
		
		String sql = "SELECT questionCode, question FROM QI"+ this.usercord +" WHERE QI like '" + this.q_serchbox + "%'";
		ResultSet rs = stmt .executeQuery(sql);
		
		//List用意
		List<Integer> qCL = new ArrayList<>();
		List<String> qL = new ArrayList<>();
		
		//データ取得
		while (rs.next()) {
			qCL.add(rs.getInt("questionCode"));
			qL.add(rs.getString("question"));
		}
		
		this.questionCode = qCL;
		this.question = qL;
		
		/*
		//配列変換
		this.questionCode = qCL.toArray(new Integer[qCL.size()]);
		this.question = qL.toArray(new String[qL.size()]);
		*/
		
		con.close();
		stmt.close();
		//return Qcode;
	}
	//アンケートのコードからそれに付随するデータを取得する
	public /*char*/ void Q() throws Exception{
		con = DriverManager.getConnection(url,user,pass);
		Statement stmt = con.createStatement();
		
		
		//DBのテーブル名、DB名は何だっけ?
		String sql = "SELECT * FROM QI"+ this.usercord +" WHERE QI='" + this.q_serchbox +"'";
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
