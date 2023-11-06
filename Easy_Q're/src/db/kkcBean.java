package db;

import java.io.Serializable;
import java.sql.Connection;

public class kkcBean implements Serializable{
	
	static Connection con;
	private String choice = "test";
	private String question = "test";
	
	public String getQuestion() {
		return question;
	}
	
	public String getChoices(int n) {
		return choice;
	}
	
	public void insertChoice(String c) {
		
	}
}