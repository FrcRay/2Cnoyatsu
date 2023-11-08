<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id = "kB" class="db.kkcBean" scope="request"/>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
		<title>アンケート回答</title>
	</head>
	<body>
		<%
			int questionCode = (int) kB.getQuestionCode(); 
			String question = (String) kB.getQuestion(questionCode);
			String option1 = (String) kB.getChoices(String(1), questionCode);
			String option2 = (String) kB.getChoices(String(2), questionCode);
			String option3 = (String) kB.getChoices(String(3), questionCode);
		%>
		<h1>アンケート回答ページ</h1>
		
		<h3><%=Qe %></h3>
		
		<FORM method="GET" action="../../servlet/answer2/">
			<p><input type="submit" value= "<%=option1 %>" name = "option" ></p>
			<p><input type="submit" value= "<%=option2 %>" name = "option"></p>
			<%if(C3 != "NULL"){ %>
			<p><input type="submit" value= "<%=option3 %>" name = "option"></p>
			<%} %>

		</FORM>
	
		<FORM method="GET" action="http://localhost:8080/Easy_Q're/home.jsp">
			<input type="submit" value="ホームに戻る">
		</FORM>

	</body>
</html>