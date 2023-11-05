import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.SelectBean;

public class createServlet2 extends HttpServlet {


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
    	String question = request.getParameter("QUESTION");
    	String option1 = request.getParameter("OPTION1");
    	String option2 = request.getParameter("OPTION2");
    	String option3 = request.getParameter("OPTION3");
    	try {
    		Class.forName("org.postgresql.Driver");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	SelectBean SB = new SelectBean();
    	try {
    		SB.selectQreCode();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	
    	//データベースから最新のアンケートコードを取得
    	//そのコード+1したものを作成するアンケートのコードとする
    	//選択肢と質問内容をデータベースに保存
    	
		int code = 0;
		SB.setStatuscode(code);
		request.setAttribute("SB", SB);
		String url="/response.jsp";
		RequestDispatcher dispatcher
			=getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
    }
}