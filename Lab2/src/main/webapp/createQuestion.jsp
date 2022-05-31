<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 25.05.2022
  Time: 02:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Creating new question</title>
    <link href="./css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
    <%String message = (String) request.getAttribute("message");
        if (message == null) {
            message="";
        }%>
    <form action="" method="POST">
        <input type="hidden" name="packId" value="<%=request.getParameter("packId")%>"/>
        <input type="hidden" name="command" value="createPack"/>
        Question: <input type="text" name="question" value="...?"/> <br>
        Answer: <input type="text" name="answer" value=""/> <br>
            <p  style="color: red; font-size: 14px">
                <%=message%>
            </p>
        <input type="submit" class="form-submit-button"/>
    </form>
</body>
</html>
