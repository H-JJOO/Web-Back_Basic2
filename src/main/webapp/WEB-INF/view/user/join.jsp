<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <form action="/user/join" method="post" id="frm" onsubmit="return joinChk();">
        <div><input type="text" name="uid" placeholder="아이디" required></div>
        <div><input type="password" name="upw" placeholder="비밀번호" required></div>
        <div><input type="password" name="upwChk" placeholder="비밀번호 확인" required></div>
        <div><input type="text" name="nm" placeholder="이름" required></div>
        <div>
            <label>female<input type="radio" name="gender" value="2" checked></label>
            <label>male<input type="radio" name="gender" value="1"></label>
        </div>
        <div>
            <input type="submit" value="회원가입">
            <input type="reset" value="초기화">
        </div>
    </form>
</div>
<script src="/res/js/user/join.js"></script>
