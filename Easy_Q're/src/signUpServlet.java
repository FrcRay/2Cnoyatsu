import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.SignBean;

public class signUpServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
	throws IOException, ServletException{
		//htmlで入力したデータを取得
		String uc = request.getParameter("uc");
		String un = request.getParameter("un");
		String gender = request.getParameter("gender");
		String birthday = request.getParameter("birthday");
		int age = Integer.parseInt(request.getParameter("age"));
		
		try {
			//JDBCドライバのロード
			Class.forName("org.postgresql.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
		SignBean SB = new SignBean();
		//テーブルを作成
		try {
			//Beanにデータを渡す
			SB.setUsercode(uc);
			SB.setUsername(un);
			SB.setGender(gender);
			SB.setBirthday(birthday);
			SB.setAge(age);
			
			//IB.createUITable();
			SB.insertUserInfo();
			
			//JSPに情報を渡す
			request.setAttribute("SB", SB);
			String url="/home.jsp";
			RequestDispatcher dispatcher
				=getServletContext().getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}catch(Exception e) {
			//ユーザ作成失敗
			int statuscode = 2;
			SB.setStatuscode(statuscode);
			request.setAttribute("SB", SB);
			String url="/response.jsp";
			RequestDispatcher dispatcher
				=getServletContext().getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
	}
}
