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
    	List<Integer> questionCodeList = new ArrayList<>();
    	List<String> questionList = new ArrayList<>();
    	
    	try {
    		Class.forName("org.postgresql.Driver");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	//mrtBeanのコンストラクタ
    	mrtBean MB = new mrtBean();
    	
    	//q_serchboxに何も入っていないなら
    	if(q_serchbox==null) {
    		try {
				MB.selectQ2();
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
    		MB.get_question2();
    	}else {
    		MB.setQ_serchbox(q_serchbox);
    		//アンケコードとアンケ文の取得
    		try {
    			MB.selectQ();
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
			questionCodeList = MB.get_questionCodeList();
			questionList = MB.get_questionList();
    	}
    	request.setAttribute("questionCodeList", questionCodeList);
    	request.setAttribute("questionList", questionList);
    	
		//JSPのURL
		String url="/result.jsp";
		RequestDispatcher dispatcher
			=getServletContext().getRequestDispatcher(url);
		//このservletが受け取ったHTTPGETリクエストをJSPファイルに転送している
		dispatcher.forward(request, response);
		
    }
}
