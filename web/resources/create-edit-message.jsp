<%--
  Created by IntelliJ IDEA.
  User: Konstantin
  Date: 2016-08-08
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div>Create/Edit View</div>

    <%
        String userName = "";
        String readonly = "";
        long userId = 0L;
        long messageId = 0L;
        try {
//            userId = Long.valueOf(request.getParameter("userId"));
            messageId = Long.valueOf(request.getParameter("messageId"));
            userName = request.getParameter("userName");
            if(userName == null) {
                userName = "";
            }
            readonly = "readonly";
        } catch (NumberFormatException e) {
//            userId = -1;
            messageId = -1;
        }
    %>
    <form name="create-edit-message" action="/processing-create-edit-message-validation" method="post">
        <%--<input type="hidden" name="userId" value="<%=userId%>">--%>
        <input type="hidden" name="messageId" value="<%=messageId%>">
        <input type="text" name="userName" value="<%=userName%>" placeholder="user name" <%=readonly%>>
        <input type="text" name="messageText" value="" placeholder="type message text">
        <input type="submit" name="saveBtn" value="Save">
    </form>
    <a href="/">Home page</a>
</body>
</html>
