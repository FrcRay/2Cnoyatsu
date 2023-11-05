<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id = "IB" class="db.InsertBean" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/Easy_Q're/css/home.css">
<title>HOME</title>
</head>
<!-- action内のurlは/から始めない -->
<body bgcolor="#FFFFFF">
<%
	String uc = (String) IB.getUsercode();
	String un = (String) IB.getUsername();
%>
<h1>ログイン中のユーザコード：<%= uc %><br>ログイン中のユーザネーム：<%= un %></h1>
<div class = "buttons">
	<FORM method="GET" action="../answer/">
		<input type="submit" value="アンケート回答" size="50" class = "inputs"/>
	</FORM>
	<FORM method="GET" action="../create/">
		<input type="submit" value="アンケート作成" size="50" class = "inputs"/>
	</FORM>
	<FORM method="GET" action="../result/">
		<input type="submit" value="アンケート結果" size="50" class = "inputs"/>
	</FORM>
</div>
</body>

</html>
