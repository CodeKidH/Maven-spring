<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/view/jsp_header.jsp"%>
<html>
<head>
<title>상품 목록 화면</title>
</head>
<body>
<div align="center" class="body">
<h2>상품 목록 화면</h2>
<table border="1">
	<tr class="header">
		<th align="center" width="80">상품ID</th>
		<th align="center" width="320">상품명</th>
		<th align="center" width="100">가격</th>
	</tr>
	<c:forEach items="${itemList}" var="item">
		<tr class="record">
			<td align="center">${item.carNo}</td>
			<td align="left"><a href="detail.html?carNo=${item.carNo}">${item.carName}</a></td>
		</tr>
	</c:forEach>
</table>
</div>
</body>
</html>