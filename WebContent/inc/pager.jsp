<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="pg"  uri="http://jsptags.com/tags/navigation/pager"%>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
 <pg:pager items="${param.items}" maxPageItems="15"  maxIndexPages="10" export="curPage=pageNumber" url="${param.url}">
  <c:forEach items="${param.params }" var="p">
  <pg:param name="${p}"/>
</c:forEach>
 <pg:last>
 共有${param.items}条记录,共有${pageNumber}页，当前是第${curPage}页。
 </pg:last>
<pg:first>
<a href="${pageUrl }>">首页</a>
</pg:first>
<pg:prev>
<a href="${pageUrl }">上一页</a>
</pg:prev>

<pg:pages>
<c:if test="${curPage eq pageNumber }">${pageNumber}</c:if>
<c:if test="${curPage ne pageNumber }">  <a href="${pageUrl}">${pageNumber}</a></c:if>
<%--  <%
   if(curPage==pageNumber){
	   %>
	   [<%=pageNumber%>]
	   <% 
   }else{
	   %>
	   <a href="<%=pageUrl%>"><%=pageNumber%></a>
	   <% 
   }
 %> --%>

</pg:pages>


<pg:next>
<a href="${pageUrl }">下一页</a>
</pg:next>
<pg:last>
<a  href="${pageUrl }">尾页</a>
</pg:last>
</pg:pager>
  