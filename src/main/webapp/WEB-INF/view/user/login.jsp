<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <form action="/user/login" method="post" id="frm">
        <div><input type="text" name="uid" placeholder="아이디"></div>
        <div><input type="password" name="upw" placeholder="비밀번호"></div>
        <div>
            <input type="submit" value="로그인">
            <a href="/user/login"><input type="button" value="회원가입"></a>
        </div>
    </form>
</div>
<script src="/res/js/user/login.js"></script>