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
		List<Integer> numanswerList = (List<Integer>) request.getAttribute("numanswer");
		List<String> question = (List<String>) request.getAttribute("question");
		String test = (String)request.getAttribute("test");
		if(outcomeList!=null){
			for(int i=0; i< numanswerList.size(); i++){
				int numanswer = numanswerList.get(i);
				int outcome = outcomeList.get(i);
	%>
				<%= numanswer %>　<%= outcome %><br>
	<%
			}
		}else{
	%>
			List is null!
	<%
		}
		
		if(question!=null){
			for(int i=0; i<question.size(); i++){
				String Q = question.get(i);
	%>
				<%= Q %><br>
	<%
			}
		}
	%>
	<br>
	<%= test %>
	
	<FORM method="GET" action="../result/" class = "bottombt">
		<input type="submit" value="戻る">
	</FORM>
	
</body>

</html>