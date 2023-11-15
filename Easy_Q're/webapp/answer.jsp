<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id = "kB" class="db.kkcBean" scope="request"/>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
		<style>
  body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #FFFFFF;
    color: #333333;
  }

  h1 {
    text-align: center;
    color: #0066cc;
  }
  
  h3 {
  	text-align: center;
  }

  .buttons {
    text-align: center;
    }
    
   .button{
   text-align: right;
   } 
</style>
		<title>アンケート回答</title>
	</head>
	<body>
		<%
			int questionCode = (int) session.getAttribute("questionCode");
			String[] question = (String[]) kB.getQuestion(questionCode);
		%>
		<h1>アンケート回答ページ</h1>
		
		<h3><%=question[0] %></h3>
		
		<div class="buttons">
			<FORM method="GET" action="../answer2/">
				<input type="submit" value= "<%=question[1] %>" name = "1" >
				<input type="submit" value= "<%=question[2] %>" name = "2">
				<%if(question[3].equals("") == false){ %>
				<input type="submit" value= "<%=question[3] %>" name = "3">
				<%} %>
			</FORM>
	 	</div>
	 	<div class="button">
			<FORM method="GET" action="http://localhost:8080/Easy_Q're/home.jsp">
				<input type="submit" value="ホームに戻る">
			</FORM>
		</div>

	</body>
</html>