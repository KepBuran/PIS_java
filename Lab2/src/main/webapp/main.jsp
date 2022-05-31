<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 16.05.2022
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WWW-Game</title>
    <link href="./css/main.css" rel="stylesheet" type="text/css">
</head>

<body>
    <header>
        <a href="${pageContext.request.contextPath}" class="header-button left" >WWW-Game</a>
        <a href="${pageContext.request.contextPath}?command=logout" class="header-button right" >Logout</a>
    </header>

    <main>
        <%String username = (String) request.getAttribute("username");
            if (username == null) {
                username="User";
            }%>
        <div class="welcome">
            <h1 class="welcome-text">Welcome to "What?Where?When?" game, <%=username%>!</h1>
        </div>

        <div class="main-menu">
            <a class="menu-button top-button" href="${pageContext.request.contextPath}?command=userGames">My Games</a> <br>
            <a class="menu-button" href="${pageContext.request.contextPath}?command=getUserPacks">My Packs</a> <br>
            <a class="menu-button" href="${pageContext.request.contextPath}?command=findGame">Find Game</a> <br>
            <a class="menu-button bottom-button" href="${pageContext.request.contextPath}?command=listGames">All Games</a> <br>
        </div>
    </main>
</body>
</html>
