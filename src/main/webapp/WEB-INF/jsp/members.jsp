<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.userDatabase.userDatabase.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>PostPals - Members</title>
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
			    padding: 1.2em 2em;
			    display: flex;
			    font-size: 20px;
			    justify-content: space-between;
			    align-items: center;
			    flex-wrap: wrap;
			    border-radius: 12px;
			    margin: 1em 1em;
			    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.04);
        }

        .logo {
            font-size: 1.5em;
            font-weight: bold;
            color: var(--text-dark);
        }

        /* Base styles general button rules */
		.btn {
		    padding: 0.5em 1.0em;
		    font-size: 1.05em;
		    min-width: 200px;
		    text-align: center;
		    display: inline-block;
		    border-radius: 6px;
		}
		
		/* Specialized button styles for nav bar */
		/* overrides the default .btn width */
		.nav-buttons .btn {
		    font-size: 0.8em;
		    padding: 0.9em 1em;
		    min-width: 50px;
		    min-height: 40px;  
		    background-color: var(--orange);
		}
		
		/* Container layout for nav buttons */
		.nav-buttons {
		    display: flex;
		    gap: 1em;
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

        .members-table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1);
        }

        .members-table th {
            background-color: var(--orange);
            color: var(--text-dark);
            padding: 1.2em;
            text-align: left;
            font-weight: bold;
            font-size: 1.1em;
        }

        .members-table td {
            padding: 1em 1.2em;
            border-bottom: 1px solid #f0f0f0;
            color: var(--text-dark);
        }

        .members-table tr:nth-child(even) {
            background-color: var(--peach);
        }

        .members-table tr:hover {
            background-color: var(--orange);
            transition: background-color 0.2s ease;
        }

        .members-table tr:last-child td {
            border-bottom: none;
        }

        .no-users {
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
        	<nav>
			    <div class="nav-buttons">
			        <a class="btn" href="/members">All Users</a>           
	                <a class="btn" href="/my-groups">My Groups</a>
	                <a class="btn" href="/group/groups">All Groups</a>                
	                <a class="btn" href="/logout">Logout</a>
			    </div>
			</nav>
    </header>

    <main>
        <h1 class="page-title">Users</h1>
        <div class="table-container">
            <table class="members-table">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <th>Email</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty users}">
                            <c:forEach var="user" items="${users}" varStatus="status">
                                <tr>
                                    <td>${status.index + 1}</td>
                                    <td><c:out value="${user.name != null ? user.name : 'N/A'}" /></td>
                                    <td><c:out value="${user.email != null ? user.email : 'N/A'}" /></td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="3" class="no-users">No users found</td>
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
