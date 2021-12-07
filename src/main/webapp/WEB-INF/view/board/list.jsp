<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/res/css/board/list.css">
<div>
    <table id="boardTable">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>조회수</th>
            <th>작성자</th>
            <th>작성일자</th>
        </tr>
        <c:forEach items="${requestScope.list}" var="item">
        <tr class="record" onclick="moveToDetail(${item.iboard})">
            <td>${item.iboard}</td>
            <td><c:out value="${item.title}"/></td>
            <td>${item.hits}</td>
            <td>${item.writerNm}</td>
            <td>${item.rdt}</td>
        </tr>
        </c:forEach>
    </table>
</div>
<script src="/res/js/board/list.js"></script>