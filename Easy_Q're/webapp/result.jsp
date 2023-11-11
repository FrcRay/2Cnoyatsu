<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/PreEasy_Q're/css/home.css">
<title>アンケート結果_検索</title>
</head>
<body bgcolor="#FFFFFF">
<H1>アンケート結果</H1>
	<form action="../result/" method="GET">
		<input type= "search" name="q_serchbox" placeholder="アンケート名" >
		<input type="submit" value="検索">
	</form> 
	
	<%@ page import="java.util.List" %>
	<% 
 		List<String> questionList = (List<String>) request.getAttribute("questionList");
		List<Integer> questionCodeList = (List<Integer>) request.getAttribute("questionCodeList");
		for(int i=0; i< questionList.size(); i++){
			String question = questionList.get(i);
			int questionCode = questionCodeList.get(i);
	%>
			<form action="../result2/" method="GET">
				<%= question %>
				<input type="hidden" name="questionCode" value="<%= questionCode %>">
				<input type="submit" value="選択">
			</form>
	<%
		}
	%>
	
	<!--
	<input type="submit" value="<-">
	<input type="submit" value="->">
	 <%
		int uc = (int) session.getAttribute("usercode");
		String un = (String) session.getAttribute("username");
	%>
	
	<FORM method="GET" action="../signIn/" class = "bottombt">
		<input type="hidden" value="<%= uc %>">
		<input type="hidden" value="<%= un %>">
		<input type="submit" value="ホームに戻る">
	</FORM> 
	-->
	
	<FORM method="GET" action="http://localhost:8080/Easy_Q're/home.jsp" class = "bottombt">
		<input type="submit" value="ホームに戻る">
	</FORM>
</body>

</html>