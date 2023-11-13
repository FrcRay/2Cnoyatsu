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
    	List<Integer> outcome = new ArrayList<>();
    	List<String> question = new ArrayList<>();
    	List<List<Integer>> Gender = new ArrayList<>();
    	List<List<Integer>> Age = new ArrayList<>();	
    	mrtBean MB = new mrtBean();
    	MB.set_questionCode(questionCode);
    	try {
			MB.QUCODE();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	outcome=MB.get_outcome();
    	
    	try {
			MB.QI();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
    	question=MB.get_question();
    	
    	try {
			MB.UI_QUCODE();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
    	Gender=MB.get_Gender();
    	Age=MB.get_Age();
    	
    	request.setAttribute("outcome", outcome);
    	request.setAttribute("question", question);
    	request.setAttribute("Gender", Gender);
    	request.setAttribute("Age", Age);
    	
		//JSPのURL
		String url="/result2.jsp";
		RequestDispatcher dispatcher
			=getServletContext().getRequestDispatcher(url);
		//このservletが受け取ったHTTPGETリクエストをJSPファイルに転送している
		dispatcher.forward(request, response);
    }
}		
