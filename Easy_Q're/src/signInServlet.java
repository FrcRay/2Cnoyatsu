import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.SignBean;

public class signInServlet extends HttpServlet{
		public void doGet(HttpServletRequest request,
            			HttpServletResponse response)
            throws IOException, ServletException
	{
	int uc = Integer.parseInt(request.getParameter("UC"));
	try {
		//JDBCドライバのロード
		Class.forName("org.postgresql.Driver");
	}catch(Exception e) {
		e.printStackTrace();
	}
	SignBean SB = new SignBean();
	try {
		//setterでhome.jspに渡すためのIDをBeanに渡す
		SB.setUsercode(uc);
		//データベースのユーザネームを探す
		String un = SB.selectUserName();
		//無い場合
		if(un.equals(""))throw new Exception();
		
		// セッションを作成
		HttpSession session = request.getSession();
		// セッションを無効化
		session.invalidate();
		// 新たなセッションを作成
		session = request.getSession();
		// セッションにユーザコードを保存
		session.setAttribute("usercode", uc);
		session.setAttribute("username", un);
		// セッションの有効期間を設定（60分）
		session.setMaxInactiveInterval(60 * 60);

		//JSPに情報を渡す
		//request.setAttribute("SB", SB);
		String url="/home.jsp";
		RequestDispatcher dispatcher
			=getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}catch(Exception e) {
		//ユーザコードが見つからないエラー
		int statuscode = 0;
		SB.setStatuscode(statuscode);
		request.setAttribute("SB", SB);
		String url="/response.jsp";
		RequestDispatcher dispatcher
			=getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
	
	}
}
