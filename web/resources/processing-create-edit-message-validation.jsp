<%@ page import="com.ciklum.model.CreateEditFormValidator" %><%--
  Created by IntelliJ IDEA.
  User: Konstantin
  Date: 2016-08-09
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="formValidator" class="com.ciklum.model.CreateEditFormValidator" scope="request">
    <jsp:setProperty name="formValidator" property="*" />
</jsp:useBean>
<%
    if (formValidator.validate()) {
%>
<jsp:forward page="/save-message" />
<%
    } else {
%>
<jsp:forward page="/retry" />
<%
    }
%>
