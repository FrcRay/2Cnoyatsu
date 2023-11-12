<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id = "kB" class="db.kkcBean" scope="request"/>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
		<title>アンケート回答</title>
	</head>
	<body>
		<%
			int questionCode = (int) session.getAttribute("questionCode");
			String question = (String) kB.getQuestion(questionCode);
			String option1 = (String) kB.getOption("1", questionCode);
			String option2 = (String) kB.getOption("2", questionCode);
			String option3 = (String) kB.getOption("3", questionCode);
		%>
		<h1>アンケート回答ページ</h1>
		
		<h3><%=question %></h3>
		
		<FORM method="GET" action="../answer2/">
			<p><input type="submit" value= "<%=option1 %>" name = "1" ></p>
			<p><input type="submit" value= "<%=option2 %>" name = "2"></p>
			<%if(option3 != ""){ %>
			<p><input type="submit" value= "<%=option3 %>" name = "3"></p>
			<%} %>

		</FORM>
	
		<FORM method="GET" action="http://localhost:8080/Easy_Q're/home.jsp">
			<input type="submit" value="ホームに戻る">
		</FORM>

	</body>
</html>