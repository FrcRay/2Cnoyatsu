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
	private List<Integer> questionCodeList = new ArrayList<>();
	private List<String> questionList = new ArrayList<>();
	private List<String> question2 = new ArrayList<>();
	//QUCODE
	private int qc;
	private List<Integer> outcome = new ArrayList<>();
	private List<Integer> numanswer = new ArrayList<>();
	//QI
	private List<String> question = new ArrayList<>();
	
	private static String url = "jdbc:postgresql://tokushima.data.ise.shibaura-it.ac.jp:5432/qredb";
	private static String user = "al21020";
	private static String pass = "bond";
	
	public void setQ_serchbox(String w) {
		this.q_serchbox=w;
	}
	
	public List<Integer> get_questionCodeList(){
		return this.questionCodeList;
	}
	
	public List<String> get_questionList(){
		return this.questionList;
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
	
	public List<String> get_question(){
		return this.question;
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
		this.questionCodeList = qCL;
		this.questionList = qL;
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
		List<Integer> oc = new ArrayList<>();
		con = DriverManager.getConnection(url,user,pass);
		Statement stmt = con.createStatement();
		String sql = "SELECT count(usercode) as ucc FROM QUCODE where questioncode='"+ this.qc +"' and numanswer='1'";
		ResultSet rs = stmt .executeQuery(sql);
		//データ取得
		while (rs.next()) {
			oc.add(rs.getInt("ucc"));
		}
		
		sql = "SELECT count(usercode) as ucc FROM QUCODE where questioncode='"+ this.qc +"' and numanswer='2'";
		rs = stmt .executeQuery(sql);
		while (rs.next()) {
			oc.add(rs.getInt("ucc"));
		}
		
		sql = "SELECT count(usercode) as ucc FROM QUCODE where questioncode='"+ this.qc +"' and numanswer='3'";
		rs = stmt .executeQuery(sql);
		while (rs.next()) {
			oc.add(rs.getInt("ucc"));
		}
		
		this.outcome = oc;
		con.close();
		stmt.close();
	}
	//questionCodeからすべてを取り出す
	public void QI() throws Exception{
		con = DriverManager.getConnection(url,user,pass);
		Statement stmt = con.createStatement();
		String sql = "SELECT question, option1, option2, option3 FROM QI WHERE questioncode='"+ this.qc +"'";
		ResultSet rs = stmt .executeQuery(sql);
		//List用意
		List<String> q = new ArrayList<>();
		//データ取得
		while (rs.next()) {
			q.add(rs.getString("question"));
			q.add(rs.getString("option1"));
			q.add(rs.getString("option2"));
			q.add(rs.getString("option3"));
		};
		this.question = q;
		con.close();
		stmt.close();
	}

}
