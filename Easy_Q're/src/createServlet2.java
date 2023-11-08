import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.SignBean;

public class createServlet2 extends HttpServlet {

	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ServletException {
		String question = request.getParameter("QUESTION");
		String option1 = request.getParameter("OPTION1");
		String option2 = request.getParameter("OPTION2");
		String option3 = request.getParameter("OPTION3");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		SignBean SB = new SignBean();
		int qc;
		
		//option3が入力されていたとき
		if(!option3.equals(null)) {
			SB.setOutcomeoption3(0);
			SB.setOption3(option3);
		//入力されていないときは0と空文字をset
		}else {
			SB.setOutcomeoption3(0);
			SB.setOption3("");
		}
		
		SB.setQuestion(question);
		SB.setOption1(option1);
		SB.setOption2(option2);
		SB.setOutcomeoption1(0);
		SB.setOutcomeoption2(0);
		//getServletContext().log(SB.getOption3()+","+SB.getQuestion());
		
		
		try {
			//アンケートコードを取り出す
			qc = SB.selectQreCode();
			//getServletContext().log(String.valueOf(qc));
			qc++;
		} catch (Exception e) {
			qc = 0;
		}
		//アンケートコードをbeanに渡す
		SB.setQuestioncode(qc);
		try {
			SB.insertQreInfo();
			
			request.setAttribute("SB", SB);
			String url = "/home.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			int code = 3;
			SB.setStatuscode(code);
			request.setAttribute("SB", SB);
			String url = "/response.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}

	}
}