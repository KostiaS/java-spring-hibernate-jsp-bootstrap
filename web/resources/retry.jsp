<%--
  Created by IntelliJ IDEA.
  User: Konstantin
  Date: 2016-08-09
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>Create edit message view</div>
<jsp:useBean id="formValidator" class="com.ciklum.model.CreateEditFormValidator" scope="request" />

<%
    long messageId = 0L;
    try {
        messageId = Long.valueOf(request.getParameter("messageId"));
    } catch (NumberFormatException e) {
        messageId = -1;
    }
%>
<form name="create-edit-message" action="/processing-create-edit-message-validation" method="post">
    <input type="hidden" name="messageId" value="<%=messageId%>">
    <input type="text" name="userName" value="<%=formValidator.getUserName() %>" placeholder="user name">
    <div style="color: red"><%=formValidator.getErrorMsg("userName") %></div>
    <div style="color: red"><%=formValidator.getErrorMsg("userNameRegex") %></div>
    <input type="text" name="messageText" value="<%=formValidator.getMessageText() %>" placeholder="type message text">
    <div style="color: red"><%=formValidator.getErrorMsg("messageText") %></div>
    <input type="submit" name="saveBtn" value="Save">
</form>
<a href="/">Home page</a>
</body>
</html>
