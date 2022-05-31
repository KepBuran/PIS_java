<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 25.05.2022
  Time: 04:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Changing pack</title>
    <link href="./css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
    <header>
        <a href="${pageContext.request.contextPath}" class="header-button left" >WWW-Game</a>
        <a href="${pageContext.request.contextPath}?command=logout" class="header-button right" >Logout</a>
    </header>
    <br>
    <main>
        <form action="" method="POST">
            <%String message = (String) request.getAttribute("message");
                if (message == null) {
                    message="";
                }%>
            <input type="hidden" name="command" value="changePackName"/>
            Pack name: <input type="text" name="newPackName" value="<%=request.getAttribute("packName")%>"/> <br>
            <p  style="color: red; font-size: 14px">
                <%=message%>
            </p>
            <input type="submit" class="form-submit-button" value="Change"/>
        </form>
    </main>
</body>
</html>
