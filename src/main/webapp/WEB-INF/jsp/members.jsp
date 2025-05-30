<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.userDatabase.userDatabase.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>PostPals - Members</title>
    <link rel="stylesheet" href="/styles.css">
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI';
            background-color: #f8f9fa;
        }
        
        /* Header with navigation */
        header {
            background-color: #333;
            color: white;
            padding: 1rem 2rem;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        
        .logo {
            font-size: 1.5rem;
            font-weight: bold;
        }
        
        .nav-buttons {
            display: flex;
            gap: 1rem;
        }
        
        .nav-btn {
            background-color: ##ff80c0;
            color: white;
            padding: 0.5rem 1rem;
            border-radius: 4px;
            text-decoration: none;
            font-weight: 500;
            transition: background-color 0.2s;
        }
        
        .nav-btn:hover {
            background-color: #ff80c0;
        }
        
        .nav-btn.active {
            background-color:#ff80c0;
        }
        
        /* Main content */
        main {
            padding: 2rem;
            min-height: calc(100vh - 120px);
        }
        
        .page-title {
            text-align: center;
            font-size: 2rem;
            margin-bottom: 2rem;
            color: #333;
        }
        
        .members-table {
            width: 100%;
            max-width: 1200px;
            margin: 0 auto;
            border-collapse: collapse;
            background: white;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        
        .members-table th,
        .members-table td {
            padding: 1rem;
            text-align: left;
            border-bottom: 1px solid #eee;
        }
        
        .members-table th {
            background-color: #333;
            color: white;
            font-weight: 600;
        }
        
        .members-table tr:hover {
            background-color: #f8f9fa;
        }
        
        .members-table tr:last-child td {
            border-bottom: none;
        }
        
        .no-users {
            text-align: center;
            padding: 2rem;
            color: #666;
        }
        
        /* Footer */
        footer {
            text-align: center;
            padding: 1.5rem;
            background-color: #333;
            color: white;
            margin-top: auto;
        }
    </style>
</head>

<body>
    <header>
        <div class="logo">PostPals</div>
        <nav class="nav-buttons">
            <a href="/home" class="nav-btn">Home</a>
            <a href="/members" class="nav-btn active">Users</a>
            <a href="/groups" class="nav-btn">Groups</a>
        </nav>
    </header>

    <main>
        <h1 class="page-title">Users</h1>
        <table class="members-table">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>Email</th>
                </tr>
            </thead>
            <tbody>
                <%
                    @SuppressWarnings("unchecked")
                    List<User> users = (List<User>) request.getAttribute("users");
                    
                    if (users != null && !users.isEmpty()) {
                        for (int i = 0; i < users.size(); i++) {
                            User user = users.get(i);
                %>
                    <tr>
                        <td><%= i + 1 %></td>
                        <td><%= user.getName() != null ? user.getName() : "N/A" %></td>
                        <td><%= user.getEmail() != null ? user.getEmail() : "N/A" %></td>
                    </tr>
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="3" class="no-users">No users found</td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </main>

    <footer>
        <p>© 2025 PostPals. All rights reserved.</p>
    </footer>
</body>
</html>
