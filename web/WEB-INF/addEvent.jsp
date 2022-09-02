<%--
  Created by IntelliJ IDEA.
  User: appleadmin
  Date: 02.09.22
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Event</title>
</head>
<body>
Please input event's data:
<form action="/add" method="post">
  <input type="text" name="name" placeholder="please input name"><br>
  <input type="text" name="place" placeholder="please input place"><br>
  Is Online?<br>
  Yes<input type="radio" name="isOnline" value="TRUE">
  Yes<input type="radio" name="isOnline" value="FALSE">
  <br>
  <input type="number" name="price" placeholder="Please input price">
  <br>
  Event Type
  <select name="eventType">
    <option value="FESTIVAL">FESTIVAL</option>
    <option value="CONCERT">CONCERT</option>
    <option value="STAFF_PARTY">STAFF_PARTY</option>
    <option value="CONFERENCE">CONFERENCE</option>
    <option value="EMPLOYEE_EVENT">EMPLOYEE_EVENT</option>
    <br>
    <input type="date" name="eventDate">
    <input type="submit" value="Add">
  </select>

</form>

</body>
</html>
