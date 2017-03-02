<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>
</title>
<link  rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/> 
</head>
<body>
<c:if test="${not empty loginUser}">
   欢迎${loginUser.nickname }访问我们的系统&nbsp;
   <a href="<%=request.getContextPath()%>/user.do?method=updateSelfInput">修改个人信息</a>
    <a href="<%=request.getContextPath()%>/user.do?method=show&id=${loginUser.id}">个人信息查询</a>
 <a href="<%=request.getContextPath()%>/user.do?method=logout">退出系统</a>
</c:if>
<c:if test="${empty loginUser }">
 <a href="<%=request.getContextPath()%>/user.do?method=loginInput">用户登录</a>&nbsp;
 <a href="<%=request.getContextPath()%>/user.do?method=addInput">用户注册</a>
</c:if>
<a href="<%=request.getContextPath()%>/category.do?method=list">商品类别管理</a>&nbsp;
<a href="<%=request.getContextPath()%>/goods.do?method=list">商品管理</a>&nbsp;
<a href="<%=request.getContextPath()%>/user.do?method=list">用户管理</a>
<hr/>

<h2 align="center"><decorator:title default="商城管理系统"></decorator:title></h2>
<decorator:body></decorator:body>
<hr/>
<div align="center"  style="width:100%;border-top:1px solid; float:left;margin-top:10px;">
   	CopyRight@2015-2018<br/>
   	开发培训项目
</div>

</body>
</html>