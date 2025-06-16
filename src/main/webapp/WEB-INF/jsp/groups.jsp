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
			    padding: 1.4em 2em;
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
        
        .group-input {
		    padding: 0.7em 1.4em;
		    font-size: 1em;
		    border: 1px solid #ccc;
		    border-radius: 6px;
		    width: 90%;
		    max-width: 290px;
		    box-sizing: border-box;
		    margin: 1em; 
		}
		
		.group-button {
		    padding: 0.4em 1.2em; 
		    font-size: 0.95em;
		    background-color: var(--orange-dark);
		    font-weight: bold;
		    color: var(--text-dark);
		    border: none;
		    border-radius: 6px;
		    cursor: pointer;
		    transition: background-color 0.3s ease;
		}
		
		.group-button:hover {
		    background-color: var(--orange-darker);
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
        
		.create-group-container {
		    background-color: var(--beige);
		    padding: 1em;
		    margin: 0 auto 1em auto;
		    border-radius: 10px;
		    max-width: 350px;
		    box-shadow: 0 4px 8px rgba(0,0,0,0.05);
		    text-align: center;
		}
		
		.create-group-form {
		    display: flex;
		    flex-direction: column;
		    gap: 2em;
		    align-items: center;
		}
		
		.group-label {
		    font-weight: bold;
		    font-size: 1.2em;
		    color: var(--text-dark);
		    
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
        <h1 class="page-title">Groups</h1>
        <div class="table-container">
			<div style="text-align: center; margin-bottom: 2em;">
			    <div class="create-group-container">
				    <form action="/group/create" method="post">
				        <label for="groupName" class="group-label">Create a New Group</label>
				        <input type="text" id="groupName" name="groupName" placeholder="Enter group name..." required class="group-input" />
				        <button type="submit" class="group-button">Create Group</button>
				    </form>
				</div>

			</div>
            <table class="groups-table">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Group Name</th>
                        <th>Members</th>
                        <th>Join Group</th>
                    </tr>
                </thead>
				<tbody>
				    <!-- User's joined groups -->
				    <c:forEach var="group" items="${joinedGroups}" varStatus="status">
				        <tr>
				            <td>${status.index + 1}</td>
				            <td><c:out value="${group.name}" /></td>
				            <td><c:out value="${group.memberships.size()} members" /></td>
				            <td>Already Joined</td>
				        </tr>
				    </c:forEach>
				
				    <!-- Other groups with Join buttons -->
				    <c:forEach var="group" items="${otherGroups}" varStatus="status">
				        <tr>
				            <td>${status.index + joinedGroups.size() + 1}</td>
				            <td><c:out value="${group.name}" /></td>
				            <td><c:out value="${group.memberships.size()} members" /></td>
				            <td>
				                <form action="/membership/add" method="post">
				                    <input type="hidden" name="groupId" value="${group.id}" />
				                    <input type="hidden" name="role" value="member" />
				                    <input type="hidden" name="userId" value="${user.id}" />
				                    <button type="submit" class="group-button">Join Group</button>
				                </form>
				            </td>
				        </tr>
				    </c:forEach>
				</tbody>
            </table>
        </div>
    </main>

    <footer>
        <p>© 2025 PostPals. All rights reserved.</p>
    </footer>
</body>
</html>
