<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.userDatabase.userDatabase.model.User" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<h2>Posts in ${group.name}</h2>

<c:forEach var="post" items="${posts}">
    <div>
        <strong>${post.user.name}</strong>: ${post.content} <em>(${post.timestamp})</em>
    </div>
</c:forEach>

<form action="/posts/create" method="post">
    <input type="hidden" name="userId" value="${user.id}" />
    <input type="hidden" name="groupId" value="${group.id}" />
    <textarea name="content" required></textarea>
    <button type="submit">Post</button>
</form>

</head>

</html>