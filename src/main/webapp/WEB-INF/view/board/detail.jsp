<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/res/css/board/detail.css">
<div>
    <div class="btnPos">
    <c:if test="${sessionScope.loginUser.iuser == requestScope.data.writer}" >
            <a href="/board/regmod?iboard=${requestScope.data.iboard}"><button>수정</button></a>
            <a href="/board/del?iboard=${requestScope.data.iboard}"><button>삭제</button></a>
    </c:if>
    </div>

    <div class="detail">
        <div>번호 : ${requestScope.data.iboard}</div>
        <di>조회수 : ${requestScope.data.hits}</di>
        <div>작성자 : ${requestScope.data.writerNm}</div>
        <div>제목 : <c:out value="${requestScope.data.title}"/></div>
        <div>작성일자 : ${requestScope.data.rdt}</div>
        <div>내용 : <c:out value="${requestScope.data.ctnt}"/></div>
    </div>

    <div class="moveBtn">
        <c:if test="${requestScope.prevBoard != 0}" >
            <a href="/board/detail?iboard=${requestScope.prevBoard}"><span><button>이전글</button></span></a>
        </c:if>

        <c:if test="${requestScope.nextBoard != 0}" >
            <a href="/board/detail?iboard=${requestScope.nextBoard}"><span><button>다음글</button></span></a>
        </c:if>
    </div>
</div>