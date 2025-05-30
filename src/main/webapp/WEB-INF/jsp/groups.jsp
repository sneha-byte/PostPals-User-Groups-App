<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.userDatabase.userDatabase.model.Group" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>PostPals - Groups</title>
    <link rel="stylesheet" href="/styles.css">
    <style>
        /* Reset + Base Styling */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI';
        }

        :root {
            --pink: #FFDCDC;
            --peach: #FFF2EB;
            --beige: #FFE8CD;
            --orange: #FFD6BA;
            --text-dark: #333;
            --text-muted: #555;
        }

        /* Body */
        body {
            background-color: var(--peach);
            color: var(--text-dark);
            padding: 20px;
            min-height: 100vh;
        }

        /* Header */
        header {
            background-color: var(--pink);
            padding: 1em 2em;
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.05);
        }

        .logo {
            font-size: 1.5em;
            font-weight: bold;
            color: var(--text-dark);
        }

        .nav-buttons {
            display: flex;
            gap: 1rem;
        }

        .nav-btn {
            background-color: var(--orange);
            color: var(--text-dark);
            padding: 0.7em 1.5em;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        .nav-btn:hover {
            background-color: #ffc09f;
        }

        .nav-btn.active {
            background-color: #ff80c0;
        }

        /* Main Content */
        main {
            margin-top: 2em;
            padding: 2rem;
        }

        .page-title {
            text-align: center;
            font-size: 2.5em;
            margin-bottom: 1.5em;
            color: var(--text-dark);
        }

        /* Table Container */
        .table-container {
            background-color: var(--beige);
            padding: 2em;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.05);
            max-width: 1200px;
            margin: 0 auto;
        }

        .groups-table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1);
        }

        .groups-table th {
            background-color: var(--orange);
            color: var(--text-dark);
            padding: 1.2em;
            text-align: left;
            font-weight: bold;
            font-size: 1.1em;
        }

        .groups-table td {
            padding: 1em 1.2em;
            border-bottom: 1px solid #f0f0f0;
            color: var(--text-dark);
        }

        .groups-table tr:nth-child(even) {
            background-color: var(--peach);
        }

        .groups-table tr:hover {
            background-color: var(--pink);
            transition: background-color 0.2s ease;
        }

        .groups-table tr:last-child td {
            border-bottom: none;
        }

        .no-groups {
            text-align: center;
            padding: 3em;
            color: var(--text-muted);
            font-size: 1.2em;
            background-color: var(--peach);
        }

        /* Footer */
        footer {
            background-color: var(--orange);
            padding: 1.5em 0;
            text-align: center;
            color: var(--text-dark);
            margin-top: 3em;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.05);
        }
    </style>
</head>

<body>
    <header>
        <div class="logo">PostPals</div>
        <nav class="nav-buttons">
            <a href="/home" class="nav-btn">Home</a>
            <a href="/members" class="nav-btn">Users</a>
            <a href="/groups" class="nav-btn active">Groups</a>
        </nav>
    </header>

    <main>
        <h1 class="page-title">Groups</h1>
        <div class="table-container">
            <table class="groups-table">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Group Name</th>
                        <th>Members</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty groups}">
                            <c:forEach var="group" items="${groups}" varStatus="status">
                                <tr>
                                    <td>${status.index + 1}</td>
                                    <td><c:out value="${group.name != null ? group.name : 'N/A'}" /></td>
                                    <td><c:out value="${group.memberships.size()} members" /></td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="3" class="no-groups">No groups found</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>
    </main>

    <footer>
        <p>© 2025 PostPals. All rights reserved.</p>
    </footer>
</body>
</html>
