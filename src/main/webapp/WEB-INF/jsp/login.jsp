<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - PostPals</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
    <header>
        <div class="logo">PostPals</div>
    </header>

    <main>
        <section class="hero">
            <h1>Log In</h1>

            <form action="login" method="post" class="login-form">
                <div class="form-group">
                    <label for="name">Username:</label>
                    <input type="text" id="name" name="name" required>
                </div>

                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>

                <button type="submit" class="btn btn-primary">Log In</button>
            </form>

			<p class="signup-prompt">
				Don't have an account? 
				<a href="/signup">Sign Up</a>
			</p>
        </section>
    </main>

    <footer>
        <p>© 2025 PostPals. All rights reserved.</p>
    </footer>
</body>
</html>
