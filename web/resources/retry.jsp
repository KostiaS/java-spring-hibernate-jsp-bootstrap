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
    <title>Create/Edit message</title>
    <link rel="stylesheet" href="resources/css/create-edit.css">
    <!--jquery-->
    <script src="resources/lib/jquery-2.2.3.min.js"></script>
    <!--bootstrap-->
    <link rel="stylesheet" href="resources/lib/bootstrap-3.3.7-dist/bootstrap.min.css">
    <link rel="stylesheet" href="resources/lib/bootstrap-3.3.7-dist/bootstrap-theme.min.css">
    <script src="resources/lib/bootstrap-3.3.7-dist/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
    <h2 class="text-center">Create/Edit Message</h2>
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
        <div class="form-group">
            <input type="hidden" name="messageId" value="<%=messageId%>">
            <input class="form-control margin-top-15px" type="text" name="userName" value="<%=formValidator.getUserName() %>" placeholder="user name">
            <!--Error messages-->
            <div class="error-message"><%=formValidator.getErrorMsg("userName") %></div>
            <div class="error-message"><%=formValidator.getErrorMsg("userNameRegex") %></div>
            <textarea rows="10" class="form-control margin-top-15px" type="text" name="messageText"
                      placeholder="type message text"><%=formValidator.getMessageText()%></textarea>
            <!--Error messages-->
            <div class="error-message"><%=formValidator.getErrorMsg("messageText") %></div>
            <div class="error-message"><%=formValidator.getErrorMsg("messageTextLength") %></div>
            <div class="margin-top-15px pull-right">
                <!--Save button-->
                <input class="btn btn-default" type="submit" name="saveBtn" value="Save">
                <!--Cancel button-->
                <a class="btn btn-default margin-left-5px" href="/">Cancel</a>
            </div>
        </div>
    </form>
    </div>
</body>
</html>
