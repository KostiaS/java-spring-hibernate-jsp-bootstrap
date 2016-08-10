<%@ page import="com.ciklum.model.UserMessageList"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ciklum.common.UserMessageData" %><%--
  Created by IntelliJ IDEA.
  User: Konstantin
  Date: 2016-08-06
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Main page</title>
    <link rel="stylesheet" href="resources/css/main.css">

    <!--jquery-->
    <script src="resources/lib/jquery-2.2.3.min.js"></script>
    <!--bootstrap-->
    <link rel="stylesheet" href="resources/lib/bootstrap-3.3.7-dist/bootstrap.min.css">
    <link rel="stylesheet" href="resources/lib/bootstrap-3.3.7-dist/bootstrap-theme.min.css">
    <script src="resources/lib/bootstrap-3.3.7-dist/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <%
        String result = request.getParameter("result");
        if(result != null) {
            if(result.equals("saved") || result.equals("deleted")) {
    %>
    <div class="hideMe successMessage text-center">Message was successfully <%=result%>.</div>
    <%
            } if (result.equals("not saved") || result.equals("not deleted")) {
    %>
    <div class="hideMe errorMessage text-center">Error occurred. Message was <%=result%>.</div>
    <%
            }
        }
    %>
    <jsp:useBean id="messagesList" class="com.ciklum.model.UserMessageList" scope="application" />
    <h2 class="text-center margin-top-15px">Main View</h2>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Message text</th>
                    <th>User name</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<UserMessageData> userMessageDataList = messagesList.getUsersMessages();
                    for(UserMessageData userMessageData : userMessageDataList) {
                %>
                <tr>
                    <td class="visible-lg visible-md multiline-lg-md"><%=userMessageData.getMessageText()%></td>
                    <td class="visible-sm visible-xs multiline-sm-xs"><%=userMessageData.getMessageText()%></td>
                    <td><%=userMessageData.getUserName()%></td>
                    <td>
                        <form style="margin-bottom: 5px" class="btn btn-default btn-group" action="/create-edit-message" method="get">
                            <input type="hidden" name="messageId" value="<%=userMessageData.getMessageId()%>">
                            <input type="hidden" name="userName" value="<%=userMessageData.getUserName()%>">
                            <input type="hidden" name="messageText" value="<%=userMessageData.getMessageText()%>">
                            <input class="clear-style" type="submit" name="editMessage" value="Edit">
                        </form>
                        <form style="margin-bottom: 5px" class="btn btn-default btn-group" action="/delete-message" method="post">
                            <input type="hidden" name="userId" value="<%=userMessageData.getUserId()%>">
                            <input type="hidden" name="messageId" value="<%=userMessageData.getMessageId()%>">
                            <input type="hidden" name="userName" value="<%=userMessageData.getUserName()%>">
                            <input class="clear-style" type="submit" name="deleteMessage" value="Delete">
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%--<div>user messageId: <%=userMessageData.getMessageId()%>, user name: <%=userMessageData.getUserName()%>, message: <%=userMessageData.getMessageText()%></div>--%>
        <%--<a href="/create-edit-message/?messageId=<%=userMessageData.getMessageId()%>&userName=<%=userMessageData.getUserName()%>">Edit message</a>--%>
    <%--    <form action="/delete-message" method="post">
            <input type="hidden" name="userId" value="<%=userMessageData.getUserId()%>">
            <input type="hidden" name="messageId" value="<%=userMessageData.getMessageId()%>">
            <input type="hidden" name="userName" value="<%=userMessageData.getUserName()%>">
            <input type="submit" name="deleteMessage" value="Delete message">
        </form>--%>
    <%--    <%
            }
        %>--%>
        <div class="row margin-top-30px">
            <div class="col-lg-offset-5 col-lg-2">
                <a class="btn btn-primary center-block" href="/create-edit-message">Add new message</a>
            </div>
            <div class="col-lg-5"></div>
        </div>

    </div>
</body>
</html>