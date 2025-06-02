<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sign Up - PostPals</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
    <header>
        <div class="logo">PostPals</div>
    </header>

    <main>
        <section class="hero">
            <h1>Sign Up</h1>
         
			<form action="/signup" method="post" class="signup-form">
			    <div class="form-group">
			        <label for="name">Username:</label>
			        <input type="text" id="name" name="name">
			    </div>
			
			    <div class="form-group">
			        <label for="email">Email:</label>
			        <input type="email" id="email" name="email">
			    </div>
			
			    <div class="form-group">
			        <label for="password">Password:</label>
			        <input type="password" id="password" name = "password">
			    </div>
			
			    <button type="submit" class="btn btn-primary">Sign Up</button>
			</form>
            <p class="signup-prompt">
                Already have an account? <a href="/login">Log In</a>
            </p>
        </section>
    </main>

    <footer>
        <p>© 2025 PostPals. All rights reserved.</p>
    </footer>
</body>
</html>
