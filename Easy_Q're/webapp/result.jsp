<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/PreEasy_Q're/css/home.css">
<title>アンケート結果_検索</title>
</head>
<body bgcolor="#FFFFFF">
<H1>アンケート結果</H1>
	<form action="<%= request.getContextPath() %>/result.jsp" method="post" name="q_serchbox">
		<input type= "search" placeholder="アンケート名" >
		<input type="submit" value="検索">
	</form> 
	
	<h3> 最近投稿されたアンケート</h3>
	
	<ul>
    	<c:forEach items="${question}" var="q">
        	<li>${q}</li>
    	</c:forEach>
	</ul>
	
	<input type="submit" value="<-">
	<input type="submit" value="->">
	
	<FORM method="GET" action="http://localhost:8080/Easy_Q're/home.jsp" class = "bottombt">
		<input type="submit" value="ホームに戻る">
	</FORM>
</body>

</html>'