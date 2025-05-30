<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.userDatabase.userDatabase.model.Group" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>PostPals - Groups</title>
    <link rel="stylesheet" href="/styles.css">
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
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
            background-color: #4a6bdf;
            color: white;
            padding: 0.5rem 1rem;
            border-radius: 4px;
            text-decoration: none;
            font-weight: 500;
            transition: background-color 0.2s;
        }
        
        .nav-btn:hover {
            background-color: #3a5bcf;
        }
        
        .nav-btn.active {
            background-color: #2a4bbf;
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
        
        .groups-table {
            width: 100%;
            max-width: 1200px;
            margin: 0 auto;
            border-collapse: collapse;
            background: white;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        
        .groups-table th,
        .groups-table td {
            padding: 1rem;
            text-align: left;
            border-bottom: 1px solid #eee;
        }
        
        .groups-table th {
            background-color: #333;
            color: white;
            font-weight: 600;
        }
        
        .groups-table tr:hover {
            background-color: #f8f9fa;
        }
        
        .groups-table tr:last-child td {
            border-bottom: none;
        }
        
        .no-groups {
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
            <a href="/members" class="nav-btn">Users</a>
            <a href="/groups" class="nav-btn active">Groups</a>
        </nav>
    </header>

    <main>
        <h1 class="page-title">Groups</h1>
        <table class="groups-table">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Group Name</th>
                    <th>Members</th>
                </tr>
            </thead>
            <tbody>
                <%
                    @SuppressWarnings("unchecked")
                    List<Group> groups = (List<Group>) request.getAttribute("groups");
                    
                    if (groups != null && !groups.isEmpty()) {
                        for (int i = 0; i < groups.size(); i++) {
                            Group group = groups.get(i);
                            int memberCount = group.getMemberships() != null ? group.getMemberships().size() : 0;
                %>
                    <tr>
                        <td><%= i + 1 %></td>
                        <td><%= group.getName() != null ? group.getName() : "N/A" %></td>
                        <td><%= memberCount %> members</td>
                    </tr>
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="3" class="no-groups">No groups found</td>
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