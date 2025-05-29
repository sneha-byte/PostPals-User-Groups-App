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
                <label for="username"> userName:</label>
                <input type="text" id="username" name="username" required>

                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>

                <button type="submit" class="btn">Log In</button>
            </form>
        </section>
    </main>

    <footer>
        <p>© 2025 PostPals. All rights reserved.</p>
    </footer>
</body>
</html>
