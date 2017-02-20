<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<jsp:useBean id="message" scope="request" class="model.Error"/>--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <style>
        <%@include file='../css/registration.css' %>
    </style>
    <script>
        <%@include file="../js/jquery-3.1.1.min.js"%>
        <%@include file="../js/registration.js"%>
    </script>
</head>
<body>
    <div id="wrapper">
        <div id="content">
            <div id="form">
                <form class="form" action="" method="post" >
                    <h2>Регистрация</h2>
                    <hr>
                    <div class="messenger"> </div>
                    <div class="input">
                        <p>Имя</p>
                        <input type="text" name="nickname" required/>
                    </div>
                    <div class="input">
                        <p>E-mail</p>
                        <input id="email" type="email" name="email" required onkeyup="checkEmail()" onchange="checkEmail()"/>
                    </div>
                    <div class="input">
                        <p>Пароль</p>
                        <input id="pass" type="password" name="password" required/>
                    </div>
                    <div class="input">
                        <p>Повторите пароль</p>
                        <input id="repass" type="password" name="repassword" required onkeyup="checkRepass()"/>
                    </div>
                    <div class="radio">
                        <input type="radio" name="sex"  value="f" checked><span>Ж</span>
                        <input type="radio" name="sex" value="m"><span>М</span>
                    </div>
                    <div id="button">
                        <input type="submit" value="Зарегестрироваться" />
                        <input type="submit" id="sendButton" style="display: none;" />
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>