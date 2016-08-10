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
        <%
            String userName = "";
            String messageText = "";
            String readonly = "";
            long userId = 0L;
            long messageId = 0L;
            try {
    //            userId = Long.valueOf(request.getParameter("userId"));
                messageId = Long.valueOf(request.getParameter("messageId"));
                userName = request.getParameter("userName");
                messageText = request.getParameter("messageText");
                if(userName == null) {
                    userName = "";
                }
                readonly = "readonly";
            } catch (NumberFormatException e) {
    //            userId = -1;
                messageId = -1;
            }
        %>
        <form name="create-edit-message margin-top-30px" action="/processing-create-edit-message-validation" method="post">
            <div class="form-group">
                <%--<input type="hidden" name="userId" value="<%=userId%>">--%>
                <input type="hidden" name="messageId" value="<%=messageId%>">
                <input class="form-control margin-top-15px" type="text" name="userName" value="<%=userName%>" placeholder="user name" <%=readonly%>>
                <textarea rows="10" class="form-control margin-top-15px" type="text" name="messageText"
                          placeholder="type message text"><%=messageText%></textarea>
                <div class="margin-top-15px pull-right">
                    <input class="btn btn-default" type="submit" name="saveBtn" value="Save">
                    <%--<div class="space btn btn-default"></div>--%>
                    <a class="btn btn-default margin-left-5px" href="/">Cancel</a>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
