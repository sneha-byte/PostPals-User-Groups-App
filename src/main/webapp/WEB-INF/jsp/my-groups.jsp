<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>My Groups</title>
</head>
<body>
    <h2>My Groups</h2>
    <ul>
        <c:forEach var="group" items="${groups}">
            <li>
                <a href="/groups/${group.id}/posts?userId=${userId}">
                    ${group.name}
                </a>
            </li>
        </c:forEach>
    </ul>
    <a href="/groups">← Back to All Groups</a>
</body>
</html>
