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
            padding: 0.7em 1.2em;
            border-radius: 5px;
            background-color: var(--orange);
            color: var(--text-dark);
            text-decoration: none;
            font-weight: 600;
            transition: background-color 0.3s ease;
        }

        nav .btn:hover {
            background-color: var(--orange-dark);
        }

        .welcome {
            font-size: 1.4em;
            color: var(--text-dark);
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
        
         h4 {
            font-size: 1.8em;
            margin-bottom: 1em;
        }

        .post-card {
            background-color: var(--beige);
            padding: 1em 1.5em;
            border-radius: 8px;
            margin-bottom: 2em;
            box-shadow: 0 2px 4px rgba(0,0,0,0.05);
        }
        
        .post-content {
		    margin-bottom: 1em;
		    font-size: 1.2em; 
		    font-weight: 500;
		}

        .post-card strong {
            color: var(--text-dark);
            font-size: 1em;  
            font-weight: 610;          
        }

        .post-card em {
            color: var(--text-muted);
            font-size: 2.5em;
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
            border: 2px solid #333;
            border-radius: 5px;
            font-size: 1.2em;
            margin-bottom: 1em;
            box-sizing: border-box;
        }

        .post-btn {
		    padding: 0.6em 2.2em;
		    background-color: var(--orange-dark);
		    color: var(--text-dark);
		    border: none;
		    border-radius: 5px;
		    font-weight: bold;
		    cursor: pointer;
		    font-size: 1.2em;
		    min-width: 80px;
		    min-height: 30px;
		    transition: background-color 0.3s ease;
		}
        
        .post-btn:hover {
            background-color: var(--orange-darker);
        }
        
        button.delete {
		    margin-top: 0.8em;
		    background-color: var(--red); 
		    color: white;
		    padding: 0.6em 1.2em;
		    border: none;
		    border-radius: 5px;
		    font-weight: bold;
		    cursor: pointer;
		    transition: background-color 0.3s ease;
		}
		
		button.delete:hover {
		    background-color: var(--red-darker); 
		}

        form button.delete {
		    background-color: var(--red);
            color: var(--text-dark);
		}
		
		form button.delete:hover {
		    background-color: var(--red-darker);
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
				<a class="btn" href="/groups?userId=${user.id}">All Groups</a>
                <a class="btn" href="/logout">Logout</a>
            </div>
        </nav>
    </header>

    <main>
        <section>
    <h4>Posts</h4>
    <c:forEach var="post" items="${posts}">
        <div class="post-card">
            <c:choose>
                <c:when test="${not empty post and not empty post.author and not empty post.createdAt}">
                    <div class="post-meta">
						<fmt:formatDate value="${post.createdAtAsDate}" pattern="M/d/yyyy h:mm a" />
                    </div>
						<div class="post-content">
						    <strong>${post.author.name}</strong>: ${post.content}
						</div>                
				</c:when>
                <c:otherwise>
                    <div class="post-meta"><em>Unknown Date</em></div>
                    <div><strong>Unknown User</strong>: ${post.content}</div>
                </c:otherwise>
            </c:choose>
            <c:if test="${post.author.id == user.id}">
	            <form action="/posts/delete" method="post" style="margin-top: 0.5em;">
		            <input type="hidden" name="postId" value="${post.id}" />
		            <input type="hidden" name="groupId" value="${group.id}" />
		       	<button class = "delete" type="submit">Delete</button>
	        	</form>
	        </c:if>
        </div>
    </c:forEach>
</section>

        <section>
            <h2>Add a New Post</h2>
            <form action="/posts/create" method="post">
                <input type="hidden" name="userId" value="${user.id}" />
                <input type="hidden" name="groupId" value="${group.id}" />
                <textarea name="content" placeholder="Write your post here..." required rows="3"></textarea><br>
                <button class = "post-btn" type="submit">Post</button>
            </form>
        </section>
    </main>
</body>
</html>
