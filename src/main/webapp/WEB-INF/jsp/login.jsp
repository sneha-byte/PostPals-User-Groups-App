<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - PostPals</title>
    <link rel="stylesheet" href="/styles.css">
    <style>
	.error-message {
	    color: red;
	    font-weight: bold;
	    margin-bottom: 1em;
	}
	.success-message {
	    color: green;
	    font-weight: bold;
	    margin-bottom: 1em;
	}
	</style>
</head>
<body>
    <header>
        <div class="logo">PostPals</div>
    </header>

    <main>
        <section class="hero">
            <h1>Log In</h1>
		    <c:if test="${not empty error}">
			    <div class="error-message" id="errorMsg">${error}</div>
				    <script>
				        setTimeout(function() {
				            const msg = document.getElementById('errorMsg');
				            if (msg) msg.style.display = 'none';
				        }, 3000); 
				</script>
			</c:if>

		
		    <c:if test="${not empty success}">
			    <div class="success-message" id="successMsg">${success}</div>
			    <script>
			        setTimeout(function() {
			            const msg = document.getElementById('successMsg');
			            if (msg) msg.style.display = 'none';
			        }, 3000);
			    </script>
			</c:if>
		    
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
