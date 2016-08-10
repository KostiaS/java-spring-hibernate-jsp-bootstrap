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
    <title>Title</title>
    <style>
        .hideMe {
            -moz-animation: cssAnimation 0s ease-in 5s forwards;
            /* Firefox */
            -webkit-animation: cssAnimation 0s ease-in 5s forwards;
            /* Safari and Chrome */
            -o-animation: cssAnimation 0s ease-in 5s forwards;
            /* Opera */
            animation: cssAnimation 0s ease-in 5s forwards;
            -webkit-animation-fill-mode: forwards;
            animation-fill-mode: forwards;
        }
        @keyframes cssAnimation {
            to {
                width:0;
                height:0;
                overflow:hidden;
            }
        }
        @-webkit-keyframes cssAnimation {
            to {
                width:0;
                height:0;
                visibility:hidden;
            }
        }
    </style>
</head>
<body>
    <h5>Main View</h5>
    <%
        String result = request.getParameter("result");
        if(result != null) {
            if(result.equals("saved") || result.equals("deleted")) {
    %>
    <div class="hideMe" style="color: green">Message was successfully <%=result%>.</div>
    <%
            } if (result.equals("not saved") || result.equals("not deleted")) {
    %>
    <div class="hideMe" style="color: red">Error occurred. Message was <%=result%>.</div>
    <%
            }
        }
    %>
    <jsp:useBean id="messagesList" class="com.ciklum.model.UserMessageList" scope="application" />
    <%
//        Map<String, String> messages = messagesList.getUsersMessages();
//        for(Map.Entry<String, String> entry: messages.entrySet()) {

        List<UserMessageData> userMessageDataList = messagesList.getUsersMessages();
        for(UserMessageData userMessageData : userMessageDataList) {
    %>
    <%--<div>user name: <%=entry.getKey()%>, message: <%=entry.getValue()%></div>--%>
    <div>user messageId: <%=userMessageData.getMessageId()%>, user name: <%=userMessageData.getUserName()%>, message: <%=userMessageData.getMessageText()%></div>
    <a href="/create-edit-message/?messageId=<%=userMessageData.getMessageId()%>&userName=<%=userMessageData.getUserName()%>">Edit message</a>
    <form action="/delete-message" method="post">
        <input type="hidden" name="userId" value="<%=userMessageData.getUserId()%>">
        <input type="hidden" name="messageId" value="<%=userMessageData.getMessageId()%>">
        <input type="hidden" name="userName" value="<%=userMessageData.getUserName()%>">
        <input type="submit" name="deleteMessage" value="Delete message">
    </form>
    <%--<a href="/delete-message/?messageId=<%=userMessageData.getMessageId()%>&userName=<%=userMessageData.getUserName()%>">Delete message</a>--%>
    <%
        }
    %>
    <a href="/create-edit-message">Add new message</a>

</body>
</html>
