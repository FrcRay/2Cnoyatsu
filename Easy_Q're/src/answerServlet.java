import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.SignBean;
import db.kkcBean;

public class answerServlet extends HttpServlet {


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
    	try {
    		//JDBCドライバのロード
    		Class.forName("org.postgresql.Driver");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	kkcBean kB = new kkcBean();
    	int questionCode = 0;
    	HttpSession session = request.getSession();
    	int userCode = (int) session.getAttribute("usercode");
    	try {
    	questionCode = (int) kB.getQuestionCode(userCode); 
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	if(questionCode == -1) {
    		SignBean SB = new SignBean();
    		int statuscode = 2;
    		SB.setStatuscode(statuscode);
    		request.setAttribute("SB", SB);
    		String url="/response2.jsp";
    		RequestDispatcher dispatcher
    			=getServletContext().getRequestDispatcher(url);
    		dispatcher.forward(request, response);
    	}
		session.setAttribute("questionCode", questionCode);
		//JSPのURL
		String url="/answer.jsp";
		RequestDispatcher dispatcher
			=getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
    }
}