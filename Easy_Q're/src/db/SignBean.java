package db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SignBean implements Serializable {
	static Connection con;
	private int usercode;
	private String username = "";
	private String gender = "";
	private String birthday = "";
	private int age;
	private int statuscode;

	private int questioncode;
	private String question = "";
	private String option1 = "";
	private String option2 = "";
	private String option3 = "";
	private int outcomeoption1;
	private int outcomeoption2;
	private int outcomeoption3;
	
	private boolean almade;
	private boolean alanswer;
	private int numanswer;

	public int getUsercode() {
		return usercode;
	}

	public void setUsercode(int usercode) {
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

	public int getQuestioncode() {
		return this.questioncode;
	}

	public void setQuestioncode(int questioncode) {
		this.questioncode = questioncode;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOption1() {
		return this.option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return this.option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return this.option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public int getOutcomeoption1() {
		return this.outcomeoption1;
	}

	public void setOutcomeoption1(int outcomeoption1) {
		this.outcomeoption1 = outcomeoption1;
	}

	public int getOutcomeoption2() {
		return this.outcomeoption2;
	}

	public void setOutcomeoption2(int outcomeoption2) {
		this.outcomeoption2 = outcomeoption2;
	}

	public int getOutcomeoption3() {
		return this.outcomeoption3;
	}

	public void setOutcomeoption3(int outcomeoption3) {
		this.outcomeoption3 = outcomeoption3;
	}
	
	public boolean getAlmade() {
		return this.almade;
	}
	
	public void setAlmade(boolean almade) {
		this.almade = almade;
	}
	
	public boolean getAlanswer() {
		return this.alanswer;
	}
	
	public void setAlanswer(boolean alanswer) {
		this.alanswer = alanswer;
	}
	
	public int getNumanswer() {
		return this.numanswer;
	}
	
	public void setNumanswer(int numanswer) {
		this.numanswer = numanswer;
	}

	//ユーザ情報をデータベースに登録
	public void insertUserInfo() throws Exception {
		String url = "jdbc:postgresql://tokushima.data.ise.shibaura-it.ac.jp:5432/qredb";
		con = DriverManager.getConnection(url, "al21020", "bond");

		String sql = "INSERT INTO ui VALUES"
				+ "('" + this.usercode + "','" + this.username + "','" + this.gender + "','" + this.birthday + "','"
				+ this.age + "')";

		Statement stmt = con.createStatement();
		stmt.executeUpdate(sql);

		stmt.close();
		con.close();
	}

	//ユーザコードに結びついているユーザネームを取り出す
	public String selectUserName() throws Exception {
		String url = "jdbc:postgresql://tokushima.data.ise.shibaura-it.ac.jp:5432/qredb";
		con = DriverManager.getConnection(url, "al21020", "bond");
		Statement stmt = con.createStatement();

		String sql = "SELECT username FROM ui WHERE usercode = '" + this.usercode + "'";
		ResultSet rs = stmt.executeQuery(sql);
		//データ取得
		String username = "";
		while (rs.next()) {
			username = rs.getString("USERNAME");
		}
		con.close();
		stmt.close();
		return username;
	}

	//アンケートコードを取り出す
	public int selectQreCode() throws Exception {
		String url = "jdbc:postgresql://tokushima.data.ise.shibaura-it.ac.jp:5432/qredb";
		con = DriverManager.getConnection(url, "al21020", "bond");
		Statement stmt = con.createStatement();

		String sql = "SELECT MAX(questionCode) FROM QI";
		ResultSet rs = stmt.executeQuery(sql);
		//データ取得
		int qc = 0;
		while (rs.next()) {
			qc = rs.getInt("MAX");
		}
		con.close();
		stmt.close();
		return qc;
	}

	//アンケートをデータベースに登録
	public void insertQreInfo() throws Exception {
		String url = "jdbc:postgresql://tokushima.data.ise.shibaura-it.ac.jp:5432/qredb";
		con = DriverManager.getConnection(url, "al21020", "bond");

		String sql = "INSERT INTO qi VALUES"
				+ "('" + this.questioncode + "','" + this.question + "','" + this.option1 + "','" + this.option2 + "','"
				+ this.option3 + "')";

		Statement stmt = con.createStatement();
		stmt.executeUpdate(sql);

		stmt.close();
		con.close();
	}

	//アンケート作成情報を登録
	public void insertQUcode() throws Exception {
		String url = "jdbc:postgresql://tokushima.data.ise.shibaura-it.ac.jp:5432/qredb";
		con = DriverManager.getConnection(url, "al21020", "bond");

		String sql = "INSERT INTO qucode VALUES"
				+ "('" + this.questioncode + "','" + this.usercode + "','" + this.almade + "','" + this.numanswer + "')";

		Statement stmt = con.createStatement();
		stmt.executeUpdate(sql);

		stmt.close();
		con.close();
	}
}
