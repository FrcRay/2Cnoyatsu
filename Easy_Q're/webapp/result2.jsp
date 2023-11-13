<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>アンケート結果_表示</title>
<!-- Chart.jsを読み込む -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.0/chart.min.js"></script>
</head>
<body bgcolor="#FFFFFF">
<H1>アンケート結果</H1>
	
	<h3> 選択したアンケート</h3>
	
	<%@ page import="java.util.List" %>
	<% 
 		List<Integer> outcomeList = (List<Integer>) request.getAttribute("outcome");
		List<String> question = (List<String>) request.getAttribute("question");
    	List<List<Integer>> Gender = (List<List<Integer>>) request.getAttribute("Gender");
    	List<List<Integer>> Age = (List<List<Integer>>) request.getAttribute("Age");
			String Q = question.get(0);
	%>
			<%= Q %><br>
	<canvas id="myPieChart" width="500" height="500"></canvas>
	
	<%
		if(question.get(3).length()==0){
	%>
		<script>
    	// Chart.jsを使用して円グラフを描画
    	var ctx = document.getElementById('myPieChart').getContext('2d');
    	var myPieChart = new Chart(ctx, {
        	type: 'pie',
        	data: {
            	labels: ['<%= question.get(1) %>',' <%= question.get(2) %>'],
            	datasets: [{
                	label: 'Example Pie Chart',
                	data: [<%= outcomeList.get(0) %>,<%= outcomeList.get(1) %>],
                	backgroundColor: [
                    	'rgba(255, 99, 132, 0.2)',
                    	'rgba(54, 162, 235, 0.2)',
                    	'rgba(255, 206, 86, 0.2)',
                	],
                	borderColor: [
                    	'rgba(255, 99, 132, 1)',
                    	'rgba(54, 162, 235, 1)',
                    	'rgba(255, 206, 86, 1)',
                	],
                	borderWidth: 1
            	}]
        	},
        	options: {
            	responsive:false,
            	maintainAspectRatio: false,
            	scales: {
                	y: {
                    	beginAtZero: true
                	}
            	}
        	}
    	});
	</script>
	<%
		}else{
	%>
	<script>
    	// Chart.jsを使用して円グラフを描画
    	var ctx = document.getElementById('myPieChart').getContext('2d');
    	var myPieChart = new Chart(ctx, {
        	type: 'pie',
        	data: {
            	labels: ['<%= question.get(1) %>', '<%= question.get(2) %>', '<%= question.get(3) %>'],
            	datasets: [{
                	label: 'Example Pie Chart',
                	data: [<%= outcomeList.get(0) %>,<%= outcomeList.get(1) %>,<%= outcomeList.get(2) %>],
                	backgroundColor: [
                    	'rgba(255, 99, 132, 0.2)',
                    	'rgba(54, 162, 235, 0.2)',
                    	'rgba(255, 206, 86, 0.2)',
                	],
                	borderColor: [
                    	'rgba(255, 99, 132, 1)',
                    	'rgba(54, 162, 235, 1)',
                    	'rgba(255, 206, 86, 1)',
                	],
                	borderWidth: 1
            	}]
        	},
        	options: {
            	responsive:false,
            	maintainAspectRatio: false,
            	scales: {
                	y: {
                    	beginAtZero: true
                	}
            	}
        	}
    	});
	</script>
	<%
		}
	%>

			<table border="3">
				<tr>
					<th>選択肢</th>
					<th>人数</th>
					<th>男</th>
					<th>女</th>
					<th>0～10未満</th>
					<th>10～20未満</th>
					<th>20～30未満</th>
					<th>30～40未満</th>
					<th>40～50未満</th>
					<th>50～60未満</th>
					<th>60～70未満</th>
					<th>70～80未満</th>
					<th>80～90未満</th>
					<th>90以上</th>
				</tr>
	<%
			for(int i=0; i< outcomeList.size(); i++){
				int outcome = outcomeList.get(i);
				Q = question.get(i+1);
				if(Q.length()==0){
				}else{
	%>
					<tr>
					<td><%= Q %></td>
					<td><%= outcome %></td>
	<%
					List<Integer> G = Gender.get(i);
					for(int j=0; j<G.size(); j++){
						int g = G.get(j);
	%>
						<td><%= g %></td>
	<%
					}
					List<Integer> A = Age.get(i);
					for(int j=0; j<A.size(); j++){
						int a = A.get(j);
	%>
						<td><%= a %></td>
	<%
					}

				}
	%>
				</tr>
	<%
			}
	%>
			</table>


	<FORM method="GET" action="../result/" class = "bottombt">
		<input type="submit" value="戻る">
	</FORM>
	
</body>

</html>