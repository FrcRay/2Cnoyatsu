import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.kkcBean;

public class answerServlet2 extends HttpServlet {


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
    	HttpSession session = request.getSession();
    	int questionCode = (int) session.getAttribute("questionCode");
    	int userCode = (int) session.getAttribute("usercode");
    	Enumeration<String> selectedOption = request.getParameterNames();
    	kkcBean kB = new kkcBean();
    	try {
    		kB.selectOption(userCode, Integer.parseInt(selectedOption.nextElement().substring(0,1)), questionCode);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
		//JSPのURL
		String url="/A_response.jsp";
		RequestDispatcher dispatcher
			=getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
    }
}
