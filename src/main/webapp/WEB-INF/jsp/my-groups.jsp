<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Groups</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
    <header>
        <h1>Welcome, ${user.name}!</h1>
        <nav>
            <a href="/groups">Browse All Groups</a> |
            <a href="/logout">Logout</a>
        </nav>
    </header>

    <main>
        <h2>Your Groups</h2>
        <c:forEach var="membership" items="${memberships}">
            <div class="group-card">
                <strong>${membership.group.name}</strong><br />
                Role: ${membership.role}<br />
                <a href="/groups/${membership.group.id}">View Group</a>
                <form action="/membership/leave" method="post" style="display:inline;">
                    <input type="hidden" name="groupId" value="${membership.group.id}" />
                    <input type="hidden" name="userId" value="${user.id}" />
                    <button type="submit">Leave Group</button>
                </form>
            </div>
            <hr />
        </c:forEach>
    </main>
</body>
</html>
