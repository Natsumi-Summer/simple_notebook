<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_duccess">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>ノート一覧</h2>
        <div class="index_list">
            <ul>
                <c:forEach var="note" items="${notes}">
                <div class="index_list_title">
                    <a href="${pageContext.request.contextPath}/show?id=${note.id}">
                        <c:out value="${note.id}"/> </a>
                            :<c:out value="${note.title}"></c:out>
                </div>
                <li>
                    作成日時:<fmt:formatDate value="${note.created_at}" pattern="yyyy-MM-dd"/>
                </li>
                <li>
                    更新日時:<fmt:formatDate value="${note.updated_at}" pattern="yyyy-MM-dd"/>
                </li>
                <li>
                     <pre><c:out value="${note.content}"/></pre>
                </li>
            </c:forEach>
        </ul>
        </div>
        <div id="pagination">
            (全 ${notes_count}件)<br/>
            <c:forEach var="i" begin="1" end="${((notes_count -1)/3) +1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}"/>&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/index?page=${i}"><c:out value="${i}"/></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="${pageContext.request.contextPath}/new">新規ノートの登録</a></p>

    </c:param>
</c:import>