<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Groups</title>
    <link rel="stylesheet" href="/styles.css">
    <style>
        .group-header {
            background-color: var(--beige);
            padding: 1.2em 2em;
            text-align: center;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.04);
            margin: 2em auto 1em auto;
            max-width: 800px;
        }

        .group-header h2 {
            font-size: 1.75em;
            margin: 0;
            color: var(--text-dark);
        }

        .group-card {
            background-color: var(--beige);
            padding: 1.5em;
            border-radius: 10px;
            margin: 1.5em auto;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
            max-width: 800px;
        }

        .group-card h3 {
            margin-bottom: 0.5em;
        }

        .group-card p {
            margin-bottom: 1em;
        }

        .buttons {
            display: flex;
            gap: 1em;
        }

        .group-card .btn {
            flex: 1;
            text-align: center;
        }

        form {
            display: inline;
            flex: 1;
        }

        form button.btn {
            width: 100%;
        }
    </style>
</head>
<body>
    <header>
        <h1 class="logo">Welcome, ${user.name}!</h1>
        <nav>
            <ul>
                <li><a href="/groups">Browse All Groups</a></li>
                <li><a href="/logout">Logout</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <section class="group-header">
            <h2>Your Groups</h2>
        </section>

        <section>
            <c:forEach var="membership" items="${memberships}">
                <div class="group-card">
                    <h3>${membership.group.name}</h3>
                    <p>Role: <strong>${membership.role}</strong></p>
                    <div class="buttons">
                        <a class="btn btn-secondary" href="/groups/${membership.group.id}">View Group</a>
                        <form action="/membership/leave" method="post">
                            <input type="hidden" name="groupId" value="${membership.group.id}" />
                            <input type="hidden" name="userId" value="${user.id}" />
                            <button type="submit" class="btn btn-primary">Leave Group</button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </section>
    </main>
</body>
</html>
