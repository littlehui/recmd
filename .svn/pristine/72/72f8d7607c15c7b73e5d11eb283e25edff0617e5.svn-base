<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cn">
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/jquery.js"></script>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-8">

				<a href="/jfinal/view/userAdd.jsp" class="btn btn-primary btn-sm"> 新增用户
				</a>



				<table
					class="table table-striped table-bordered table-hover table-condensed">
					<c:forEach var="user" items="${userList}">
						<tr class="active">
							<td><c:out value="${user.id}" default="" />
							</td>
							<td>${user.userName}</td>
							<td>${user.password}</td>
							<td>&nbsp;&nbsp;<a href="/jfinal/user/delete/${user.id}">删除</a>
								&nbsp;&nbsp;<a href="/jfinal/user/edit/${user.id}">修改</a></td>
						</tr>
					</c:forEach>
				</table>
				
			</div>

			<div class="col-md-4"></div>
		</div>
	</div>
	</div>

</body>
</html>