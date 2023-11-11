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
	//selectQ
	private List<Integer> questionCode = new ArrayList<>();
	private List<String> question = new ArrayList<>();
	private List<String> question2 = new ArrayList<>();
	//Q
	private int qc;
	private List<Integer> outcome = new ArrayList<>();
	private List<Integer> numanswer = new ArrayList<>();
	
	private static String url = "jdbc:postgresql://tokushima.data.ise.shibaura-it.ac.jp:5432/qredb";
	private static String user = "al21020";
	private static String pass = "bond";
	
	public void setQ_serchbox(String w) {
		this.q_serchbox=w;
	}
	
	public List<Integer> get_questionCode(){
		return this.questionCode;
	}
	
	public List<String> get_question(){
		return this.question;
	}
	
	public List<String> get_question2(){
		return this.question2;
	}
	
	public void set_questionCode(int qc) {
		this.qc=qc;
	}
	
	public List<Integer> get_outcome(){
		return this.outcome;
	}
	
	public List<Integer> get_numanswer(){
		return this.numanswer;
	}
	//q_serchboxから一致する名前のアンケートのコードと質問文を取得する
	public void selectQ() throws Exception{
		con = DriverManager.getConnection(url,user,pass);
		Statement stmt = con.createStatement();
		String sql = "SELECT questioncode, question FROM QI WHERE question like '" + this.q_serchbox + "%'";
		ResultSet rs = stmt .executeQuery(sql);
		//List用意
		List<Integer> qCL = new ArrayList<>();
		List<String> qL = new ArrayList<>();
		//データ取得
		while (rs.next()) {
			qCL.add(rs.getInt("questioncode"));
			qL.add(rs.getString("question"));
		}
		this.questionCode = qCL;
		this.question = qL;
		con.close();
		stmt.close();
	}
	//まだ実装してない
	public void selectQ2() throws Exception{
		con = DriverManager.getConnection(url,user,pass);
		Statement stmt = con.createStatement();
		String sql = "SELECT question FROM QI";
		ResultSet rs = stmt .executeQuery(sql);
		//List用意
		List<String> qL = new ArrayList<>();
		//データ取得
		while (rs.next()) {
			//qCL.add(rs.getInt("questioncode"));
			qL.add(rs.getString("question"));
		}
		//this.questionCode = qCL;
		this.question2 = qL;
		con.close();
		stmt.close();
	}
	//そのアンケートを答えた人のuserCodeをカウントする。
	public void QUCODE() throws Exception{
		con = DriverManager.getConnection(url,user,pass);
		Statement stmt = con.createStatement();
		String sql = "SELECT count(usercode) as ucc, numanswer FROM QUCODE GROUP BY numanswer HAVING questioncode='"+ this.qc +"' ORDER BY numanswer";
		ResultSet rs = stmt .executeQuery(sql);
		//データ取得
		List<Integer> na = new ArrayList<>();
		List<Integer> oc = new ArrayList<>();
		while (rs.next()) {
			na.add(rs.getInt("numanswer"));
			oc.add(rs.getInt("ucc"));
		}
		this.numanswer = na;
		this.outcome = oc;
		con.close();
		stmt.close();
	}

}
