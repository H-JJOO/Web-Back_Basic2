<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/res/css/board/list.css" ver="1">

<c:choose>
    <c:when test="${requestScope.maxPageNum == 0}">
        <div>작성된 게시글이 없습니다.</div>
    </c:when>
    <c:otherwise>
        <div>
            <form action="/board/list" method="get">
                <div>
                    <input type="search" name="searchText">
                    <input type="submit" value="검색">
                </div>
            </form>
        </div>
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
<div class="pageContainer">
    <c:set var="selectedPage" value="${param.page == null ? 1 : param.page}"/>
    <c:forEach var="page" begin="1" end="${maxPageNum}">
        <div><a href="/board/list?page=${page}"><span class="${selectedPage == page ? 'selected' : ''}"><c:out value="${page}"/></span></a></div>
    </c:forEach>
</div>
    </c:otherwise>
</c:choose>
<script src="/res/js/board/list.js"></script>