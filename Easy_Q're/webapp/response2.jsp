<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id = "SB" class="db.SignBean" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/Easy_Q're/css/create.css">
<title>レスポンスページ</title>
</head>
<body>
<%
	int sc = (int) SB.getStatuscode();
	String mess = "";
	if(sc == 0){
		mess = "アンケート作成に成功しました。";
	}else if(sc == 1){
		mess = "アンケート作成に失敗しました。";
	}else if(sc == 2){
		mess = "回答しています。全てのアンケートに";
	}
%>
<h1><%= mess %></h1>
	<br>
	<br>
	<FORM method="GET" action="http://localhost:8080/Easy_Q're/home.jsp" class = "bottombt">
			<input type="submit" value="ホームに戻る">
	</FORM>
</body>
</html>
