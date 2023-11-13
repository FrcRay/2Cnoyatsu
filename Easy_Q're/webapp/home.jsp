<%@ page contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="SB" class="db.SignBean" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Shift-JIS">
<link rel="stylesheet" type="text/css"
	href="http://localhost:8080/Easy_Q're/css/home.css">
<title>HOME</title>
</head>
<!-- action内のurlは/から始めない -->
<body bgcolor="#FFFFFF">
	<%
	//セッションからユーザコードを取得
	int uc = (int) session.getAttribute("usercode");
	//ユーザネーム取り出し
	String un = (String) session.getAttribute("username");
	%>
	<h2>
		ログイン中のユーザコード：<%= uc%><br>ログイン中のユーザネーム：<%= un%></h2>
		<FORM method="GET" action="http://localhost:8080/Easy_Q're/servlet/signOut/">
            <input type="submit" value="サインアウト" style="width: 200px; height: 50px;"/>
        </FORM>
	<h1>Easy Q're</h1>
	<div class="buttons">
		<FORM method="GET" action="http://localhost:8080/Easy_Q're/servlet/answer/">
			<input type="submit" value="アンケート回答" size="50" class="inputs" />
		</FORM>
		<FORM method="GET" action="http://localhost:8080/Easy_Q're/create.html">
			<input type="submit" value="アンケート作成" size="50" class="inputs" />
		</FORM>
		<FORM method="GET" action="http://localhost:8080/Easy_Q're/servlet/result/">
			<input type="submit" value="アンケート結果" size="50" class="inputs" />
		</FORM>
	</div>
</body>

</html>
