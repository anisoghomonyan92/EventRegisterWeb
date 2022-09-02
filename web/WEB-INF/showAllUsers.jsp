<%@ page import="model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: appleadmin
  Date: 02.09.22
  Time: 23:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users page</title>
</head>
<body>
<%
    List<User> users = (List<User>) request.getAttribute("user");
%>
<table border="1">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>surname</th>
        <th>email</th>
        <th>eventId</th>
    </tr>

    <%
        for (User user : users) {

    %>
    <tr>
        <td><%=user.getId()%>
        </td>
        <td><%=user.getName()%>
        </td>
        <td><%=user.getSurname()%>
        </td>
        <td><%=user.getEmail()%>
        </td>
        <td><%=user.getEventId()%>
        </td>
    </tr>
    <%
        }
    %>

</table>

</body>
</html>
