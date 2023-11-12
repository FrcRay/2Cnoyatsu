<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>アンケート結果_表示</title>
</head>
<body bgcolor="#FFFFFF">
<H1>アンケート結果</H1>
	
	<h3> 選択したアンケート</h3>
	
	<%@ page import="java.util.List" %>
	<% 
 		List<Integer> outcomeList = (List<Integer>) request.getAttribute("outcome");
		List<String> question = (List<String>) request.getAttribute("question");
		if(outcomeList!=null){
			String Q = question.get(0);
	%>
			<%= Q %><br>
	<%
			for(int i=0; i< outcomeList.size(); i++){
				int outcome = outcomeList.get(i);
				Q = question.get(i+1);
				if(Q.length()==0){
				}else{
	%>
					<%= Q %>:<%= outcome %><br>
	<%
				}
			}
		}else{
	%>
			List is null!
	<%
		}
	%>

	<FORM method="GET" action="../result/" class = "bottombt">
		<input type="submit" value="戻る">
	</FORM>
	
</body>

</html>