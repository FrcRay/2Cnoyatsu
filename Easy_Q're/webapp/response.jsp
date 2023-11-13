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
		mess = "ユーザコードがデータベース上に見つかりませんでした。";
	}else if(sc == 1){
		mess = "ユーザ作成に失敗しました。";
	}else if(sc == 2){
		mess = "サインアウトしました。";
	}
%>
<h1><%= mess %></h1>
	<br>
	<br>
	<FORM method="GET" action="http://localhost:8080/Easy_Q're/sign.html" >
			<input type="submit" value="サインイン・サインアップページに戻る" style="background-color:#f0908d; width: 255px;">
	</FORM>
</body>
</html>
