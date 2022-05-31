<%@ page import="java.util.List" %>
<%@ page import="pis.lab2.entities.Pack" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
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
  <link href="./css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
  <header>
    <a href="${pageContext.request.contextPath}" class="header-button left" >WWW-Game</a>
    <a href="${pageContext.request.contextPath}?command=logout" class="header-button right" >Logout</a>
  </header>

  <main>
    <input type="hidden" name="command" value="getAllPacks" />
    <br>
    <a class="add-button" href="${pageContext.request.contextPath}?command=createPack"> + </a><br>
    <div class="packs-div">
      <%
        List<Pack> packs = (List<Pack>) request.getAttribute("userPacks");

        if (packs == null || packs.size() == 0) {
          out.println("<p>You don't have any packs.</p>");
          return;
        }
        String path = request.getContextPath();
        for (int i = 0; i < packs.size(); i++) {
          Pack pack = packs.get(i);
          out.print("<div class=\"pack\">" +
                          "<b>Pack:</b> " + pack.getName() + "</a><br>" +
                          "<b>Created on:</b> " + new SimpleDateFormat("HH:mm dd.MM.yyyy").format(pack.getCreatedOn()) +"<br>"+
                          "<b>Last updated:</b> " + new SimpleDateFormat("HH:mm dd.MM.yyyy").format(pack.getLastUpdated()) + "<br><br>"+
                          "<a class=\"pack-open\" href=\""+path+"?command=openPack&packId="+pack.getId()+"\""+">Open</a>"+ //+"&packName="+pack.getName()
                          "<a class=\"pack-delete\" href=\""+path+"?command=deletePack&packId="+pack.getId()+"\""+">Delete</a>"+
                  "</div><br>");
        }
      %>
    </div>
  </main>
</body>
</html>

