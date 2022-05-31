<%@ page import="pis.lab2.entities.Question" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 24.05.2022
  Time: 01:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pack</title>
    <link href="./css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
    <header>
        <a href="${pageContext.request.contextPath}" class="header-button left" >WWW-Game</a>
        <a href="${pageContext.request.contextPath}?command=logout" class="header-button right" >Logout</a>
    </header>

    <main>
        <br>
        <a class="back-button" href="${pageContext.request.contextPath}?command=getUserPacks">Back</a><br>
        <a class="add-button" href="${pageContext.request.contextPath}?command=createQuestion&packId=<%=request.getParameter("packId")%>"> + </a><br>
        <p><span class="pack-name"><b>Pack: </b><%=request.getAttribute("packName")%></span><a href="${pageContext.request.contextPath}?command=changePackName&packId=<%=request.getParameter("packId")%>" class="change-pack" >Change name</a></p>
        <p></p>
<%--        <form action="" method="POST">--%>
<%--            <%String message = (String) request.getAttribute("message");--%>
<%--                if (message == null) {--%>
<%--                    message="";--%>
<%--                }%>--%>
<%--            <input type="hidden" name="command" value="changePackName"/>--%>
<%--            Pack name: <input type="text" name="newPackName" value="<%=request.getAttribute("packName")%>"/> <br>--%>
<%--            <p  style="color: red; font-size: 14px">--%>
<%--                <%=message%>--%>
<%--            </p>--%>
<%--            <input type="submit" class="form-submit-button" value="Change"/>--%>
<%--        </form>--%>



        <div class="question-div">
            <%
                List<Question> questions = (List<Question>) request.getAttribute("questions");
                if (questions == null || questions.size() == 0) {
                    out.print("<p>No questions in the pack yet! Click \"+\" to add...</p>");
                    return;
                }
                for (int i = 0; i <= questions.size(); i++) {
                    if (i==0) {
                        out.print("");
                        continue;
                    }
                    out.print("<div class=\"question\"><p>"+i+") <b>Question:</b> "+questions.get(i-1).getQuestionText()+"</p>"+
                                "<p>   <b>Answer:</b> "+questions.get(i-1).getAnswer()+"</p>"+
                                "<a class=\"pack-delete\" href=\""+request.getContextPath()+"?command=deleteQuestion&questionId="+questions.get(i-1).getId()+"&packId="+request.getParameter("packId")+"\""+">Delete</a>"+"</div><br>");
                }
            %>
        </div>
    </main>
</body>
</html>
