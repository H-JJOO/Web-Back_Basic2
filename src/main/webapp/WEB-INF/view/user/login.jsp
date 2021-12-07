<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <form action="/user/login" method="post" id="frm">
        <div><input type="text" name="uid" placeholder="아이디"></div>
        <div><input type="password" name="upw" placeholder="비밀번호"></div>
        <div>
            <input type="submit" value="로그인">
        </div>
    </form>
    <div>
        <a href="/user/login"><input type="button" value="회원가입"></a>
    </div>
    <div>
        <input type="button" value="비밀번호 보이기" id="btnShowPw">
    </div>
</div>
<script src="/res/js/user/login.js"></script>