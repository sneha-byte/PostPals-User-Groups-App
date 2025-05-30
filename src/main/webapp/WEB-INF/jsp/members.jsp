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
        .members-section {
            max-width: 800px;
            margin: 2rem auto;
            padding: 0 1rem;
        }
        
        .members-table {
            width: 100%;
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
    </style>
</head>

<body>
    <header>
        <div class="logo">PostPals</div>
    </header>

    <main>
        <section class="members-section">
            <h2>Users</h2>
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
        </section>
    </main>

    <footer>
        <p>© 2025 PostPals. All rights reserved.</p>
    </footer>
</body>
</html>