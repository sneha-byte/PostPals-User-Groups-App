<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.userDatabase.userDatabase.model.User" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Group View - ${group.name}</title>
    <link rel="stylesheet" href="/styles.css">
    <style>
        
        header {
		    background-color: var(--pink);
		    padding: 1em 2em;
		    display: flex;
		    justify-content: space-between;
		    align-items: center;
		    flex-wrap: wrap;
		    border-radius: 12px;
		    margin: 1em 1em;
		}

        nav {
            margin-top: 0.5em;
        }

        nav .nav-buttons {
            display: flex;
            gap: 1em;
        }

        nav .btn {
            padding: 0.5em 1.2em;
            border-radius: 5px;
            background-color: var(--orange);
            color: var(--text-dark);
            text-decoration: none;
            font-weight: 500;
            transition: background-color 0.3s ease;
        }

        nav .btn:hover {
            background-color: var(--orange-dark);
        }

        .welcome {
            font-size: 1.1em;
            color: var(--text-muted);
            margin-top: 0.5em;
        }

        h1 {
            font-size: 2em;
            color: var(--text-dark);
            margin-bottom: 0.2em;
        }

        main {
            padding: 2em;
        }

        h2 {
            font-size: 1.5em;
            margin-bottom: 1em;
        }

        .post-card {
            background-color: var(--beige);
            padding: 1em 1.5em;
            border-radius: 8px;
            margin-bottom: 2em;
            box-shadow: 0 2px 4px rgba(0,0,0,0.05);
        }

        .post-card strong {
            color: var(--text-dark);
            font-size: 1.05em;
        }

        .post-card em {
            color: var(--text-muted);
            font-size: 0.9em;
        }
        
        .post-meta {
		    font-size: 0.95em;
		    color: var(--text-muted);
		    margin-bottom: 0.3em;
		}
        

        form textarea {
            width: 100%;
            max-width: 600px;
            padding: 0.7em;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 1em;
            margin-bottom: 1em;
            box-sizing: border-box;
        }

        form button {
            padding: 0.6em 1.5em;
            background-color: var(--orange-dark);
            color: var(--text-dark);
            border: none;
            border-radius: 5px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        form button:hover {
            background-color: var(--orange-darker);
        }
    </style>
</head>
<body>
    <header>
        <div>
            <h1>${group.name}</h1>
            <p class="welcome">Welcome, ${user.name}!</p>
        </div>
        <nav>
            <div class="nav-buttons">
                <a class="btn" href="/members">All Users</a>           
                <a class="btn" href="/my-groups">My Groups</a>
                <a class="btn" href="/groups">All Groups</a>                
                <a class="btn" href="/logout">Logout</a>
            </div>
        </nav>
    </header>

    <main>
        <section>
    <h2>Posts</h2>
    <c:forEach var="post" items="${posts}">
        <div class="post-card">
            <c:choose>
                <c:when test="${not empty post and not empty post.author and not empty post.createdAt}">
                    <div class="post-meta">
						<fmt:formatDate value="${post.createdAtAsDate}" pattern="M/d/yyyy h:mm a" />
                    </div>
                    <div><strong>${post.author.name}</strong>: ${post.content}</div>
                </c:when>
                <c:otherwise>
                    <div class="post-meta"><em>Unknown Date</em></div>
                    <div><strong>Unknown User</strong>: ${post.content}</div>
                </c:otherwise>
            </c:choose>
        </div>
    </c:forEach>
</section>

        <section>
            <h2>Add a New Post</h2>
            <form action="/posts/create" method="post">
                <input type="hidden" name="userId" value="${user.id}" />
                <input type="hidden" name="groupId" value="${group.id}" />
                <textarea name="content" placeholder="Write your post here..." required rows="3"></textarea><br>
                <button type="submit">Post</button>
            </form>
        </section>
    </main>
</body>
</html>
