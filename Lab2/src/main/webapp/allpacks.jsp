<%@ page import="java.util.List" %>
<%@ page import="pis.lab2.entities.Pack" %><%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 17.05.2022
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Question Packs</title>
</head>
<body>
    <input type="hidden" name="command" value="getAllPacks" />
    <div>
        <%
            List<Pack> packs = (List<Pack>) request.getAttribute("packs");
            if (packs == null) {
                out.println("<p>PACKS NOT FOUND</p>");
                return;
            }
            for (int i = 0; i < packs.size(); i++) {
                out.println("<p>"+i+") "+packs.get(i)+"</p>");
            }
        %>
    </div>
</body>
</html>
