import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.mrtBean;

public class resultServlet extends HttpServlet {


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
    	//検索ワードをresult.jspから取得
    	String q_serchbox = request.getParameter("q_serchbox");
    	List<Integer> questionCode = new ArrayList<>();
    	List<String> question = new ArrayList<>();
    	
    	try {
    		Class.forName("org.postgresql.Driver");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	//mrtBeanのコンストラクタ
    	mrtBean MB = new mrtBean();
    	
    	//アンケコードとアンケ文の取得
    	try {
    		MB.setQ_serchbox(q_serchbox);
    		MB.selectQ();
    		questionCode = MB.get_questionCode();
    		question = MB.get_question();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	request.setAttribute("questionCode", questionCode);
    	request.setAttribute("question", question);
    	
		//JSPのURL
		String url="/result.jsp";
		RequestDispatcher dispatcher
			=getServletContext().getRequestDispatcher(url);
		//このservletが受け取ったHTTPGETリクエストをJSPファイルに転送している
		dispatcher.forward(request, response);
		
    }
}
