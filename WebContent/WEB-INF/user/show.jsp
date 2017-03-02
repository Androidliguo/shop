<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${user.nickname}信息查询</title>
</head>
<body>
<table width="800" class="thin-border" align="center">
<tr>
<td>用户名</td><td>${user.username}</td>
</tr>
<tr>
<td>用户密码</td><td>${user.password }</td>
</tr>
<tr>
<td>用户昵称</td><td>${user.nickname }</td>
</tr>
<tr>
<td>用户类型</td>
<td>
<c:if test="${user.type eq 0 }">普通用户</c:if>
 <c:if test="${user.type eq 1 }">管理员</c:if>
</td>
</tr>

<tr>
<td colspan="2">
<a  href="">用户订单查询</a>
</td>
</tr>

<tr>
<td colspan="2">
<c:if test="${user.id eq loginUser.id}"><a  href="<%=request.getContextPath()%>/address.do?method=addInput&userId=${user.id}">添加用户地址</a></c:if>
</td>
</tr>

<tr>
<td colspan="2">
<c:if test="${empty user.addresses}">
还没有添加地址
</c:if>
<c:if test="${ not empty user.addresses}">
 <c:forEach items="${user.addresses}"  var="address">
 <tr>
 <td>
 地址:${address.name}&nbsp;&nbsp;&nbsp;
 电话:${address.phone}&nbsp;&nbsp;
 邮编: ${address.postcode }&nbsp;&nbsp;
 <a href="<%=request.getContextPath()%>/address.do?method=delete&id=${address.id}&userId=${user.id}">删除</a>&nbsp;
 <a href="<%=request.getContextPath()%>/address.do?method=updateInput&id=${address.id}&userId=${user.id}">修改</a>
 </td>
 </tr>
  </c:forEach>
</c:if>
</td>
</tr>


</table> 
</body>
</html>