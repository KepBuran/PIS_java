<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 25.05.2022
  Time: 00:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WWW-Game Registration</title>
    <link href="./css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
<main>
    <form action="" method="POST">
        <%String message = (String) request.getAttribute("message");
            if (message == null) {
                message="";
            }%>
        <input type="hidden" name="command" value="registerUser"/>
        Username: <input type="text" name="username"/> <br>
        Password: <input type="password" name="password"/> <br>
        <p  style="color: red; font-size: 14px">
            <%=message%>
        </p>
        <input type="submit" class="form-submit-button"/>
    </form>
</main>
</body>
</html>
