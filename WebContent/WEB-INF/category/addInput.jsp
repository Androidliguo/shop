
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加商品分类</title>
</head>
<body>
<jsp:include page="inc.jsp"></jsp:include>
<form action="<%=request.getContextPath() %>/category.do?method=add" method="post">
<table width="800" class="thin-border" align="center">
<tr>
<td>商品类别</td><td><input type="text" name="name"  size="50"/><span class="errorContainer">${errors.name}</span></td>
</tr>
<tr>
<td colspan="2">
<input type="submit"  value="添加"/>&nbsp;<input type="reset" value="重置"/>
</td>
</tr>
</table>
</form> 


</body>
</html>