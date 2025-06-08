<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.userDatabase.userDatabase.model.User" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Group View - ${group.name}</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
    <header>
        <h1>${group.name}</h1>
        <p>Welcome, ${user.name}!</p>
        <nav>
            <a href="/my-groups">My Groups</a> |
            <a href="/groups">All Groups</a> |
            <a href="/logout">Logout</a>
        </nav>
    </header>

    <main>
        <section>
            <h2>Posts</h2>
            <c:forEach var="post" items="${posts}">
			    <div>
			        <c:choose>
					  <c:when test="${not empty post and not empty post.author}">
					    <strong>${post.author.name}</strong>: ${post.content} <em>(${post.createdAt})</em>
					  </c:when>
					  <c:otherwise>
					    <strong>Unknown User</strong>: ${post.content}
					  </c:otherwise>
					</c:choose>
			    </div>
			</c:forEach>
		</section>

        <section>
            <h3>Add a New Post</h3>
            <form action="/posts/create" method="post">
                <input type="hidden" name="userId" value="${user.id}" />
                <input type="hidden" name="groupId" value="${group.id}" />
                <textarea name="content" placeholder="Write your post here..." required rows="3" cols="40"></textarea><br>
                <button type="submit">Post</button>
            </form>
        </section>
    </main>
</body>
</html>
