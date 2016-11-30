<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cn">
<link href="/assets/css/bootstrap.min.css" rel="stylesheet">
<script src="/assets/js/bootstrap.min.js"></script>
<script src="/assets/js/jquery.js"></script>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-8">
			<c:if test="${user!=null}">
			<form class="form-horizontal" role="form" action="/jfinal/user/update" method="post">
			</c:if>
				<form class="form-horizontal" role="form" action="/jfinal/user/add" method="post">
					<div class="form-group">
						<label for="userName" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="userName" name="user.userName"
								placeholder="请填写用户名" value="${user.userName}">
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="col-sm-2 control-label">密&nbsp;&nbsp;码</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="password" name="user.password"
								placeholder="请填写密码" value="${user.password}">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">保存</button>
						</div>
					</div>
				</form>





				<div class="col-md-4"></div>
			</div>
		</div>
	</div>

</body>
</html>