<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>상</title>
<link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
	<div id="container">
		<h2>게시글 목록</h2>
		<table class="tbl_list">
			<form th:action="@{insertBoard}" method="post">
				<table class="tbl_reg">
					<tbody>
						<tr>
							<td>ì ëª©</td>
							<td><input type="text" name="title"></td>
						</tr>
						<tr>
							<td>ìì±ì</td>
							<td><input type="text" name="writer"></td>
						</tr>
						<tr>
							<td>ë´ì©</td>
							<td>
								<textarea rows="10" cols="50"
								   name="content"></textarea>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<button type="submit">ìê¸ ë±ë¡</button>
						</td>
						</tr>
					</tbody>
				</table>
			</form>	
		</table>
	</div>
</body>
</html>