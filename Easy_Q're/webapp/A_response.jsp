<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id = "kB" class="db.kkcBean" scope="request"/>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
		<title>アンサーレスポンスページ</title>
	</head>
	<body>
		<h1>回答を記録しました</h1>
		<FORM method="GET" action="http://localhost:8080/Easy_Q're/home.jsp">
			<input type="submit" value="ホームに戻る">
		</FORM>
	</body>
</html>
