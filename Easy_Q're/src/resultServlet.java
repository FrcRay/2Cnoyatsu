import java.io.IOException;

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
    	
    	try {
    		Class.forName("org.postgresql.Driver");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	//mrtBeanのコンストラクタ
    	mrtBean MB = new mrtBean();
    	
    	try {
    		MB.setQ_serchbox(q_serchbox);
    	   	//char q_code = MB.selectQ();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	
    	
		//JSPのURL
		String url="/result.jsp";
		RequestDispatcher dispatcher
			=getServletContext().getRequestDispatcher(url);
		//このservletが受け取ったHTTPGETリクエストをJSPファイルに転送している
		dispatcher.forward(request, response);
		
    }
}
