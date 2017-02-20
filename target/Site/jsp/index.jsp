<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="message" scope="request" class="model.Error"/>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Site</title>
    <%--<link type="text/css" href="css/index.css" rel="stylesheet"/>--%>
    <style>
        <%@include file='../css/index.css' %>
    </style>
    <script>
        <%@include file="../js/index.js"%>
    </script>
</head>
<body>
    <div id="wrapper">
        <div id="content">
            <div id="form">
                <form action="<c:url value="/enter"/>" method="post">
                    <h2>Войти</h2>
                    <hr>
                    <p class="error"><%
                    if(null!=request.getAttribute("error"))
                    {
                    out.println(request.getAttribute("error"));
                    }
                    %></p>
                    <div class="input">
                        <p>Email</p>
                        <input type="email" name="email" required />
                    </div>
                    <div class="input">
                        <p>Пароль</p>
                        <input type="password" name="password" required/>
                    </div>
                    <div id="bottom" class="bottom">
                        <input type="submit" value="Войти" />
                        <a href="jsp/registration.jsp">Регистрация</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>