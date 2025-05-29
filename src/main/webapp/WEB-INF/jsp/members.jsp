<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Members</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
    <h1>All Members</h1>

    <form method="get" action="/members">
        <input type="text" name="search" placeholder="Search by name">
        <button type="submit">Search</button>
    </form>

    <ul>
        <c:forEach var="user" items="${users}">
            <li>
                Name: ${user.name}, Email: ${user.email}
            </li>
        </c:forEach>
    </ul>
</body>
</html>
