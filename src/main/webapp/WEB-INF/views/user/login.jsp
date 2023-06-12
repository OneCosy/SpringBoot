<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<c:if test="${cookie.ssafy_id.value ne null}">
    <c:set var="idck" value=" checked"/>
    <c:set var="saveid" value="${cookie.bit_id.value}"/>
</c:if>

<script type="text/javascript">
    $(document).ready(function () {
        $("#userpwd").keydown(function(key) {
            if (key.keyCode === 13) {
                login();
            }
        });

        $("#loginBtn").click(function () {
            login();
        });

        $("#mvRegisterBtn").click(function () {
            location.href = "${root}/user/register";
        });
    });

    function login() {
        if (!$("#userid").val()) {
            alert("아이디 입력!!!");
            return;
        } else if (!$("#userpwd").val()) {
            alert("비밀번호 입력!!!");
            return;
        } else {
            $("#loginform").attr("action", "${root}/user/login").submit();
        }
    }
</script>

<div class="container text-center mt-3">
    <div class="col-lg-8 mx-auto">
        <h2 class="p-3 mb-3 shadow bg-light"><mark class="orange">로그인</mark></h2>
        <form id="loginform" class="text-left mb-3" method="post" action="">
            <div class="form-group form-check text-right">
                <label class="form-check-label">
                    <input class="form-check-input" type="checkbox" id="idsave" name="idsave" value="saveok"${idck}> 아이디저장
                </label>
            </div>
            <div class="form-group">
                <label for="userid">아이디</label>
                <input type="text" class="form-control" id="userid" name="userId" placeholder="" value="${saveid}">
            </div>
            <div class="form-group">
                <label for="userpwd">비밀번호</label>
                <input type="password" class="form-control" id="userpwd" name="userPwd" placeholder="">
            </div>
            <div class="text-danger mb-2">${msg}</div>
            <div class="form-group text-center">
                <button type="button" id="loginBtn" class="btn btn-outline-warning">로그인</button>
                <button type="button" id="mvRegisterBtn" class="btn btn-outline-primary">회원가입</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>