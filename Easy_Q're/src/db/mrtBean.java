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
	private int usercode;
	//selectQ
	private List<Integer> questionCodeList = new ArrayList<>();
	private List<String> questionList = new ArrayList<>();
	private List<String> question2 = new ArrayList<>();
	//QUCODE
	private int qc;
	private List<Integer> outcome = new ArrayList<>();
	//QI
	private List<String> question = new ArrayList<>();
	//UI_QUCOD
	private List<List<Integer>> Gender = new ArrayList<>();
	private List<List<Integer>> Age = new ArrayList<>();
	
	private static String url = "jdbc:postgresql://tokushima.data.ise.shibaura-it.ac.jp:5432/qredb";
	private static String user = "al21020";
	private static String pass = "bond";
	
	public void set_usercode(int uc) {
		this.usercode=uc;
	}
	
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
	
	public List<List<Integer>> get_Gender(){
		return this.Gender;
	}
	
	public List<List<Integer>> get_Age(){
		return this.Age;
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
		String sql = "SELECT A.questioncode, A.question FROM QI A, QUCODE B where B.usercode='"+this.usercode+"' and A.questioncode=B.questioncode and B.almade='t";
		ResultSet rs = stmt .executeQuery(sql);
		//List用意
		List<Integer> qCL = new ArrayList<>();
		List<String> qL = new ArrayList<>();
		//データ取得
		while (rs.next()) {
			qCL.add(rs.getInt("A.questioncode"));
			qL.add(rs.getString("A.question"));
		}
		this.questionCodeList = qCL;
		this.questionList = qL;
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
	//UIとQUCODEを結合させてquestionCodeからgender,ageをそれぞれ条件を絞って取り出す。
	public void UI_QUCODE() throws Exception{
		List<List<Integer>> Gender = new ArrayList<>();
		List<List<Integer>> Age = new ArrayList<>();
		con = DriverManager.getConnection(url,user,pass);
		Statement stmt = con.createStatement();
		//gender
		for(int i=1; i<=3; i++) {
			List<Integer> a = new ArrayList<>();
			String sql = "SELECT count(A.usercode) as mnum FROM UI A, QUCODE B WHERE B.questioncode='"+ this.qc +"' and B.numanswer='"+ i +"' and A.gender='m' and A.usercode=B.usercode";
			ResultSet rs = stmt .executeQuery(sql);
			if (rs.next()) {
				a.add(rs.getInt("mnum"));
			} else {
			    // 結果がない場合の処理
				a.add(0);
			}
		
			sql = "SELECT count(A.usercode) as fnum FROM UI A, QUCODE B WHERE B.questioncode='"+ this.qc +"' and B.numanswer='"+ i +"' and A.gender='f' and A.usercode=B.usercode";
			rs = stmt .executeQuery(sql);
			if (rs.next()) {
				a.add(rs.getInt("fnum"));
			} else {
			    // 結果がない場合の処理
				a.add(0);
			}
			Gender.add(a);
		}
		this.Gender = Gender;
		
		//age
		for(int i=1; i<=3; i++) {
			List<Integer> a = new ArrayList<>();
			String sql = "SELECT count(A.usercode) as a FROM UI A, QUCODE B WHERE B.questioncode='"+ this.qc +"' and A.usercode=B.usercode and B.numanswer='"+ i +"' and A.age BETWEEN 0 AND 10 ";
			ResultSet rs = stmt .executeQuery(sql);
			if (rs.next()) {
				a.add(rs.getInt("a"));
			} else {
			    // 結果がない場合の処理
				a.add(0);
			}
		
			sql = "SELECT count(A.usercode) as a FROM UI A, QUCODE B WHERE B.questioncode='"+ this.qc +"' and A.usercode=B.usercode and B.numanswer='"+ i +"' and A.age BETWEEN 10 AND 20 ";
			rs = stmt .executeQuery(sql);
			if (rs.next()) {
				a.add(rs.getInt("a"));
			} else {
			    // 結果がない場合の処理
				a.add(0);
			}
			
			sql = "SELECT count(A.usercode) as a FROM UI A, QUCODE B WHERE B.questioncode='"+ this.qc +"' and A.usercode=B.usercode and B.numanswer='"+ i +"' and A.age BETWEEN 20 AND 30 ";
			rs = stmt .executeQuery(sql);
			if (rs.next()) {
				a.add(rs.getInt("a"));
			} else {
			    // 結果がない場合の処理
				a.add(0);
			}
			
			sql = "SELECT count(A.usercode) as a FROM UI A, QUCODE B WHERE B.questioncode='"+ this.qc +"' and A.usercode=B.usercode and B.numanswer='"+ i +"' and A.age BETWEEN 30 AND 40 ";
			rs = stmt .executeQuery(sql);
			if (rs.next()) {
				a.add(rs.getInt("a"));
			} else {
			    // 結果がない場合の処理
				a.add(0);
			}
			
			sql = "SELECT count(A.usercode) as a FROM UI A, QUCODE B WHERE B.questioncode='"+ this.qc +"' and A.usercode=B.usercode and B.numanswer='"+ i +"' and A.age BETWEEN 40 AND 50 ";
			rs = stmt .executeQuery(sql);
			if (rs.next()) {
				a.add(rs.getInt("a"));
			} else {
			    // 結果がない場合の処理
				a.add(0);
			}
			
			sql = "SELECT count(A.usercode) as a FROM UI A, QUCODE B WHERE B.questioncode='"+ this.qc +"' and A.usercode=B.usercode and B.numanswer='"+ i +"' and A.age BETWEEN 50 AND 60 ";
			rs = stmt .executeQuery(sql);
			if (rs.next()) {
				a.add(rs.getInt("a"));
			} else {
			    // 結果がない場合の処理
				a.add(0);
			}
			
			sql = "SELECT count(A.usercode) as a FROM UI A, QUCODE B WHERE B.questioncode='"+ this.qc +"' and A.usercode=B.usercode and B.numanswer='"+ i +"' and A.age BETWEEN 60 AND 70 ";
			rs = stmt .executeQuery(sql);
			if (rs.next()) {
				a.add(rs.getInt("a"));
			} else {
			    // 結果がない場合の処理
				a.add(0);
			}
			
			sql = "SELECT count(A.usercode) as a FROM UI A, QUCODE B WHERE B.questioncode='"+ this.qc +"' and A.usercode=B.usercode and B.numanswer='"+ i +"' and A.age BETWEEN 70 AND 80 ";
			rs = stmt .executeQuery(sql);
			if (rs.next()) {
				a.add(rs.getInt("a"));
			} else {
			    // 結果がない場合の処理
				a.add(0);
			}
			
			sql = "SELECT count(A.usercode) as a FROM UI A, QUCODE B WHERE B.questioncode='"+ this.qc +"' and A.usercode=B.usercode and B.numanswer='"+ i +"' and A.age BETWEEN 80 AND 90 ";
			rs = stmt .executeQuery(sql);
			if (rs.next()) {
				a.add(rs.getInt("a"));
			} else {
			    // 結果がない場合の処理
				a.add(0);
			}
			
			sql = "SELECT count(A.usercode) as a FROM UI A, QUCODE B WHERE B.questioncode='"+ this.qc +"' and A.usercode=B.usercode and B.numanswer='"+ i +"' and A.age >=90 ";
			rs = stmt .executeQuery(sql);
			if (rs.next()) {
				a.add(rs.getInt("a"));
			} else {
			    // 結果がない場合の処理
				a.add(0);
			}
			Age.add(a);
		}
		this.Age = Age;
		
		
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
