<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Groups</title>
    <link rel="stylesheet" href="/styles.css">
    <style>
     	main {
            width: 100%;
        }
        
	    header {
			    background-color: var(--pink);
			    padding: 1.5em 2em;
			    display: flex;
			    font-size: 20px;
			    justify-content: space-between;
			    align-items: center;
			    flex-wrap: wrap;
			    border-radius: 12px;
			    margin: 1em 1em;
			    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.04);
			}
		
		.group-container {
            width: 100%;
            padding: 0 2em;
        }

        .group-header {
            background-color: var(--beige);
            padding: 1.2em 2em;
            text-align: left;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.04);
            margin: 2em 0 1.5em 0;
        }

        .group-header h2 {
            font-size: 1.75em;
            margin: 0;
            color: var(--text-dark);
        }

		/* Container layout for group card buttons */
		.buttons {
		    display: flex;
		    justify-content: flex-start;
		    gap: 1em;
		    margin-top: 1.5em;
		}
		
        .group-card {
            background-color: var(--beige);
            padding: 1.5em;
            border-radius: 10px;
            margin-bottom: 1.5em;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
            width: 100%;
        }

        .group-card h3 {
		    font-size: 1.5em;
		    font-weight: 600;
		    margin-bottom: 0.4em;
		    color: var(--text-dark);
		}
		

		.group-card p {
		    font-size: 1.2em;
		    color: var(--text-dark);
		    margin: 0.2em 0;
		    font-weight: 600;
		}


        /* Base styles general button rules */
		.btn {
		    padding: 0.5em 1.2em;
		    font-size: 1.15em;
		    min-width: 250px;
		    text-align: center;
		    display: inline-block;
		    border-radius: 6px;
		}
		
		/* Specialized button styles for nav bar */
		/* overrides the default .btn width */
		.nav-buttons .btn {
		    font-size: 0.9em;
		    padding: 0.9em 1em;
		    min-width: 110px;
		    min-height: 60px;  
		    background-color: var(--orange);
		}
		
		/* Container layout for nav buttons */
		.nav-buttons {
		    display: flex;
		    gap: 1em;
		}
			
		form {
		    display: inline;
		}    

        form button.btn {
            width: 50%;
        }     
	</style>
        
</head>
	<body>
	    <header>
		    <div>
		        <h3 class="welcome">Welcome, ${user.name}!</h3>
		    </div>
		    <nav>
			    <div class="nav-buttons">
			        <a class="btn btn-primary" href="/my-groups">My Groups</a>
			        <a class="btn btn-primary" href="/groups">All Groups</a>
			        <a class="btn btn-primary" href="/logout">Logout</a>
			    </div>
			</nav>
		</header>

   	<main>
	    <div class="group-container">
	        <section class="group-header">
	            <h2>Your Groups</h2>
	        </section>
	
	        <c:forEach var="membership" items="${memberships}">
	            <div class="group-card">
	                <h3>${membership.group.name}</h3>
	                <p>Role: ${membership.role}</p>
	                <div class="buttons">
	                    <a class="btn btn-primary" href="/groups/${membership.group.id}">View Group</a>
	                    <form action="/membership/leave" method="post">
	                        <input type="hidden" name="groupId" value="${membership.group.id}" />
	                        <input type="hidden" name="userId" value="${user.id}" />
	                        <button type="submit" class="btn btn-primary">Leave Group</button>
	                    </form>
	                </div>
	            </div>
	        </c:forEach>
		    </div>
		</main>
	</body>
</html>
