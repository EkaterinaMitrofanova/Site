<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="model.Post" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Page</title>
    <style>
        <%@include file='../css/page.css' %>
    </style>
    <script src="https://api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript"></script>
    <script charset="UTF-8">
        <%@include file="../js/moment.js"%>
        <%@include file="../js/jquery-3.1.1.min.js"%>
        <%@include file="../js/page.js"%>
    </script>
</head>
<body>
<a id="toTop" href="javascript:toTop()">Наверх</a>
<div id="wrapper">
    <div id="content">
        <div id="header">
            <div id="header-topLine">
                <div id="header-photo">
                    <img src="../images/ava6.jpg">
                </div>
                <div id="header-nickname">
                    <span id="nickname"><%= session.getAttribute("name") %></span>
                </div>
                <div id="header-topLine-list">
                    <ul>
                        <li><a href="<c:url value="/map"/>">Карта</a></li>
                        <%--<li><a href="#">Сообщения</a></li>--%>
                    </ul>
                    <div id="exit">
                        <a href="<c:url value="/exit"/>">
                            <div id="exit-text">
                                Выход
                            </div>
                        </a>
                    </div>
                </div>
            </div>

            <div id="header-bottomLine">
                <span>Введите текст</span>
                <div id="header-post">
                    <div id="header-post-top">
                        <textarea id="area" maxlength="2000" name="post" placeholder="Ваше сообщение..."></textarea>
                    </div>
                    <div id="header-post-bot">
                        <%--<textarea id="areaMap" onclick="getMap()" name="postMap" placeholder="Местоположение..."></textarea>--%>
                        <a id= "addMap" onclick="getMap()">Выбрать местоположение..</a>
                        <div id="placeName"></div>
                        <%--<button onclick="getLocation()">Искать</button>--%>
                        <input id="sub" type="submit" value="Добавить" onclick="sub('Удалить','Нравится')"
                               data-count="1"/>
                    </div>
                </div>
                <div>
                    <div id = "map" data-coords = "0" data-location = "0">

                    </div>
                </div>
            </div>
        </div>

        <div id="mainContent">

            <%--По непонятной причине не работает выражение ${variable}, оно выводилось как текст, --%>
            <%--поэтому пришлось прибегнуть к данному методу--%>

            <% ArrayList<Post> postList = (ArrayList<Post>) request.getAttribute("list");%>
            <% if (postList.size() != 0) {%>
                <% for (Post post : postList) { %>
            <div class="mainContent-post" data-id = <%= post.getIdPost()%>>
                <div class="post-middleLine">
                    <div class="post-header">
                        <a class="post-photo" href="#">
                            <img src="../images/ava6.jpg"> </a>
                        <div class="post-info">
                            <div class="post-author"> <%= session.getAttribute("name")%> </div>
                            <div class="post-date"> <%= post.getDate()%> </div>
                        </div>
                        <a class="delete" data-id ='<%= post.getIdPost()%>' onclick="deletePost(this)">Удалить</a>
                    </div>
                    <div class="post-text"> <%= post.getText()%> </div>
                </div>
                <div class="post-bottomLine">
                    <a href="#" class="post-likeImg" onclick="return like()">
                    <div class="post-like"></div>
                    </a>
                    <div class="post-amountLikes"> <%= post.getLocation()%> </div>
                </div>
            </div>
                <% } %>
            <% } %>

        </div>
    </div>
</div>
<div class="footer">

</div>
</body>
</html>