<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
</head>
<body>
<form action="user.do?method=login" method="post">
<input type="hidden" name="id" value="${user.id}"/>
<table width="600" class="thin-border" align="center">
<tr>
<td>用户名</td><td><input type="text" name="username"></td>
</tr>
<tr>
<td>用户密码</td><td><input type="text" name="password" ></td>
</tr>
<tr>
<td colspan="2">
<input type="submit"  value="登录"/>&nbsp;<input type="reset" value="重置信息"/>
</td>
</tr>
</table>
</form> 


</body>
</html>