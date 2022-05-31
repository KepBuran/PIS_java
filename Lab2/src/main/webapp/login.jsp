<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WWW-Game Login</title>
    <link href="./css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
    <main>
        <form action="" method="POST">
            <%String message = (String) request.getAttribute("message");
                if (message == null) {
                    message="";
                }%>
            <input type="hidden" name="command" value="login"/>
            Username: <input type="text" name="username"/> <br>
            Password: <input type="password" name="password"/> <br>
            <p  style="color: red; font-size: 14px">
                <%=message%>
            </p>
            <input type="submit" class="form-submit-button"/>
        </form><br><br>
        <p>Don't have account? <a href="${pageContext.request.contextPath}?command=registerUser">Register</a> right now!</p>
    </main>
</body>
</html>
