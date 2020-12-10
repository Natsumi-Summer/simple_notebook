<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>ノート一覧</h2>
        <ul>
            <c:forEach var="note" items="${notes}">
                <li>
                    <a href="${pageContext.request.contextPath}/show?id=${note.id}">
                        <c:out value="${note.id}"/>
                    </a>
                    :<c:out value="${note.title}"></c:out> &gt; <c:out value="${note.content}"/>
                </li>
            </c:forEach>
        </ul>

        <p><a href="${pageContext.request.contextPath}/new">新規ノートの登録</a></p>

    </c:param>
</c:import>