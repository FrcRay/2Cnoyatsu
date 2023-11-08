import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.SignBean;

public class signUpServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
	throws IOException, ServletException{
		//htmlで入力したデータを取得
		int uc = Integer.parseInt(request.getParameter("uc"));
		String un = request.getParameter("un");
		String gender = request.getParameter("gender");
		String birthday = request.getParameter("birthday");
		int age;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	    Date date = null;
		try {
			date = sdf.parse(birthday);
		} catch (ParseException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
	    Date now = new Date();
	    
	    //Calendar型インスタンス生成
        Calendar calBirth = Calendar.getInstance();
        Calendar calNow = Calendar.getInstance();

        //Date型をCalendar型に変換
        calBirth.setTime(date);
        calNow.setTime(now);

        //年齢の計算
        age = calNow.get(Calendar.YEAR) - calBirth.get(Calendar.YEAR);
        //誕生月を迎えていない場合は年齢-1
        if (calNow.get(Calendar.MONTH) < calBirth.get(Calendar.MONTH)) {
            age -= 1;
        //誕生月を迎えている場合
        }else if (calNow.get(Calendar.MONTH) == calBirth.get(Calendar.MONTH)) {
            //誕生日を迎えていない場合年齢−１
            if (calNow.get(Calendar.DATE) < calBirth.get(Calendar.DATE)) {
                age -= 1;
            }
        }
		
		try {
			//JDBCドライバのロード
			Class.forName("org.postgresql.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
		SignBean SB = new SignBean();
		
		//Beanにデータを渡す
		SB.setUsercode(uc);
		SB.setUsername(un);
		SB.setGender(gender);
		SB.setBirthday(birthday);
		SB.setAge(age);
		
		//dockerのlogに残す
		//getServletContext().log(SB.getUsername()+","+SB.getUsercode()+","+SB.getBirthday()+","+SB.getGender()+","+SB.getAge());
		try {
			SB.insertUserInfo();
			//JSPに情報を渡す
			request.setAttribute("SB", SB);
			String url="/home.jsp";
			RequestDispatcher dispatcher
				=getServletContext().getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}catch(Exception e) {
			//ユーザ作成失敗
			int statuscode = 2;
			SB.setStatuscode(statuscode);
			request.setAttribute("SB", SB);
			String url="/response.jsp";
			RequestDispatcher dispatcher
				=getServletContext().getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
	}
}
