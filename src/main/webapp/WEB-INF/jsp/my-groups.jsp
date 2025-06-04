<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>My Groups</title>
</head>
<body>
    <h2>Groups for ${user.name}</h2>

    <c:forEach var="membership" items="${memberships}">
        <div>
            <strong>Group:</strong> ${membership.group.name} <br />
            <strong>Role:</strong> ${membership.role} <br />
            <a href="/groups/${membership.group.id}">View Group</a>
        </div>
        <hr />
    </c:forEach>
</body>
</html>
