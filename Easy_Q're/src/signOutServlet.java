import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.SignBean;

public class signOutServlet extends HttpServlet {
	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ServletException {
		SignBean SB = new SignBean();
		// セッションを作成
		HttpSession session = request.getSession();
		// セッションを無効化
		session.invalidate();
		int statuscode = 2;
		SB.setStatuscode(statuscode);
		request.setAttribute("SB", SB);
		String url = "/response.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}
}
