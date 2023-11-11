import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.mrtBean;

public class resultServlet2 extends HttpServlet {


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
    	int questionCode = Integer.parseInt(request.getParameter("questionCode"));
    	String test="OK!";
    	List<Integer> outcome = new ArrayList<>();
    	List<Integer> numanswer = new ArrayList<>();
    	
    	mrtBean MB = new mrtBean();
    	MB.set_questionCode(questionCode);
    	try {
			MB.QUCODE();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			test = "error";
			e.printStackTrace();
		}
    	outcome=MB.get_outcome();
    	numanswer=MB.get_numanswer();
    	
    	request.setAttribute("outcome", outcome);
    	request.setAttribute("numanswer", numanswer);
    	request.setAttribute("questionCode",questionCode);
    	request.setAttribute("test", test);
    	
		//JSPのURL
		String url="/result2.jsp";
		RequestDispatcher dispatcher
			=getServletContext().getRequestDispatcher(url);
		//このservletが受け取ったHTTPGETリクエストをJSPファイルに転送している
		dispatcher.forward(request, response);
    }
}		
