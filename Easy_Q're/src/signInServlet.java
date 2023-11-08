import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.SignBean;

public class signInServlet extends HttpServlet{
		public void doGet(HttpServletRequest request,
            			HttpServletResponse response)
            throws IOException, ServletException
	{
	String uc = request.getParameter("UC");
	try {
		//JDBCドライバのロード
		Class.forName("org.postgresql.Driver");
	}catch(Exception e) {
		e.printStackTrace();
	}
	SignBean SB = new SignBean();
	try {
		//setterでhome.jspに渡すためのIDを取得
		SB.setUsercode(uc);
		//データベースにユーザコードがある場合
		SB.selectUserCode();
		
		//JSPに情報を渡す
		request.setAttribute("SB", SB);
		String url="/home.jsp";
		RequestDispatcher dispatcher
			=getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}catch(Exception e) {
		//ユーザコードが見つからないエラー
		int statuscode = 1;
		SB.setStatuscode(statuscode);
		request.setAttribute("SB", SB);
		String url="/response.jsp";
		RequestDispatcher dispatcher
			=getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
	
	}
}
