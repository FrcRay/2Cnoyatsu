package db;

import java.io.Serializable;
import java.sql.Connection;

public class kkcBean implements Serializable{
	
	static Connection con;
	private String Choice = "";
	
	public String getChoices(int n) {
		return Choice;
	}
}