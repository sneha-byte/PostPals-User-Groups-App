<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>PostPals - My Groups</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<header>
    <div class="logo">PostPals</div>
    <nav class="nav-buttons">
        <a href="/home" class="nav-btn">Home</a>
        <a href="/members" class="nav-btn">Users</a>
        <a href="/groups" class="nav-btn">Groups</a>
        <a href="/myGroups?userId=${user.id}" class="nav-btn active">My Groups</a>
    </nav>
</header>

<main>
    <h1 class="page-title">My Groups</h1>

    <div class="table-container">
        <c:choose>
            <c:when test="${not empty myGroups}">
                <table class="groups-table">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Group Name</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="group" items="${myGroups}" varStatus="loop">
                            <tr>
                                <td>${loop.index + 1}</td>
                                <td><c:out value="${group.name}" /></td>
                                <td>
                                    <a href="/groups/${group.id}" class="nav-btn">View Posts</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p class="no-groups">You're not a member of any groups yet.</p>
            </c:otherwise>
        </c:choose>
    </div>
</main>

<footer>
    <p>© 2025 PostPals. All rights reserved.</p>
</footer>
</body>
</html>
