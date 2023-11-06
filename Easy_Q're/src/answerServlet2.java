import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.kkcBean;

public class answerServlet2 extends HttpServlet {


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
    	String c = request.getParameter("1");
    	
    	kkcBean kB = new kkcBean();
    	try {
    		kB.insertChoice(c);
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