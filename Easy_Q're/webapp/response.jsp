<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id = "SB" class="db.SelectBean" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/2C_Survey/css/create.css">
<title>レスポンスページ</title>
</head>
<body>
<%
	int sc = (int) SB.getStatuscode();
	String mess = "";
	if(sc == 0){
		mess = "アンケート作成に成功しました。";
	}else if(sc == 1){
		mess = "ユーザコードがデータベース上に見つかりませんでした。";
	}else if(sc == 2){
		mess = "ユーザ作成に失敗しました。";
	}
%>
<h1><%= mess %></h1>
	<br>
	<br>
	<FORM method="GET" action="http://localhost:8080/2C_Survey/sign.html" class = "bottombt">
			<input type="submit" value="サインイン・サインアップページに戻る">
	</FORM>
</body>
</html>
